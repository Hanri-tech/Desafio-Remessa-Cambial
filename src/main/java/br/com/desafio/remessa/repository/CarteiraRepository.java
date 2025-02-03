package br.com.desafio.remessa.repository;

import br.com.desafio.remessa.domain.carteira.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
}
