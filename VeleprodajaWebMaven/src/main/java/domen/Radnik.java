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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nikola
 */
@Entity
@Table(name = "radnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Radnik.findAll", query = "SELECT r FROM Radnik r"),
    @NamedQuery(name = "Radnik.findByRadnikID", query = "SELECT r FROM Radnik r WHERE r.radnikID = :radnikID"),
    @NamedQuery(name = "Radnik.findByIme", query = "SELECT r FROM Radnik r WHERE r.ime = :ime"),
    @NamedQuery(name = "Radnik.findByPrezime", query = "SELECT r FROM Radnik r WHERE r.prezime = :prezime"),
    @NamedQuery(name = "Radnik.findByKorisnickoIme", query = "SELECT r FROM Radnik r WHERE r.korisnickoIme = :korisnickoIme"),
    @NamedQuery(name = "Radnik.findByLozinka", query = "SELECT r FROM Radnik r WHERE r.lozinka = :lozinka")})
public class Radnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "radnikID")
    private Integer radnikID;
    @Size(max = 255)
    @Column(name = "ime")
    private String ime;
    @Size(max = 255)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 255)
    @Column(name = "korisnickoIme")
    private String korisnickoIme;
    @Size(max = 255)
    @Column(name = "lozinka")
    private String lozinka;
    @OneToMany(mappedBy = "radnik")
    private List<Racun> racunList;
    @OneToMany(mappedBy = "radnik")
    private List<Narudzbenica> narudzbenicaList;

    public Radnik() {
    }

    public Radnik(Integer radnikID) {
        this.radnikID = radnikID;
    }

    public Integer getRadnikID() {
        return radnikID;
    }

    public void setRadnikID(Integer radnikID) {
        this.radnikID = radnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @XmlTransient
    public List<Racun> getRacunList() {
        return racunList;
    }

    public void setRacunList(List<Racun> racunList) {
        this.racunList = racunList;
    }

    @XmlTransient
    public List<Narudzbenica> getNarudzbenicaList() {
        return narudzbenicaList;
    }

    public void setNarudzbenicaList(List<Narudzbenica> narudzbenicaList) {
        this.narudzbenicaList = narudzbenicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (radnikID != null ? radnikID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Radnik)) {
            return false;
        }
        Radnik other = (Radnik) object;
        if ((this.radnikID == null && other.radnikID != null) || (this.radnikID != null && !this.radnikID.equals(other.radnikID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Radnik[ radnikID=" + radnikID + " ]";
    }
    
}
