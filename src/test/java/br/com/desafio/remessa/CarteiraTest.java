package br.com.desafio.remessa;

import br.com.desafio.remessa.domains.carteira.Carteira;
import br.com.desafio.remessa.dtos.CarteiraDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class CarteiraTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private CarteiraDTO carteiraDTO;

    @Test
    public void testCadastrarCarteira() {
        carteiraDTO = new CarteiraDTO(
                "João Silva",
                "joao@teste.com",
                "senha123",
                1L,
                "12345678900",
                new BigDecimal("1000.00"),
                new BigDecimal("200.00")
        );
        ResponseEntity<Carteira> response = restTemplate.exchange(
                "/carteira", HttpMethod.POST, new HttpEntity<>(carteiraDTO), Carteira.class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "A criação da carteira deve retornar HTTP 200 OK");

        Carteira carteiraCriada = response.getBody();
        assertNotNull(carteiraCriada, "A resposta da criação não deve ser nula");
        assertEquals("João Silva", carteiraCriada.getNomeCompleto(), "O nome completo deve ser João Silva");
        assertEquals(new BigDecimal("1000.00"), carteiraCriada.getSaldoBRL(), "O saldo em BRL deve ser 1000.00");
        assertEquals(new BigDecimal("200.00"), carteiraCriada.getSaldoUSD(), "O saldo em USD deve ser 200.00");
    }

    @Test
    public void testBuscarTodasCarteiras() {
        restTemplate.exchange("/carteira", HttpMethod.POST, new HttpEntity<>(carteiraDTO), Carteira.class);

        ResponseEntity<Carteira[]> response = restTemplate.exchange(
                "/carteira", HttpMethod.GET, null, Carteira[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode(), "A consulta das carteiras deve retornar HTTP 200 OK");


        Carteira[] carteiras = response.getBody();
        assertNotNull(carteiras, "A resposta das carteiras não pode ser nula");
        assertTrue(carteiras.length > 0, "A lista de carteiras não pode estar vazia");
    }
}
