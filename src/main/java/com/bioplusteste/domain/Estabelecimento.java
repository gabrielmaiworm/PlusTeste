package com.bioplusteste.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Estabelecimento.
 */
@Entity
@Table(name = "estabelecimento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Estabelecimento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "id_local")
    private Integer idLocal;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_1")
    private String phone1;

    @Column(name = "place_id")
    private String placeId;

    @Column(name = "types")
    private String types;

    @Column(name = "validated_by")
    private String validatedBy;

    @Column(name = "vicinity")
    private String vicinity;

    @Column(name = "vaga")
    private String vaga;

    @Column(name = "foto")
    private String foto;

    @Column(name = "nota")
    private Double nota;

    @Column(name = "lat_2_lng_2")
    private String lat2lng2;

    @Column(name = "has_chair_wheel")
    private String hasChairWheel;

    @Column(name = "has_handicapped_help")
    private String hasHandicappedHelp;

    @Column(name = "has_interpreter")
    private String hasInterpreter;

    @Column(name = "has_special_furniture")
    private String hasSpecialFurniture;

    @OneToMany(mappedBy = "estabelecimento")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "estabelecimento", "infoAdicional" }, allowSetters = true)
    private Set<Avaliacao> avaliacaos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Estabelecimento id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdLocal() {
        return this.idLocal;
    }

    public Estabelecimento idLocal(Integer idLocal) {
        this.setIdLocal(idLocal);
        return this;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public Estabelecimento objectId(String objectId) {
        this.setObjectId(objectId);
        return this;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return this.name;
    }

    public Estabelecimento name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public Estabelecimento phone1(String phone1) {
        this.setPhone1(phone1);
        return this;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public Estabelecimento placeId(String placeId) {
        this.setPlaceId(placeId);
        return this;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getTypes() {
        return this.types;
    }

    public Estabelecimento types(String types) {
        this.setTypes(types);
        return this;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getValidatedBy() {
        return this.validatedBy;
    }

    public Estabelecimento validatedBy(String validatedBy) {
        this.setValidatedBy(validatedBy);
        return this;
    }

    public void setValidatedBy(String validatedBy) {
        this.validatedBy = validatedBy;
    }

    public String getVicinity() {
        return this.vicinity;
    }

    public Estabelecimento vicinity(String vicinity) {
        this.setVicinity(vicinity);
        return this;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getVaga() {
        return this.vaga;
    }

    public Estabelecimento vaga(String vaga) {
        this.setVaga(vaga);
        return this;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public String getFoto() {
        return this.foto;
    }

    public Estabelecimento foto(String foto) {
        this.setFoto(foto);
        return this;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getNota() {
        return this.nota;
    }

    public Estabelecimento nota(Double nota) {
        this.setNota(nota);
        return this;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getLat2lng2() {
        return this.lat2lng2;
    }

    public Estabelecimento lat2lng2(String lat2lng2) {
        this.setLat2lng2(lat2lng2);
        return this;
    }

    public void setLat2lng2(String lat2lng2) {
        this.lat2lng2 = lat2lng2;
    }

    public String getHasChairWheel() {
        return this.hasChairWheel;
    }

    public Estabelecimento hasChairWheel(String hasChairWheel) {
        this.setHasChairWheel(hasChairWheel);
        return this;
    }

    public void setHasChairWheel(String hasChairWheel) {
        this.hasChairWheel = hasChairWheel;
    }

    public String getHasHandicappedHelp() {
        return this.hasHandicappedHelp;
    }

    public Estabelecimento hasHandicappedHelp(String hasHandicappedHelp) {
        this.setHasHandicappedHelp(hasHandicappedHelp);
        return this;
    }

    public void setHasHandicappedHelp(String hasHandicappedHelp) {
        this.hasHandicappedHelp = hasHandicappedHelp;
    }

    public String getHasInterpreter() {
        return this.hasInterpreter;
    }

    public Estabelecimento hasInterpreter(String hasInterpreter) {
        this.setHasInterpreter(hasInterpreter);
        return this;
    }

    public void setHasInterpreter(String hasInterpreter) {
        this.hasInterpreter = hasInterpreter;
    }

    public String getHasSpecialFurniture() {
        return this.hasSpecialFurniture;
    }

    public Estabelecimento hasSpecialFurniture(String hasSpecialFurniture) {
        this.setHasSpecialFurniture(hasSpecialFurniture);
        return this;
    }

    public void setHasSpecialFurniture(String hasSpecialFurniture) {
        this.hasSpecialFurniture = hasSpecialFurniture;
    }

    public Set<Avaliacao> getAvaliacaos() {
        return this.avaliacaos;
    }

    public void setAvaliacaos(Set<Avaliacao> avaliacaos) {
        if (this.avaliacaos != null) {
            this.avaliacaos.forEach(i -> i.setEstabelecimento(null));
        }
        if (avaliacaos != null) {
            avaliacaos.forEach(i -> i.setEstabelecimento(this));
        }
        this.avaliacaos = avaliacaos;
    }

    public Estabelecimento avaliacaos(Set<Avaliacao> avaliacaos) {
        this.setAvaliacaos(avaliacaos);
        return this;
    }

    public Estabelecimento addAvaliacao(Avaliacao avaliacao) {
        this.avaliacaos.add(avaliacao);
        avaliacao.setEstabelecimento(this);
        return this;
    }

    public Estabelecimento removeAvaliacao(Avaliacao avaliacao) {
        this.avaliacaos.remove(avaliacao);
        avaliacao.setEstabelecimento(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Estabelecimento)) {
            return false;
        }
        return id != null && id.equals(((Estabelecimento) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Estabelecimento{" +
            "id=" + getId() +
            ", idLocal=" + getIdLocal() +
            ", objectId='" + getObjectId() + "'" +
            ", name='" + getName() + "'" +
            ", phone1='" + getPhone1() + "'" +
            ", placeId='" + getPlaceId() + "'" +
            ", types='" + getTypes() + "'" +
            ", validatedBy='" + getValidatedBy() + "'" +
            ", vicinity='" + getVicinity() + "'" +
            ", vaga='" + getVaga() + "'" +
            ", foto='" + getFoto() + "'" +
            ", nota=" + getNota() +
            ", lat2lng2='" + getLat2lng2() + "'" +
            ", hasChairWheel='" + getHasChairWheel() + "'" +
            ", hasHandicappedHelp='" + getHasHandicappedHelp() + "'" +
            ", hasInterpreter='" + getHasInterpreter() + "'" +
            ", hasSpecialFurniture='" + getHasSpecialFurniture() + "'" +
            "}";
    }
}
