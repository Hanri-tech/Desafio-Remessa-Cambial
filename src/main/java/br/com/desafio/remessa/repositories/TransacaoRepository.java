package br.com.desafio.remessa.repositories;

import br.com.desafio.remessa.domains.transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query(" SELECT COALESCE(SUM (t.vlrTransferenciaBRL), 0) " +
            " FROM Transacao t where t.id = :id AND cast(t.dhTransferencia as DATE ) = :data")
    BigDecimal findSumDiaByTransacao(@Param("id") Long usuarioId, @Param("data") LocalDate data);

}
