package com.dai.eventos.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Logseventos.
 */
@Entity
@Table(name = "logseventos")
public class Logseventos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora_inicio")
    private ZonedDateTime dataHoraInicio;

    @Column(name = "data_hora_fim")
    private ZonedDateTime dataHoraFim;

    @Column(name = "classe_origem")
    private String classeOrigem;

    @Column(name = "metodo_origem")
    private String metodoOrigem;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public Logseventos dataHoraInicio(ZonedDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
        return this;
    }

    public void setDataHoraInicio(ZonedDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public ZonedDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public Logseventos dataHoraFim(ZonedDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
        return this;
    }

    public void setDataHoraFim(ZonedDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getClasseOrigem() {
        return classeOrigem;
    }

    public Logseventos classeOrigem(String classeOrigem) {
        this.classeOrigem = classeOrigem;
        return this;
    }

    public void setClasseOrigem(String classeOrigem) {
        this.classeOrigem = classeOrigem;
    }

    public String getMetodoOrigem() {
        return metodoOrigem;
    }

    public Logseventos metodoOrigem(String metodoOrigem) {
        this.metodoOrigem = metodoOrigem;
        return this;
    }

    public void setMetodoOrigem(String metodoOrigem) {
        this.metodoOrigem = metodoOrigem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Logseventos)) {
            return false;
        }
        return id != null && id.equals(((Logseventos) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Logseventos{" +
            "id=" + getId() +
            ", dataHoraInicio='" + getDataHoraInicio() + "'" +
            ", dataHoraFim='" + getDataHoraFim() + "'" +
            ", classeOrigem='" + getClasseOrigem() + "'" +
            ", metodoOrigem='" + getMetodoOrigem() + "'" +
            "}";
    }
}
