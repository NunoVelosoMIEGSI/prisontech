package com.dai.eventos.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Camara.
 */
@Entity
@Table(name = "camara")
public class Camara implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "camara")
    private Set<Evento> eventos = new HashSet<>();

    @ManyToMany(mappedBy = "camaras")
    @JsonIgnore
    private Set<Area> areas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Camara descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isEstado() {
        return estado;
    }

    public Camara estado(Boolean estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public Camara eventos(Set<Evento> eventos) {
        this.eventos = eventos;
        return this;
    }

    public Camara addEvento(Evento evento) {
        this.eventos.add(evento);
        evento.setCamara(this);
        return this;
    }

    public Camara removeEvento(Evento evento) {
        this.eventos.remove(evento);
        evento.setCamara(null);
        return this;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

    public Set<Area> getAreas() {
        return areas;
    }

    public Camara areas(Set<Area> areas) {
        this.areas = areas;
        return this;
    }

    public Camara addArea(Area area) {
        this.areas.add(area);
        area.getCamaras().add(this);
        return this;
    }

    public Camara removeArea(Area area) {
        this.areas.remove(area);
        area.getCamaras().remove(this);
        return this;
    }

    public void setAreas(Set<Area> areas) {
        this.areas = areas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Camara)) {
            return false;
        }
        return id != null && id.equals(((Camara) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Camara{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", estado='" + isEstado() + "'" +
            "}";
    }
}
