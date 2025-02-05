package br.com.desafio.remessa.repositories;

import br.com.desafio.remessa.domains.cotacao.CotacaoMoeda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CotacaoRepository extends JpaRepository<CotacaoMoeda, Long> {
    Optional<CotacaoMoeda> findFirstByOrderByDataDesc();

    Optional<CotacaoMoeda> findByData(LocalDate ontem);
}
