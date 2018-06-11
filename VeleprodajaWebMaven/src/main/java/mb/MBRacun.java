/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Narudzbenica;
import domen.Racun;
import domen.Stavkanarudzbenice;
import domen.Stavkaracuna;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import kontroler.Kontroler;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Nikola
 */
@ManagedBean(name = "mbRacun")
@SessionScoped
public class MBRacun implements Serializable {

    @Inject
    Kontroler kontroler;
    private Racun racunUnos;
    private List<Racun> sviRacuni;
    private Stavkaracuna izabranaStavka;
    private List<String> jediniceMere;
    private Racun racunIzmena;
    private List<Racun> filterRacuni;

    public MBRacun() {
        racunUnos = new Racun();
        izabranaStavka = new Stavkaracuna();
        jediniceMere = new ArrayList<>();
        jediniceMere.add("kom");
        jediniceMere.add("pakovanje");
        
        filterRacuni = new ArrayList<>();
    }

    public void setRacunIzmena(Racun racunIzmena) {
        this.racunIzmena = racunIzmena;
    }

    public Racun getRacunIzmena() {
        return racunIzmena;
    }

    public Racun getRacunUnos() {
        return racunUnos;
    }

    public void setRacunUnos(Racun racunUnos) {
        this.racunUnos = racunUnos;
    }

    public List<Racun> getSviRacuni() {
        return sviRacuni;
    }

    public void setSviRacuni(List<Racun> sviRacuni) {
        this.sviRacuni = sviRacuni;
    }

    public Stavkaracuna getIzabranaStavka() {
        return izabranaStavka;
    }

    public void setIzabranaStavka(Stavkaracuna izabranaStavka) {
        this.izabranaStavka = izabranaStavka;
    }

    public List<String> getJediniceMere() {
        return jediniceMere;
    }

    public void setJediniceMere(List<String> jediniceMere) {
        this.jediniceMere = jediniceMere;
    }

    public void setFilterRacuni(List<Racun> filterRacuni) {
        this.filterRacuni = filterRacuni;
    }

    public List<Racun> getFilterRacuni() {
        return filterRacuni;
    }

    public void dodajStavku() {

        System.out.println("dodajem stavku:" + izabranaStavka.getProizvod().getNaziv());

        if (izabranaStavka.getProizvod() == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali proizvod", ""));
            return;
        }
        Stavkaracuna sr = null;
        for (Stavkaracuna stavkaRacuna : racunUnos.getStavkaracunaList()) {
            if (stavkaRacuna.getProizvod().equals(izabranaStavka.getProizvod())) {
                sr = stavkaRacuna;
                break;
            }
        }

        if (sr == null) {
            Stavkaracuna novaStavka = new Stavkaracuna();
            setujStavku(novaStavka);

        } else {
            sr.setKolicina(sr.getKolicina() + izabranaStavka.getKolicina());
        }

        racunUnos.izracunajUkupno();

        izabranaStavka.setProizvod(null);
        izabranaStavka.setJm(null);
        izabranaStavka.setKolicina(0);
        izabranaStavka.setVrednostSaPDV(0.0);

    }

    public void obrisiStavku() {

        if (izabranaStavka == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali stavku", ""));
            return;
        }
        System.out.println("izabrani proizvod:" + izabranaStavka.getProizvod().getNaziv());
        for (int i = 0; i < racunUnos.getStavkaracunaList().size(); i++) {
            if (racunUnos.getStavkaracunaList().get(i).getProizvod().getProizvodID() == izabranaStavka.getProizvod().getProizvodID()) {
                racunUnos.getStavkaracunaList().remove(i);
                break;
            }
        }
        racunUnos.izracunajUkupno();
    }

    public void sacuvajRacun() {
        if (racunUnos != null) {
            try {
                kontroler.sacuvajRacun(racunUnos);

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Uspešno sačuvano", "Račun je uspešno sačuvan."));

                racunUnos = new Racun();
                ucitajRacune();
            } catch (Exception e) {
                System.out.println("Nece da se cuva racun");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", e.getMessage()));
                e.printStackTrace();
            }

        }
    }

    private void setujStavku(Stavkaracuna novaStavka) {
        int kolicina = izabranaStavka.getKolicina();
        System.out.println(kolicina);
        int iznosStopePDV = izabranaStavka.getProizvod().getStopaPDV().getIznos();
        novaStavka.setSifraRacuna(racunUnos);
        novaStavka.setProizvod(izabranaStavka.getProizvod());
        novaStavka.setSifraStavke(racunUnos.getStavkaracunaList().size() + 1);
        novaStavka.setVrednostSaPDV(izabranaStavka.getProizvod().getCena() * kolicina * (100 + iznosStopePDV) / 100);
        novaStavka.setKolicina(izabranaStavka.getKolicina());
        novaStavka.setCena(izabranaStavka.getProizvod().getCena());
        novaStavka.setStopaPDV(iznosStopePDV);
        novaStavka.setJm(izabranaStavka.getJm());
        racunUnos.getStavkaracunaList().add(novaStavka);
    }

    private void ucitajRacune() {
       try {
            sviRacuni = kontroler.ucitajRacune();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", e.getMessage()));
            e.printStackTrace();
        }
    }
    
     public void storniraj(){
        System.out.println("storniram");
    }

    public void onload() throws Exception {
        sviRacuni = kontroler.ucitajRacune();
    }
    
    public void ispisi() {

        if (filterRacuni.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da nađe račune po zadatoj vrednosti!", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistem je našao račune po zadatoj vrednosti!", ""));
        }
    }
    
   

    JasperPrint jasperPrint;

    public void init() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(sviRacuni);
        jasperPrint = JasperFillManager.fillReport("C:\\Users\\nikola.matic\\Documents\\NetBeansProjects\\VeleprodajaRestMaven\\src\\main\\java\\resources\\report2.jasper", new HashMap(), beanCollectionDataSource);
    }

    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

    }

}
