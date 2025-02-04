package br.com.desafio.remessa.configs;

import br.com.desafio.remessa.domains.carteira.TipoCarteira;
import br.com.desafio.remessa.repositories.TipoCarteiraRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarregarDadosTabela implements CommandLineRunner {

    private final TipoCarteiraRepository tipoCarteiraRepository;

    public CarregarDadosTabela(TipoCarteiraRepository tipoCarteiraRepository) {
        this.tipoCarteiraRepository = tipoCarteiraRepository;
    }

    @Override
    public void run(String... args) throws Exception {
            TipoCarteira cpf = new TipoCarteira(1L, "CPF");
            tipoCarteiraRepository.save(cpf);

            TipoCarteira cnpj = new TipoCarteira(2l, "CNPJ");
            tipoCarteiraRepository.save(cnpj);
    }
}
