package com.dai.eventos.repository;

import com.dai.eventos.domain.Tipoevento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tipoevento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoeventoRepository extends JpaRepository<Tipoevento, Long> {

}
