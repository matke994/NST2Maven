/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.konverter;

import domen.Stopapdv;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import kontroler.Kontroler;

/**
 *
 * @author Nikola
 */
@Named
@RequestScoped
@FacesConverter(value = "stopaPDVCnv")
public class StopaPDVConverter implements Converter{

    Kontroler kontroler;

    public StopaPDVConverter() {
        kontroler = new Kontroler();
    }
    
    
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        int sifraStopaPDV = Integer.parseInt(value);

        return kontroler.pronadjiStopu(sifraStopaPDV);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Stopapdv) {
            Stopapdv stopa = (Stopapdv) value;
            return stopa.getSifraStope()+ "";
        }

        return "";
    }
    
}
