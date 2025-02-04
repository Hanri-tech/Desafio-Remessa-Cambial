package br.com.desafio.remessa.repositories;

import br.com.desafio.remessa.domains.carteira.TipoCarteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCarteiraRepository extends JpaRepository<TipoCarteira, Long> {
}
