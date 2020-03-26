package com.dai.eventos.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Area.
 */
@Entity
@Table(name = "area")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "num_min_pessoa")
    private Integer numMinPessoa;

    @Column(name = "num_max_pessoa")
    private Integer numMaxPessoa;

    @Column(name = "limite_area")
    private Integer limiteArea;

    @OneToMany(mappedBy = "area")
    private Set<Evento> eventos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Area nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumMinPessoa() {
        return numMinPessoa;
    }

    public Area numMinPessoa(Integer numMinPessoa) {
        this.numMinPessoa = numMinPessoa;
        return this;
    }

    public void setNumMinPessoa(Integer numMinPessoa) {
        this.numMinPessoa = numMinPessoa;
    }

    public Integer getNumMaxPessoa() {
        return numMaxPessoa;
    }

    public Area numMaxPessoa(Integer numMaxPessoa) {
        this.numMaxPessoa = numMaxPessoa;
        return this;
    }

    public void setNumMaxPessoa(Integer numMaxPessoa) {
        this.numMaxPessoa = numMaxPessoa;
    }

    public Integer getLimiteArea() {
        return limiteArea;
    }

    public Area limiteArea(Integer limiteArea) {
        this.limiteArea = limiteArea;
        return this;
    }

    public void setLimiteArea(Integer limiteArea) {
        this.limiteArea = limiteArea;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public Area eventos(Set<Evento> eventos) {
        this.eventos = eventos;
        return this;
    }

    public Area addEvento(Evento evento) {
        this.eventos.add(evento);
        evento.setArea(this);
        return this;
    }

    public Area removeEvento(Evento evento) {
        this.eventos.remove(evento);
        evento.setArea(null);
        return this;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Area)) {
            return false;
        }
        return id != null && id.equals(((Area) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Area{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", numMinPessoa=" + getNumMinPessoa() +
            ", numMaxPessoa=" + getNumMaxPessoa() +
            ", limiteArea=" + getLimiteArea() +
            "}";
    }
}
