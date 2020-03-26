package com.dai.eventos.repository;

import com.dai.eventos.domain.Logseventos;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Logseventos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogseventosRepository extends JpaRepository<Logseventos, Long> {

}
