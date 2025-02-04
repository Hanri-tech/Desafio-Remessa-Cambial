package br.com.desafio.remessa.services;

import br.com.desafio.remessa.domains.carteira.Carteira;
import br.com.desafio.remessa.domains.carteira.TipoCarteiraEnum;
import br.com.desafio.remessa.domains.transacao.Transacao;
import br.com.desafio.remessa.dtos.TransacaoDTO;
import br.com.desafio.remessa.exceptions.CarteiraNaoEncontradaException;
import br.com.desafio.remessa.repositories.CarteiraRepository;
import br.com.desafio.remessa.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public Transacao realizarRemessa(TransacaoDTO transacaoDTO) throws Exception {
        Carteira carteiraPagador = carteiraRepository.findById(transacaoDTO.pagador()).orElseThrow(CarteiraNaoEncontradaException::new);
        Carteira carteiraRecebedor = carteiraRepository.findById(transacaoDTO.recebedor()).orElseThrow(CarteiraNaoEncontradaException::new);
        if (transacaoDTO.pagador().equals(transacaoDTO.recebedor())) {
            throw new Exception("Operação não permitida");
        }
        validarLimiteTransacaoPorDia(carteiraPagador);
        validarSaldoTranferencia(transacaoDTO.vlrTransferencia(), carteiraPagador);
        return null;

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

