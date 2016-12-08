package br.com.peter.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validator responsible for ensuring that the email
 * entered is a valid email
 */
@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator{

        @Override
	public void validate(FacesContext facesContext, 
        UIComponent uIComponent, Object object) throws ValidatorException {
        
        String enteredEmail = (String)object;
        //Set the email pattern string
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        
        //Match the given string with the pattern
        Matcher m = p.matcher(enteredEmail);
        
        //Check whether match is found
        boolean matchFound = m.matches();
        
        if (!matchFound) {
            FacesMessage message = new FacesMessage();
            message.setDetail("E-mail incorrect!");
            message.setSummary("E-mail incorrect!");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}