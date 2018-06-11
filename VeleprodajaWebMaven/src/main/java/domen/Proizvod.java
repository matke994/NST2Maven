/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nikola
 */
@Entity
@Table(name = "proizvod")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proizvod.findAll", query = "SELECT p FROM Proizvod p"),
    @NamedQuery(name = "Proizvod.findByProizvodID", query = "SELECT p FROM Proizvod p WHERE p.proizvodID = :proizvodID"),
    @NamedQuery(name = "Proizvod.findByNaziv", query = "SELECT p FROM Proizvod p WHERE p.naziv = :naziv"),
    @NamedQuery(name = "Proizvod.findByStanje", query = "SELECT p FROM Proizvod p WHERE p.stanje = :stanje"),
    @NamedQuery(name = "Proizvod.findByCena", query = "SELECT p FROM Proizvod p WHERE p.cena = :cena"),
    @NamedQuery(name = "Proizvod.findByOpis", query = "SELECT p FROM Proizvod p WHERE p.opis = :opis")})
public class Proizvod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proizvodID")
    private Integer proizvodID;
    @Size(max = 255)
    @Column(name = "naziv")
    private String naziv;
    @Size(max = 255)
    @Column(name = "stanje")
    private String stanje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cena")
    private Double cena;
    @Size(max = 255)
    @Column(name = "opis")
    private String opis;
    @OneToMany(mappedBy = "proizvod")
    private List<Stavkaracuna> stavkaracunaList;
    @JoinColumn(name = "stopaPDV", referencedColumnName = "sifraStope")
    @ManyToOne
    private Stopapdv stopaPDV;
    @OneToMany(mappedBy = "proizvod")
    private List<Stavkanarudzbenice> stavkanarudzbeniceList;

    public Proizvod() {
    }

    public Proizvod(Integer proizvodID) {
        this.proizvodID = proizvodID;
    }

    public Integer getProizvodID() {
        return proizvodID;
    }

    public void setProizvodID(Integer proizvodID) {
        this.proizvodID = proizvodID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @XmlTransient
    public List<Stavkaracuna> getStavkaracunaList() {
        return stavkaracunaList;
    }

    public void setStavkaracunaList(List<Stavkaracuna> stavkaracunaList) {
        this.stavkaracunaList = stavkaracunaList;
    }

    public Stopapdv getStopaPDV() {
        return stopaPDV;
    }

    public void setStopaPDV(Stopapdv stopaPDV) {
        this.stopaPDV = stopaPDV;
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
        hash += (proizvodID != null ? proizvodID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proizvod)) {
            return false;
        }
        Proizvod other = (Proizvod) object;
        if ((this.proizvodID == null && other.proizvodID != null) || (this.proizvodID != null && !this.proizvodID.equals(other.proizvodID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Proizvod[ proizvodID=" + proizvodID + " ]";
    }
    
}
