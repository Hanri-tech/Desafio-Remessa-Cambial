package br.com.desafio.remessa.services;

import br.com.desafio.remessa.client.CotacaoClient;
import br.com.desafio.remessa.domains.cotacao.CotacaoMoeda;
import br.com.desafio.remessa.dtos.CotacaoDolarResponseDTO;
import br.com.desafio.remessa.exceptions.ErroBuscarUltimaCotacaoException;
import br.com.desafio.remessa.exceptions.ErroConsumirApiCotacaoException;
import br.com.desafio.remessa.repositories.CotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Service
public class CotacaoDolarService {

    @Autowired
    private CotacaoClient cotacaoClient;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    public BigDecimal buscarCotacaoDolarApi() {
        Date dataAtual = new Date();

        SimpleDateFormat formato = new SimpleDateFormat("MM-dd-yyyy");
        String dataFormatada = formato.format(dataAtual);
        ResponseEntity<CotacaoDolarResponseDTO> dolarDia = cotacaoClient.getApiCotacaoDolarDia(dataFormatada, "json");

        if (!dolarDia.getStatusCode().is2xxSuccessful()) {
            throw new ErroConsumirApiCotacaoException();
        }
        CotacaoDolarResponseDTO cotacaoDolarResponseDTO = dolarDia.getBody();
        boolean vazio = Objects.requireNonNull(cotacaoDolarResponseDTO).getValue().isEmpty();
        if (vazio) {
            throw new ErroConsumirApiCotacaoException();
        }
        return dolarDia.getBody().getValue().get(0).getCotacaoCompra();
    }

    @Cacheable(value = "cotacaoDolar", key = "#hoje.toString()")
    public BigDecimal obterCotacaoDolar(LocalDate hoje) {
        DayOfWeek diaSemana = hoje.getDayOfWeek();

        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            return buscarUltimaCotacao();
        }
        try {
            BigDecimal cotacaoAtual = buscarCotacaoDolarApi();

            CotacaoMoeda cotacao = new CotacaoMoeda();
            cotacao.setValor(cotacaoAtual);
            cotacao.setData(hoje);
            cotacaoRepository.save(cotacao);

            return cotacaoAtual;
        } catch (Exception e) {
            return buscarUltimaCotacao();
        }
    }

    private BigDecimal buscarUltimaCotacao() {
        CotacaoMoeda cotacaoMoeda = cotacaoRepository.findFirstByOrderByDataDesc()
                .orElseThrow(ErroBuscarUltimaCotacaoException::new);

        return cotacaoMoeda.getValor();
    }
}
