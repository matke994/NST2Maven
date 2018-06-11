/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nikola
 */
@Entity
@Table(name = "narudzbenica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Narudzbenica.findAll", query = "SELECT n FROM Narudzbenica n"),
    @NamedQuery(name = "Narudzbenica.findBySifraNarudzbenice", query = "SELECT n FROM Narudzbenica n WHERE n.sifraNarudzbenice = :sifraNarudzbenice"),
    @NamedQuery(name = "Narudzbenica.findByUkupnaVrednost", query = "SELECT n FROM Narudzbenica n WHERE n.ukupnaVrednost = :ukupnaVrednost"),
    @NamedQuery(name = "Narudzbenica.findByDatum", query = "SELECT n FROM Narudzbenica n WHERE n.datum = :datum")})
public class Narudzbenica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sifraNarudzbenice")
    private Integer sifraNarudzbenice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ukupnaVrednost")
    private Double ukupnaVrednost;
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @JoinColumn(name = "pravnoLice", referencedColumnName = "pib")
    @ManyToOne
    private Pravnolice pravnoLice;
    @JoinColumn(name = "radnik", referencedColumnName = "radnikID")
    @ManyToOne
    private Radnik radnik;
    @OneToMany(mappedBy = "sifraNarudzbenice")
    private List<Stavkanarudzbenice> stavkanarudzbeniceList;

    public Narudzbenica() {
    }

    public Narudzbenica(Integer sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
    }

    public Integer getSifraNarudzbenice() {
        return sifraNarudzbenice;
    }

    public void setSifraNarudzbenice(Integer sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
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

    public Pravnolice getPravnoLice() {
        return pravnoLice;
    }

    public void setPravnoLice(Pravnolice pravnoLice) {
        this.pravnoLice = pravnoLice;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    @XmlTransient
    public List<Stavkanarudzbenice> getStavkanarudzbeniceList() {
        return stavkanarudzbeniceList;
    }

    public void setStavkanarudzbeniceList(List<Stavkanarudzbenice> stavkanarudzbeniceList) {
        this.stavkanarudzbeniceList = stavkanarudzbeniceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraNarudzbenice != null ? sifraNarudzbenice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Narudzbenica)) {
            return false;
        }
        Narudzbenica other = (Narudzbenica) object;
        if ((this.sifraNarudzbenice == null && other.sifraNarudzbenice != null) || (this.sifraNarudzbenice != null && !this.sifraNarudzbenice.equals(other.sifraNarudzbenice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Narudzbenica[ sifraNarudzbenice=" + sifraNarudzbenice + " ]";
    }
    
}
