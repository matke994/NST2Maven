/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Narudzbenica;
import domen.Pravnolice;
import domen.Proizvod;
import domen.Stavkanarudzbenice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.swing.text.html.parser.DTDConstants;
import kontroler.Kontroler;

/**
 *
 * @author Nikola
 */
@ManagedBean(name = "mbNarudzbenica")
@SessionScoped
public class MBNarudzbenica implements Serializable {

    @Inject
    Kontroler kontroler;
    private Narudzbenica narudzbenicaUnos;
    private Narudzbenica narudzbenicaIzmena;
    private List<Narudzbenica> sveNarudzbenice;
    private Stavkanarudzbenice izabranaStavka;
    private List<Pravnolice> pravnaLica;
    private List<String> jediniceMere;
    private List<Narudzbenica> filterNarudzbenice;
    
    
    

    public MBNarudzbenica() {
        narudzbenicaUnos = new Narudzbenica();
        narudzbenicaIzmena = new Narudzbenica();
        izabranaStavka = new Stavkanarudzbenice();
        sveNarudzbenice = new ArrayList<>();
        filterNarudzbenice = new ArrayList<>();
        popuniJediniceMere();
    }

    @PostConstruct
    public void ucitajSveProizvode() {
        try {
            pravnaLica = kontroler.ucitajPravnaLica();
        } catch (Exception ex) {
            Logger.getLogger(MBProizvodi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Narudzbenica> getFilterNarudzbenice() {
        return filterNarudzbenice;
    }

    public void setFilterNarudzbenice(List<Narudzbenica> filterNarudzbenice) {
        this.filterNarudzbenice = filterNarudzbenice;
    }

    public List<Pravnolice> getPravnaLica() {
        return pravnaLica;
    }

    public void setPravnaLica(List<Pravnolice> pravnaLica) {
        this.pravnaLica = pravnaLica;
    }

    public void setJediniceMere(List<String> jediniceMere) {
        this.jediniceMere = jediniceMere;
    }

    public List<String> getJediniceMere() {
        return jediniceMere;
    }

    public Narudzbenica getNarudzbenicaUnos() {
        return narudzbenicaUnos;
    }

    public void setNarudzbenicaUnos(Narudzbenica narudzbenicaUnos) {
        this.narudzbenicaUnos = narudzbenicaUnos;
    }

    public Narudzbenica getNarudzbenicaIzmena() {
        return narudzbenicaIzmena;
    }

    public void setNarudzbenicaIzmena(Narudzbenica narudzbenicaIzmena) {
        this.narudzbenicaIzmena = narudzbenicaIzmena;
    }

    public List<Narudzbenica> getSveNarudzbenice() {
        return sveNarudzbenice;
    }

    public void setSveNarudzbenice(List<Narudzbenica> sveNarudzbenice) {
        this.sveNarudzbenice = sveNarudzbenice;
    }

    public Stavkanarudzbenice getIzabranaStavka() {
        return izabranaStavka;
    }

    public void setIzabranaStavka(Stavkanarudzbenice izabranaStavka) {
        this.izabranaStavka = izabranaStavka;
    }

    public void dodajStavku() {

        System.out.println("dodajem stavku:" + izabranaStavka.getProizvod().getNaziv());

        if (izabranaStavka.getProizvod() == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali proizvod", ""));
            return;
        }
        Stavkanarudzbenice sr = null;
        for (Stavkanarudzbenice stavkanarudzbenice : narudzbenicaUnos.getStavkanarudzbeniceList()) {
            if (stavkanarudzbenice.getProizvod().equals(izabranaStavka.getProizvod())) {
                sr = stavkanarudzbenice;
                break;
            }
        }

        if (sr == null) {
            Stavkanarudzbenice novaStavka = new Stavkanarudzbenice();
            setujStavku(novaStavka);

        } else {
            int kolicina = sr.getKolicina();
            sr.setKolicina(sr.getKolicina() + izabranaStavka.getKolicina());
            int stanje = Integer.parseInt(izabranaStavka.getProizvod().getStanje()) - kolicina;
            izabranaStavka.getProizvod().setStanje(Integer.toString(stanje));
        }

        narudzbenicaUnos.izracunajUkupno();

        izabranaStavka.setProizvod(null);
        izabranaStavka.setJm(null);
        izabranaStavka.setKolicina(0);
        izabranaStavka.setVrednost(0.0);

    }

    public void obrisiStavku() {

        if (izabranaStavka == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali stavku", ""));
            return;
        }
        System.out.println("izabrani proizvod:" + izabranaStavka.getProizvod().getNaziv());
        for (int i = 0; i < narudzbenicaUnos.getStavkanarudzbeniceList().size(); i++) {
            if (narudzbenicaUnos.getStavkanarudzbeniceList().get(i).getProizvod().getProizvodID() == izabranaStavka.getProizvod().getProizvodID()) {
                narudzbenicaUnos.getStavkanarudzbeniceList().remove(i);
                break;
            }
        }
        narudzbenicaUnos.izracunajUkupno();
    }

    public void sacuvajNarudzbenicu() {
//        System.out.println(narudzbenicaUnos.getPravnoLice().getNazivPravnogLica());
        if (narudzbenicaUnos != null) {
            try {
                narudzbenicaUnos.setStavkanarudzbeniceList(narudzbenicaUnos.getStavkanarudzbeniceList());
                narudzbenicaIzmena.setStavkanarudzbeniceList(narudzbenicaUnos.getStavkanarudzbeniceList());
                kontroler.sacuvajNarudzbenicu(narudzbenicaUnos);

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Uspešno sačuvano", "Narudzbenica  je uspešno sačuvana."));

                narudzbenicaUnos = new Narudzbenica();
                onload();
            } catch (Exception e) {
                System.out.println("Nece da se cuva narudzbenica");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", e.getMessage()));
                e.printStackTrace();
            }

        }
    }

    

    private void setujStavku(Stavkanarudzbenice novaStavka) {
        int kolicina = izabranaStavka.getKolicina();
        System.out.println(kolicina);
        int iznosStopePDV = izabranaStavka.getProizvod().getStopaPDV().getIznos();
        novaStavka.setSifraNarudzbenice(narudzbenicaUnos);
        novaStavka.setProizvod(izabranaStavka.getProizvod());
        novaStavka.setSifraStavke(narudzbenicaUnos.getStavkanarudzbeniceList().size() + 1);
        novaStavka.setVrednost(izabranaStavka.getProizvod().getCena() * kolicina * (100 + iznosStopePDV) / 100);
        novaStavka.setKolicina(izabranaStavka.getKolicina());
        novaStavka.setCena(izabranaStavka.getProizvod().getCena());
        novaStavka.setStopaPDV(iznosStopePDV);
        novaStavka.setJm(izabranaStavka.getJm());
        narudzbenicaUnos.getStavkanarudzbeniceList().add(novaStavka);
        narudzbenicaIzmena.getStavkanarudzbeniceList().add(novaStavka);
        int stanje = Integer.parseInt(izabranaStavka.getProizvod().getStanje()) - kolicina;
        izabranaStavka.getProizvod().setStanje(Integer.toString(stanje));
    }

    public void onload() throws Exception {
        sveNarudzbenice = kontroler.ucitajNarudzbine();
    }

    public void ispisi() {

        if (filterNarudzbenice.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da nađe narudžbenice po zadatoj vrednosti!", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistem je našao narudžbenice po zadatoj vrednosti!", ""));
        }
    }

    public void obrisiNarudzbenicu() {

        if (narudzbenicaIzmena != null) {
            try {
                kontroler.izbrisiNarudzbenicu(narudzbenicaIzmena);

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Uspešno brisanje", "Narudžbenica je uspešno izbrisana."));

                narudzbenicaIzmena = new Narudzbenica();
                sveNarudzbenice = kontroler.ucitajNarudzbine();
            } catch (Exception e) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", e.getMessage()));
                e.printStackTrace();
            }
        }

    }

   

    private void popuniJediniceMere() {
        jediniceMere = new ArrayList<>();
        jediniceMere.add("kom");
        jediniceMere.add("pakovanje");
    }

    public String kreirajRacun() {
       
        return "/unosRacunNarudzbenica.xhtml";

    }

}
