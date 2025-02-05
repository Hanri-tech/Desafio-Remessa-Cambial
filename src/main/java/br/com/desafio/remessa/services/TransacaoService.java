package br.com.desafio.remessa.services;

import br.com.desafio.remessa.domains.cambio.Cambio;
import br.com.desafio.remessa.domains.carteira.Carteira;
import br.com.desafio.remessa.domains.carteira.TipoCarteiraEnum;
import br.com.desafio.remessa.domains.cotacao.CotacaoMoeda;
import br.com.desafio.remessa.domains.transacao.Transacao;
import br.com.desafio.remessa.dtos.TransacaoDTO;
import br.com.desafio.remessa.exceptions.CarteiraNaoEncontradaException;
import br.com.desafio.remessa.repositories.CarteiraRepository;
import br.com.desafio.remessa.repositories.CotacaoRepository;
import br.com.desafio.remessa.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CotacaoDolarService cotacaoDolarService;
    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Transactional
    public Transacao realizarRemessa(TransacaoDTO transacaoDTO) throws Exception {
        Carteira carteiraPagador = carteiraRepository.findById(transacaoDTO.pagador()).orElseThrow(CarteiraNaoEncontradaException::new);
        Carteira carteiraRecebedor = carteiraRepository.findById(transacaoDTO.recebedor()).orElseThrow(CarteiraNaoEncontradaException::new);
        if (transacaoDTO.pagador().equals(transacaoDTO.recebedor())) {
            throw new Exception("Operação não permitida");
        }
        validarLimiteTransacaoPorDia(carteiraPagador);
        validarSaldoTranferencia(transacaoDTO.vlrTransferencia(), carteiraPagador);

        debitar(transacaoDTO, carteiraPagador);
        BigDecimal cambioBRLparaUSD = getCambioBRLparaUSD(transacaoDTO);
        creditar(cambioBRLparaUSD, carteiraRecebedor);

        Transacao transacao = new Transacao(carteiraPagador, carteiraRecebedor, transacaoDTO.vlrTransferencia(),
                Timestamp.from(Instant.now()));
        Cambio cambio = criarVariacaoCambial(cambioBRLparaUSD);
        transacao.setCambio(cambio);

        carteiraRepository.save(carteiraPagador);
        carteiraRepository.save(carteiraRecebedor);


        return transacaoRepository.save(transacao);
    }

    private Cambio criarVariacaoCambial(BigDecimal cambioBRLparaUSD) throws Exception {
        Cambio cambio = new Cambio();
        LocalDate ontem = LocalDate.now().minusDays(1);
        BigDecimal cotacaoDolarHoje = cotacaoDolarService.obterCotacaoDolar(LocalDate.now());

        Optional<CotacaoMoeda> cotacaoMoedaOntem = cotacaoRepository.findByData(ontem);

        BigDecimal vlrMoedaOntem = getVlrMoedaOntem(cotacaoDolarHoje, cotacaoMoedaOntem);
        BigDecimal variacaoAbsoluto = vlrMoedaOntem.subtract(cotacaoDolarHoje);
        BigDecimal variacaoCambialPorcetagem = getCambialPorcetagem(vlrMoedaOntem, variacaoAbsoluto);


        cambio.setCotacaoMoedaHoje(cotacaoDolarHoje);
        cambio.setDataCotacao(LocalDate.now());
        cambio.setVlrCambioBRLParaUSD(cambioBRLparaUSD);
        cambio.setVariacaoCambial(variacaoAbsoluto);
        cambio.setVariacaoCambialPercentual(variacaoCambialPorcetagem);
        return cambio;
    }

    private BigDecimal getCambialPorcetagem(BigDecimal vlrMoedaOntem, BigDecimal variacaoAbsoluto) {
        return variacaoAbsoluto
                .divide(vlrMoedaOntem, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));
    }

    private static BigDecimal getVlrMoedaOntem(BigDecimal cotacaoDolarHoje, Optional<CotacaoMoeda> cotacaoMoedaOntem) {
        BigDecimal vlrMoedaOntem = cotacaoDolarHoje;
        if (cotacaoMoedaOntem.isPresent()){
            vlrMoedaOntem = cotacaoMoedaOntem.get().getValor();
        }
        return vlrMoedaOntem;
    }

    private void creditar(BigDecimal cambioBRLparaUSD, Carteira carteiraRecebedor) throws Exception {
        carteiraRecebedor.setSaldoUSD(carteiraRecebedor.getSaldoUSD().add(cambioBRLparaUSD));
    }

    private BigDecimal getCambioBRLparaUSD(TransacaoDTO transacaoDTO) throws Exception {
        BigDecimal cotacaoDolar = cotacaoDolarService.obterCotacaoDolar(LocalDate.now());
        BigDecimal cambioBRLparaUSD = transacaoDTO.vlrTransferencia()
                .divide(cotacaoDolar, 4, BigDecimal.ROUND_HALF_UP);
        return cambioBRLparaUSD;
    }

    private static void debitar(TransacaoDTO transacaoDTO, Carteira carteiraPagador) {
        carteiraPagador.setSaldoBRL(carteiraPagador.getSaldoBRL()
                .subtract(transacaoDTO.vlrTransferencia()));
    }

    private static void validarSaldoTranferencia(BigDecimal vlrTransferencia, Carteira carteiraPagador) throws Exception {
        boolean semLimiteTranferencia = carteiraPagador.getSaldoBRL().compareTo(vlrTransferencia) < 0;

        if (semLimiteTranferencia) {
            throw new Exception("Sem limite disponovel para transferencia");
        }
    }

    private void validarLimiteTransacaoPorDia(Carteira carteiraPagador) throws Exception {
        BigDecimal vlrTransacionadoHoje = transacaoRepository.findSumDiaByTransacao(carteiraPagador.getId(), LocalDate.now());
        boolean cateiraCpf = carteiraPagador.getTipoCarteira().getId().equals(TipoCarteiraEnum.CPF.getId());
        boolean cateiraCnpj = carteiraPagador.getTipoCarteira().getId().equals(TipoCarteiraEnum.CNPJ.getId());

        if (cateiraCpf && vlrTransacionadoHoje.compareTo(BigDecimal.valueOf(10000L)) >= 0) {
            throw new Exception("Valor limite transacional por dia atigindo ");
        }
        if (cateiraCnpj && vlrTransacionadoHoje.compareTo(BigDecimal.valueOf(50000L)) >= 0) {
            throw new Exception("Valor limite transacional por dia atigindo ");
        }
    }

}

