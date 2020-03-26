package com.dai.eventos.repository;

import com.dai.eventos.domain.Camara;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Camara entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CamaraRepository extends JpaRepository<Camara, Long> {

}
