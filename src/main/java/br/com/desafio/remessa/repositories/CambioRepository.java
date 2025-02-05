package br.com.desafio.remessa.repositories;

import br.com.desafio.remessa.domains.cambio.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
}
