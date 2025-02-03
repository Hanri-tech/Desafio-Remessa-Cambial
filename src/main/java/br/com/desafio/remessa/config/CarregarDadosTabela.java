package br.com.desafio.remessa.config;

import br.com.desafio.remessa.domain.carteira.TipoCarteiraEnum;
import br.com.desafio.remessa.repository.TipoCarteiraRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;

public class CarregarDadosTabela implements CommandLineRunner {

    private final TipoCarteiraRepository tipoCarteiraRepository;

    public CarregarDadosTabela(TipoCarteiraRepository tipoCarteiraRepository) {
        this.tipoCarteiraRepository = tipoCarteiraRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(TipoCarteiraEnum.values())
                .forEach(tipoCarteira ->
                        tipoCarteiraRepository.save(tipoCarteira.getTipoCarteira()));

    }
}
