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
@Table(name = "stavkaracuna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavkaracuna.findAll", query = "SELECT s FROM Stavkaracuna s"),
    @NamedQuery(name = "Stavkaracuna.findBySifraStavke", query = "SELECT s FROM Stavkaracuna s WHERE s.sifraStavke = :sifraStavke"),
    @NamedQuery(name = "Stavkaracuna.findByKolicina", query = "SELECT s FROM Stavkaracuna s WHERE s.kolicina = :kolicina"),
    @NamedQuery(name = "Stavkaracuna.findByJm", query = "SELECT s FROM Stavkaracuna s WHERE s.jm = :jm"),
    @NamedQuery(name = "Stavkaracuna.findByCena", query = "SELECT s FROM Stavkaracuna s WHERE s.cena = :cena"),
    @NamedQuery(name = "Stavkaracuna.findByStopaPDV", query = "SELECT s FROM Stavkaracuna s WHERE s.stopaPDV = :stopaPDV"),
    @NamedQuery(name = "Stavkaracuna.findByVrednostBezPDV", query = "SELECT s FROM Stavkaracuna s WHERE s.vrednostBezPDV = :vrednostBezPDV"),
    @NamedQuery(name = "Stavkaracuna.findByVrednostSaPDV", query = "SELECT s FROM Stavkaracuna s WHERE s.vrednostSaPDV = :vrednostSaPDV")})
public class Stavkaracuna implements Serializable {

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
    @Column(name = "vrednostBezPDV")
    private Double vrednostBezPDV;
    @Column(name = "vrednostSaPDV")
    private Double vrednostSaPDV;
    @JoinColumn(name = "proizvod", referencedColumnName = "proizvodID")
    @ManyToOne
    private Proizvod proizvod;
    @JoinColumn(name = "sifraRacuna", referencedColumnName = "brojRacuna")
    @ManyToOne
    private Racun sifraRacuna;

    public Stavkaracuna() {
    }

    public Stavkaracuna(Integer sifraStavke) {
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

    public Double getVrednostBezPDV() {
        return vrednostBezPDV;
    }

    public void setVrednostBezPDV(Double vrednostBezPDV) {
        this.vrednostBezPDV = vrednostBezPDV;
    }

    public Double getVrednostSaPDV() {
        return vrednostSaPDV;
    }

    public void setVrednostSaPDV(Double vrednostSaPDV) {
        this.vrednostSaPDV = vrednostSaPDV;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public Racun getSifraRacuna() {
        return sifraRacuna;
    }

    public void setSifraRacuna(Racun sifraRacuna) {
        this.sifraRacuna = sifraRacuna;
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
        if (!(object instanceof Stavkaracuna)) {
            return false;
        }
        Stavkaracuna other = (Stavkaracuna) object;
        if ((this.sifraStavke == null && other.sifraStavke != null) || (this.sifraStavke != null && !this.sifraStavke.equals(other.sifraStavke))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Stavkaracuna[ sifraStavke=" + sifraStavke + " ]";
    }
    
}
