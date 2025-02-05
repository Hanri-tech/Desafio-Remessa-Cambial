package br.com.desafio.remessa;

import br.com.desafio.remessa.domains.transacao.Transacao;
import br.com.desafio.remessa.dtos.TransacaoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RemessaTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRemessaNormal() {

        TransacaoDTO transacaoDTO = new TransacaoDTO(13L, 12L, new BigDecimal("50.00"));


        ResponseEntity<Transacao> response = restTemplate.postForEntity("/remessa", transacaoDTO, Transacao.class);


        assertEquals(HttpStatus.OK, response.getStatusCode(), "A criação da transação deve retornar HTTP 200 OK");
        Transacao transacaoCriada = response.getBody();
        assertNotNull(transacaoCriada, "A transação retornada não deve ser nula");
        assertNotNull(transacaoCriada.getId(), "A transação deve ter um ID gerado");
        assertEquals(new BigDecimal("50.00"), transacaoCriada.getVlrTransferenciaBRL(), "O valor da transferência deve ser 100.00 BRL");
    }

    @Test
    public void testRemessaSaldoInsuficiente() {

        TransacaoDTO transacaoDTO = new TransacaoDTO(16L, 15L, new BigDecimal("10000.00"));

        ResponseEntity<String> response = restTemplate.exchange("/remessa", HttpMethod.POST, new HttpEntity<>(transacaoDTO), String.class);

        assertTrue(response.getBody().contains("Sem limite disponovel"), "Sem limite disponovel para transferencia");
    }

    @Test
    public void testRemessaFinalDeSemana() {

        TransacaoDTO transacaoDTO = new TransacaoDTO(18L, 19L, new BigDecimal("10.00"));


        ResponseEntity<Transacao> response = restTemplate.postForEntity("/remessa", transacaoDTO, Transacao.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "A criação da transação deve retornar HTTP 200 OK");
        Transacao transacaoCriada = response.getBody();
        assertNotNull(transacaoCriada, "A transação retornada não deve ser nula");

        assertNotNull(transacaoCriada.getCambio(), "O câmbio aplicado não pode ser nulo");
        assertTrue(transacaoCriada.getCambio().getDataCotacao().isBefore(java.time.LocalDate.now()) ||
                        transacaoCriada.getCambio().getDataCotacao().isEqual(java.time.LocalDate.now()),
                "A cotação deve ser do último dia útil, caso a remessa tenha sido realizada no final de semana.");
    }
}
