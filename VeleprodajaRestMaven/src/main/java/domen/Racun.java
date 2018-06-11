/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nikola
 */
@Entity
@Table(name = "racun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racun.findAll", query = "SELECT r FROM Racun r"),
    @NamedQuery(name = "Racun.findByBrojRacuna", query = "SELECT r FROM Racun r WHERE r.brojRacuna = :brojRacuna"),
    @NamedQuery(name = "Racun.findByUkupnaVrednost", query = "SELECT r FROM Racun r WHERE r.ukupnaVrednost = :ukupnaVrednost"),
    @NamedQuery(name = "Racun.findByDatum", query = "SELECT r FROM Racun r WHERE r.datum = :datum")})
public class Racun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojRacuna")
    private Integer brojRacuna;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ukupnaVrednost")
    private Double ukupnaVrednost;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "racun")
    private Racunnarudzbenica racunnarudzbenica;
    @OneToMany(mappedBy = "sifraRacuna")
    private List<Stavkaracuna> stavkaracunaList;
    @JoinColumn(name = "radnik", referencedColumnName = "radnikID")
    @ManyToOne
    private Radnik radnik;

    public Racun() {
        stavkaracunaList = new ArrayList<>();
    }

    public Racun(Integer brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Integer getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(Integer brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Double getUkupnaVrednost() {
        return ukupnaVrednost;
    }

    public void setUkupnaVrednost(Double ukupnaVrednost) {
        this.ukupnaVrednost = ukupnaVrednost;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Racunnarudzbenica getRacunnarudzbenica() {
        return racunnarudzbenica;
    }

    public void setRacunnarudzbenica(Racunnarudzbenica racunnarudzbenica) {
        this.racunnarudzbenica = racunnarudzbenica;
    }

    @XmlTransient
    public List<Stavkaracuna> getStavkaracunaList() {
        return stavkaracunaList;
    }

    public void setStavkaracunaList(List<Stavkaracuna> stavkaracunaList) {
        this.stavkaracunaList = stavkaracunaList;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brojRacuna != null ? brojRacuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Racun)) {
            return false;
        }
        Racun other = (Racun) object;
        if ((this.brojRacuna == null && other.brojRacuna != null) || (this.brojRacuna != null && !this.brojRacuna.equals(other.brojRacuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Racun[ brojRacuna=" + brojRacuna + " ]";
    }
    
}
