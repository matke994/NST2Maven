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
@FacesValidator(value = "nazivValidator")
public class NazivValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String naziv = String.valueOf(value).trim();
        
        if (naziv == null || naziv.isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, component.getAttributes().get("label")+" nije unet.", "Polje za "+component.getAttributes().get("label")+" mora biti popunjeno."));
        }
        
        for (int i = 0; i < naziv.length(); i++) {
            if (!Character.isLetter(naziv.charAt(i)) && naziv.charAt(i) != ' ') {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, component.getAttributes().get("label")+" može sadržati samo slova.", naziv.charAt(i)+" nije slovo." ));
            }
        }
    }
    
}
