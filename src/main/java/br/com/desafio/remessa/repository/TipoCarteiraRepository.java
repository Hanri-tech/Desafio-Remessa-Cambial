package br.com.desafio.remessa.repository;

import br.com.desafio.remessa.domain.carteira.TipoCarteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCarteiraRepository extends JpaRepository<TipoCarteira, Long> {
}
