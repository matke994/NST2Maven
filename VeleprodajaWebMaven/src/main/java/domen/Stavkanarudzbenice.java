/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nikola
 */
@Entity
@Table(name = "stavkanarudzbenice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavkanarudzbenice.findAll", query = "SELECT s FROM Stavkanarudzbenice s"),
    @NamedQuery(name = "Stavkanarudzbenice.findBySifraStavke", query = "SELECT s FROM Stavkanarudzbenice s WHERE s.sifraStavke = :sifraStavke"),
    @NamedQuery(name = "Stavkanarudzbenice.findByKolicina", query = "SELECT s FROM Stavkanarudzbenice s WHERE s.kolicina = :kolicina"),
    @NamedQuery(name = "Stavkanarudzbenice.findByJm", query = "SELECT s FROM Stavkanarudzbenice s WHERE s.jm = :jm"),
    @NamedQuery(name = "Stavkanarudzbenice.findByCena", query = "SELECT s FROM Stavkanarudzbenice s WHERE s.cena = :cena"),
    @NamedQuery(name = "Stavkanarudzbenice.findByStopaPDV", query = "SELECT s FROM Stavkanarudzbenice s WHERE s.stopaPDV = :stopaPDV"),
    @NamedQuery(name = "Stavkanarudzbenice.findByVrednost", query = "SELECT s FROM Stavkanarudzbenice s WHERE s.vrednost = :vrednost")})
public class Stavkanarudzbenice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sifraStavke")
    private Integer sifraStavke;
    @Column(name = "kolicina")
    private Integer kolicina;
    @Size(max = 255)
    @Column(name = "jm")
    private String jm;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cena")
    private Double cena;
    @Column(name = "stopaPDV")
    private Integer stopaPDV;
    @Column(name = "vrednost")
    private Double vrednost;
    @JoinColumn(name = "proizvod", referencedColumnName = "proizvodID")
    @ManyToOne
    private Proizvod proizvod;
    @JoinColumn(name = "sifraNarudzbenice", referencedColumnName = "sifraNarudzbenice")
    @ManyToOne
    private Narudzbenica sifraNarudzbenice;

    public Stavkanarudzbenice() {
    }

    public Stavkanarudzbenice(Integer sifraStavke) {
        this.sifraStavke = sifraStavke;
    }

    public Integer getSifraStavke() {
        return sifraStavke;
    }

    public void setSifraStavke(Integer sifraStavke) {
        this.sifraStavke = sifraStavke;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getJm() {
        return jm;
    }

    public void setJm(String jm) {
        this.jm = jm;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Integer getStopaPDV() {
        return stopaPDV;
    }

    public void setStopaPDV(Integer stopaPDV) {
        this.stopaPDV = stopaPDV;
    }

    public Double getVrednost() {
        return vrednost;
    }

    public void setVrednost(Double vrednost) {
        this.vrednost = vrednost;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public Narudzbenica getSifraNarudzbenice() {
        return sifraNarudzbenice;
    }

    public void setSifraNarudzbenice(Narudzbenica sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraStavke != null ? sifraStavke.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stavkanarudzbenice)) {
            return false;
        }
        Stavkanarudzbenice other = (Stavkanarudzbenice) object;
        if ((this.sifraStavke == null && other.sifraStavke != null) || (this.sifraStavke != null && !this.sifraStavke.equals(other.sifraStavke))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Stavkanarudzbenice[ sifraStavke=" + sifraStavke + " ]";
    }
    
}
