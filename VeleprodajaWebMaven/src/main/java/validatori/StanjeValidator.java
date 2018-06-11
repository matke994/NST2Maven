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
@FacesValidator(value = "stanjeValidator")
public class StanjeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String stanje = String.valueOf(value).trim();

        if (stanje == null || stanje.isEmpty()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stanje nije uneta.", "Polje za stanje mora biti popunjeno."));
        }

        for (int i = 0; i < stanje.length(); i++) {
            if (!Character.isDigit(stanje.charAt(i))) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stanje mora sadržati samo cifre.", "'" + stanje.charAt(i) + "' nije cifra."));
            }
        }
        if (value != null) {
            int broj = Integer.valueOf(stanje);
            if (broj <= 0) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stanje mora biti veće od nule.", ""));

            }
        }
    }

}
