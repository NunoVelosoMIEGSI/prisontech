package com.dai.eventos.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Evento.
 */
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "num_pessoas_perm", nullable = false)
    private Integer numPessoasPerm;

    @NotNull
    @Column(name = "num_pessoas_det", nullable = false)
    private Integer numPessoasDet;

    @NotNull
    @Column(name = "data_hora_inicio", nullable = false)
    private ZonedDateTime dataHoraInicio;

    @NotNull
    @Column(name = "data_hora_fim", nullable = false)
    private ZonedDateTime dataHoraFim;

    @NotNull
    @Column(name = "path", nullable = false)
    private String path;

    @NotNull
    @Column(name = "formato", nullable = false)
    private String formato;

    @ManyToOne
    @JsonIgnoreProperties("eventos")
    private Area area;

    @ManyToOne
    @JsonIgnoreProperties("eventos")
    private Camara camara;

    @ManyToOne
    @JsonIgnoreProperties("eventos")
    private Tipoevento tipoevento;

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

    public Evento descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumPessoasPerm() {
        return numPessoasPerm;
    }

    public Evento numPessoasPerm(Integer numPessoasPerm) {
        this.numPessoasPerm = numPessoasPerm;
        return this;
    }

    public void setNumPessoasPerm(Integer numPessoasPerm) {
        this.numPessoasPerm = numPessoasPerm;
    }

    public Integer getNumPessoasDet() {
        return numPessoasDet;
    }

    public Evento numPessoasDet(Integer numPessoasDet) {
        this.numPessoasDet = numPessoasDet;
        return this;
    }

    public void setNumPessoasDet(Integer numPessoasDet) {
        this.numPessoasDet = numPessoasDet;
    }

    public ZonedDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public Evento dataHoraInicio(ZonedDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
        return this;
    }

    public void setDataHoraInicio(ZonedDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public ZonedDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public Evento dataHoraFim(ZonedDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
        return this;
    }

    public void setDataHoraFim(ZonedDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getPath() {
        return path;
    }

    public Evento path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFormato() {
        return formato;
    }

    public Evento formato(String formato) {
        this.formato = formato;
        return this;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Area getArea() {
        return area;
    }

    public Evento area(Area area) {
        this.area = area;
        return this;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Camara getCamara() {
        return camara;
    }

    public Evento camara(Camara camara) {
        this.camara = camara;
        return this;
    }

    public void setCamara(Camara camara) {
        this.camara = camara;
    }

    public Tipoevento getTipoevento() {
        return tipoevento;
    }

    public Evento tipoevento(Tipoevento tipoevento) {
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
        if (!(o instanceof Evento)) {
            return false;
        }
        return id != null && id.equals(((Evento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Evento{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", numPessoasPerm=" + getNumPessoasPerm() +
            ", numPessoasDet=" + getNumPessoasDet() +
            ", dataHoraInicio='" + getDataHoraInicio() + "'" +
            ", dataHoraFim='" + getDataHoraFim() + "'" +
            ", path='" + getPath() + "'" +
            ", formato='" + getFormato() + "'" +
            "}";
    }
}
