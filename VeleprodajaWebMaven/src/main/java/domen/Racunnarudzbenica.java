/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nikola
 */
@Entity
@Table(name = "racunnarudzbenica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racunnarudzbenica.findAll", query = "SELECT r FROM Racunnarudzbenica r"),
    @NamedQuery(name = "Racunnarudzbenica.findByBrojRacuna", query = "SELECT r FROM Racunnarudzbenica r WHERE r.brojRacuna = :brojRacuna"),
    @NamedQuery(name = "Racunnarudzbenica.findByIznos", query = "SELECT r FROM Racunnarudzbenica r WHERE r.iznos = :iznos"),
    @NamedQuery(name = "Racunnarudzbenica.findByDatum", query = "SELECT r FROM Racunnarudzbenica r WHERE r.datum = :datum")})
public class Racunnarudzbenica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojRacuna")
    private Integer brojRacuna;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iznos")
    private Double iznos;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @JoinColumn(name = "brojRacuna", referencedColumnName = "brojRacuna", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Racun racun;

    public Racunnarudzbenica() {
    }

    public Racunnarudzbenica(Integer brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Integer getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(Integer brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
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
        if (!(object instanceof Racunnarudzbenica)) {
            return false;
        }
        Racunnarudzbenica other = (Racunnarudzbenica) object;
        if ((this.brojRacuna == null && other.brojRacuna != null) || (this.brojRacuna != null && !this.brojRacuna.equals(other.brojRacuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Racunnarudzbenica[ brojRacuna=" + brojRacuna + " ]";
    }
    
}
