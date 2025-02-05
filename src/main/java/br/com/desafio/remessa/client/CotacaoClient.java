package br.com.desafio.remessa.client;

import br.com.desafio.remessa.dtos.CotacaoDolarResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "${client.service.cotacao.name}",
        url = "${client.service.cotacao.url}"
)
public interface CotacaoClient {

    @GetMapping("/CotacaoDolarDia(dataCotacao=@dataCotacao)?@dataCotacao='{dataCotacao}'&$format={format}")
    public ResponseEntity<CotacaoDolarResponseDTO> getApiCotacaoDolarDia(
            @PathVariable("dataCotacao") String dataCotacao,
            @PathVariable("format") String format
    );
}
