package br.com.desafio.remessa.config;

import br.com.desafio.remessa.domain.carteira.TipoCarteira;
import br.com.desafio.remessa.domain.carteira.TipoCarteiraEnum;
import br.com.desafio.remessa.repository.TipoCarteiraRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CarregarDadosTabela implements CommandLineRunner {

    private final TipoCarteiraRepository tipoCarteiraRepository;

    public CarregarDadosTabela(TipoCarteiraRepository tipoCarteiraRepository) {
        this.tipoCarteiraRepository = tipoCarteiraRepository;
    }

    @Override
    public void run(String... args) throws Exception {
            TipoCarteira cpf = new TipoCarteira(1, "CPF");
            tipoCarteiraRepository.save(cpf);

            TipoCarteira cnpj = new TipoCarteira(2, "CNPJ");
            tipoCarteiraRepository.save(cnpj);
    }
}
