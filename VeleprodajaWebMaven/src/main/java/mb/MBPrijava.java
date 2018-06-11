/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Radnik;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import kontroler.Kontroler;

/**
 *
 * @author Nikola
 */
@ManagedBean(name = "mbPrijava")
@SessionScoped
public class MBPrijava implements Serializable {

    @Inject
    Kontroler kontroler;
    private Radnik radnik;
    private boolean ulogovan;

    public MBPrijava() {
        radnik = new Radnik();
        ulogovan = false;
//        radnik.setKorisnickoIme("");
//        radnik.setLozinka("");
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }

    public boolean isUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(boolean ulogovan) {
        this.ulogovan = ulogovan;
    }

    public String prijaviRadnika() {

        Radnik r = kontroler.proveriRadnika(radnik);

        if (r != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno logovanje", "Uspešno ste prijavljeni."));
            radnik = r;
            ulogovan = true;
            return "/index.xhtml";
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška u logovanju", "Sistem ne može da nađe radnika sa tim podacima."));
        return "";
    }

    public String odjaviRadnika() {
        radnik = new Radnik();
        ulogovan = false;
        return "/login.xhtml";
    }

    public void daLiJeUlogovan(ComponentSystemEvent event) {
        if (!ulogovan) {
            FacesContext fc = FacesContext.getCurrentInstance();

            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();

            nav.performNavigation("accessDenied");
        }
    }

}
