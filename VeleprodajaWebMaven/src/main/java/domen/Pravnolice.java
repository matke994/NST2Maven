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
@Table(name = "pravnolice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pravnolice.findAll", query = "SELECT p FROM Pravnolice p"),
    @NamedQuery(name = "Pravnolice.findByPib", query = "SELECT p FROM Pravnolice p WHERE p.pib = :pib"),
    @NamedQuery(name = "Pravnolice.findByNazivPravnogLica", query = "SELECT p FROM Pravnolice p WHERE p.nazivPravnogLica = :nazivPravnogLica"),
    @NamedQuery(name = "Pravnolice.findByMaticniBroj", query = "SELECT p FROM Pravnolice p WHERE p.maticniBroj = :maticniBroj"),
    @NamedQuery(name = "Pravnolice.findByAdresa", query = "SELECT p FROM Pravnolice p WHERE p.adresa = :adresa"),
    @NamedQuery(name = "Pravnolice.findByKontakt", query = "SELECT p FROM Pravnolice p WHERE p.kontakt = :kontakt")})
public class Pravnolice implements Serializable {

    @OneToMany(mappedBy = "pravnoLice")
    private List<Narudzbenica> narudzbenicaList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pib")
    private Integer pib;
    @Size(max = 255)
    @Column(name = "nazivPravnogLica")
    private String nazivPravnogLica;
    @Size(max = 255)
    @Column(name = "maticniBroj")
    private String maticniBroj;
    @Size(max = 255)
    @Column(name = "adresa")
    private String adresa;
    @Size(max = 255)
    @Column(name = "kontakt")
    private String kontakt;

    public Pravnolice() {
    }

    public Pravnolice(Integer pib) {
        this.pib = pib;
    }

    public Integer getPib() {
        return pib;
    }

    public void setPib(Integer pib) {
        this.pib = pib;
    }

    public String getNazivPravnogLica() {
        return nazivPravnogLica;
    }

    public void setNazivPravnogLica(String nazivPravnogLica) {
        this.nazivPravnogLica = nazivPravnogLica;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pib != null ? pib.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pravnolice)) {
            return false;
        }
        Pravnolice other = (Pravnolice) object;
        if ((this.pib == null && other.pib != null) || (this.pib != null && !this.pib.equals(other.pib))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Pravnolice[ pib=" + pib + " ]";
    }

    @XmlTransient
    public List<Narudzbenica> getNarudzbenicaList() {
        return narudzbenicaList;
    }

    public void setNarudzbenicaList(List<Narudzbenica> narudzbenicaList) {
        this.narudzbenicaList = narudzbenicaList;
    }
    
}
