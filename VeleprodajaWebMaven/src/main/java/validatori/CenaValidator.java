/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatori;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author Nikola
 */
@Named
@ApplicationScoped
@FacesValidator(value = "cenaValidator")
public class CenaValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String cena = String.valueOf(value).trim();
        
        if (cena == null || cena.isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cena nije uneta.", "Polje za cena mora biti popunjeno."));
        }
        
        
        for (int i = 0; i < cena.length(); i++) {
            if (!Character.isDigit(cena.charAt(i))) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cena mora sadržati samo cifre.", "'"+cena.charAt(i)+"' nije cifra."));
            }
        }
    }
    
}
