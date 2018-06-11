/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mb.konverter;

import domen.Pravnolice;
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
@FacesConverter(value = "pravnoLiceCnv")
public class PravnoLiceConverter implements Converter{

    Kontroler kontroler;

    public PravnoLiceConverter() {
        kontroler = new Kontroler();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        int pib = Integer.parseInt(value);

        return kontroler.pronadjiPravnoLice(pib);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Stopapdv) {
            Pravnolice pravnolice = (Pravnolice) value;
            return pravnolice.getPib()+ "";
        }

        return "";
    }
    
}
