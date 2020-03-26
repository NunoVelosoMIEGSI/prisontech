package com.dai.eventos.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tipoevento.
 */
@Entity
@Table(name = "tipoevento")
public class Tipoevento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "tipoevento")
    private Set<Evento> eventos = new HashSet<>();

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

    public Tipoevento descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public Tipoevento eventos(Set<Evento> eventos) {
        this.eventos = eventos;
        return this;
    }

    public Tipoevento addEvento(Evento evento) {
        this.eventos.add(evento);
        evento.setTipoevento(this);
        return this;
    }

    public Tipoevento removeEvento(Evento evento) {
        this.eventos.remove(evento);
        evento.setTipoevento(null);
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
        if (!(o instanceof Tipoevento)) {
            return false;
        }
        return id != null && id.equals(((Tipoevento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tipoevento{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
