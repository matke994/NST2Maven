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
@Table(name = "stopapdv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stopapdv.findAll", query = "SELECT s FROM Stopapdv s"),
    @NamedQuery(name = "Stopapdv.findBySifraStope", query = "SELECT s FROM Stopapdv s WHERE s.sifraStope = :sifraStope"),
    @NamedQuery(name = "Stopapdv.findByIznos", query = "SELECT s FROM Stopapdv s WHERE s.iznos = :iznos"),
    @NamedQuery(name = "Stopapdv.findByOznaka", query = "SELECT s FROM Stopapdv s WHERE s.oznaka = :oznaka")})
public class Stopapdv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sifraStope")
    private Integer sifraStope;
    @Column(name = "iznos")
    private Integer iznos;
    @Size(max = 255)
    @Column(name = "oznaka")
    private String oznaka;
    @OneToMany(mappedBy = "stopaPDV")
    private List<Proizvod> proizvodList;

    public Stopapdv() {
    }

    public Stopapdv(Integer sifraStope) {
        this.sifraStope = sifraStope;
    }

    public Integer getSifraStope() {
        return sifraStope;
    }

    public void setSifraStope(Integer sifraStope) {
        this.sifraStope = sifraStope;
    }

    public Integer getIznos() {
        return iznos;
    }

    public void setIznos(Integer iznos) {
        this.iznos = iznos;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    @XmlTransient
    public List<Proizvod> getProizvodList() {
        return proizvodList;
    }

    public void setProizvodList(List<Proizvod> proizvodList) {
        this.proizvodList = proizvodList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraStope != null ? sifraStope.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stopapdv)) {
            return false;
        }
        Stopapdv other = (Stopapdv) object;
        if ((this.sifraStope == null && other.sifraStope != null) || (this.sifraStope != null && !this.sifraStope.equals(other.sifraStope))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Stopapdv[ sifraStope=" + sifraStope + " ]";
    }
    
}
