/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Proizvod;
import domen.Radnik;
import domen.Stopapdv;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import report.Jasper;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import kontroler.Kontroler;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nikola
 */
@Named(value = "mBProizvodi")
@SessionScoped
public class MBProizvodi implements Serializable {

    @Inject
    Kontroler kontroler;

    private Proizvod proizvodUnos;
    private List<Proizvod> sviProizvodi;
    private Proizvod proizvodIzmena;
    private List<Proizvod> filterProizvodi;
    private List<Stopapdv> sveStope;

    /**
     * Creates a new instance of MBProizvodi
     */
    public MBProizvodi() {
//        proizvodUnos = new Proizvod();
        filterProizvodi = new ArrayList<>();
    }

    @PostConstruct
    public void ucitajSveProizvode() {
        try {
            sviProizvodi = kontroler.ucitajProizvode();
            sveStope = kontroler.ucitajStope();
        } catch (Exception ex) {
            Logger.getLogger(MBProizvodi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pripremiFormu(ComponentSystemEvent event) {
        proizvodUnos = new Proizvod();
    }

    public void sacuvajProizvod() {
        if (proizvodUnos != null) {
            try {
                kontroler.sacuvajProizvod(proizvodUnos);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistem je zapamtio novi proizvod", ""));
                proizvodUnos = new Proizvod();
                ucitajProizvode();
            } catch (Exception e) {
                System.out.println("Nece da se cuva proizvod");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da zapamti novi proizvod", ""));
                e.printStackTrace();
            }

        }

    }

    public void izmeniProizvod() {
        try {
            kontroler.izmeniProizvod(proizvodIzmena);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Uspešno sačuvano", "Proizovd " + proizvodIzmena.getNaziv() + " je uspešno sačuvan."));

            proizvodIzmena = new Proizvod();

            ucitajProizvode();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", e.getMessage()));
            e.printStackTrace();
        }

    }

    public String prikaziProizvod() {
        return "/izmeniProizvod.xhtml";
    }

    public String izbrisiProizvod() {
        if (proizvodIzmena != null) {
            try {
                kontroler.izbrisiProizvod(proizvodIzmena);

                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Uspešno brisanje", "Proizvod " + proizvodIzmena.getNaziv() + " je uspešno izbrisan."));

                proizvodIzmena = new Proizvod();
                sviProizvodi = kontroler.ucitajProizvode();
            } catch (Exception e) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", e.getMessage()));
                e.printStackTrace();
            }
        }

        return "";
    }
//    
//    
//    
//

    private void ucitajProizvode() {
        try {
            sviProizvodi = kontroler.ucitajProizvode();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška", e.getMessage()));
            e.printStackTrace();
        }
    }

    public Proizvod getProizvodIzmena() {
        return proizvodIzmena;
    }

    public Proizvod getProizvodUnos() {
        return proizvodUnos;
    }

    public List<Proizvod> getSviProizvodi() {
        return sviProizvodi;
    }

    public void setProizvodIzmena(Proizvod proizvodIzmena) {
        this.proizvodIzmena = proizvodIzmena;
    }

    public void setProizvodUnos(Proizvod proizvodUnos) {
        this.proizvodUnos = proizvodUnos;
    }

    public void setSviProizvodi(List<Proizvod> sviProizvodi) {
        this.sviProizvodi = sviProizvodi;
    }

    public List<Proizvod> getFilterProizvodi() {
        return filterProizvodi;
    }

    public void setFilterProizvodi(List<Proizvod> filterProizvodi) {
        this.filterProizvodi = filterProizvodi;
    }

    public List<Stopapdv> getSveStope() {
        return sveStope;
    }

    public void setSveStope(List<Stopapdv> sveStope) {
        this.sveStope = sveStope;
    }

    public void ispisi() {

        if (filterProizvodi.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne može da nađe proizvode po zadatoj vrednosti!", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistem je našao proizvode po zadatoj vrednosti!", ""));
        }
    }

    public void onload() throws Exception {
        sviProizvodi = kontroler.ucitajProizvode();
    }


    public List<Proizvod> vratiProizvode() throws Exception {
        return kontroler.ucitajProizvode();
    }

    JasperPrint jasperPrint;

    public void init() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(sviProizvodi);
        jasperPrint = JasperFillManager.fillReport("C:\\Users\\nikola.matic\\Documents\\NetBeansProjects\\VeleprodajaRestMaven\\src\\main\\java\\resources\\report1.jasper", new HashMap(), beanCollectionDataSource);
    }

    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        init();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

    }

}
