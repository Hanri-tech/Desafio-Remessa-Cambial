package br.com.desafio.remessa.repositories;

import br.com.desafio.remessa.domains.carteira.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    Optional<Carteira> findByDocumentoOrEmail(String documento, String email);
}
