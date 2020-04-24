package com.dai.eventos.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "num_pessoas_perm", nullable = false)
    private Integer numPessoasPerm;

    @NotNull
    @Column(name = "limite_area", nullable = false)
    private Integer limiteArea;

    @OneToMany(mappedBy = "area")
    private Set<Evento> eventos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "area_camara",
               joinColumns = @JoinColumn(name = "area_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "camara_id", referencedColumnName = "id"))
    private Set<Camara> camaras = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("areas")
    private Tipoevento tipoevento;

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

    public Integer getNumPessoasPerm() {
        return numPessoasPerm;
    }

    public Area numPessoasPerm(Integer numPessoasPerm) {
        this.numPessoasPerm = numPessoasPerm;
        return this;
    }

    public void setNumPessoasPerm(Integer numPessoasPerm) {
        this.numPessoasPerm = numPessoasPerm;
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

    public Set<Camara> getCamaras() {
        return camaras;
    }

    public Area camaras(Set<Camara> camaras) {
        this.camaras = camaras;
        return this;
    }

    public Area addCamara(Camara camara) {
        this.camaras.add(camara);
        camara.getAreas().add(this);
        return this;
    }

    public Area removeCamara(Camara camara) {
        this.camaras.remove(camara);
        camara.getAreas().remove(this);
        return this;
    }

    public void setCamaras(Set<Camara> camaras) {
        this.camaras = camaras;
    }

    public Tipoevento getTipoevento() {
        return tipoevento;
    }

    public Area tipoevento(Tipoevento tipoevento) {
        this.tipoevento = tipoevento;
        return this;
    }

    public void setTipoevento(Tipoevento tipoevento) {
        this.tipoevento = tipoevento;
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
            ", numPessoasPerm=" + getNumPessoasPerm() +
            ", limiteArea=" + getLimiteArea() +
            "}";
    }
}
