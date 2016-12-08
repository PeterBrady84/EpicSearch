package br.com.peter.validators;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validator responsible for ensuring that the chosen password
 *  contains numbers, special characters, letters and
 *  has at least 7 and a maximum of 22 characters 
*/

@FacesValidator(value="passwordValidator")
public class PasswordValidator implements Validator {

    int num = 0;
    int chars = 0;

    //Sets an array of special characters
    char[] specialCharacters={'=','|','!','@','#','$','%','^','&','*','(',')','{','}','[',']',';',':','.',',','<','>','?','~','+','-','_','\'','"'};
    
    @Override
    public void validate(FacesContext context,UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }      
        
        String password = (String)value;
        
        /* Assemble an array of characters with password content
		 * and runs through them counting how many numbers the password has
		*/        
        for(int i=0;i<password.length();i++) {            
            if(password.charAt(i)>=48 && password.charAt(i)<=57) {
                num++;
            }
        }
        System.out.println("Found "+num+" numbers in the password!");                                        
        
        /* Assemble an array of characters with password content
		 * and runs through them counting how many numbers the password has
		*/ 
        for(int i=0;i<password.length();i++) {
            for(int j=0;j<specialCharacters.length;j++) {
                if(password.charAt(i)==specialCharacters[j]) {
                    chars++;
                }
            }
        }
        System.out.println("Found "+chars+" characters in the password!");
        
        //Verifies that the password is not empty
        if(password == null || password.equals("")){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "The password can not be null!", ""));             
             
        //Verifies that found at least one number and one special character
        } else if(!(chars > 0)|| !(num > 0)){            
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "The password must contain numbers, special characters and letters!", ""));
             
        //Verifies that the password has at least 7 characters and a maximum of 22
        } else if(password.length() < 7){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "The password must be at least 7 characters!", ""));
        } else if(password.length() >= 22){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "The password must be at most 22 characters !!!", ""));
        } 
    }
}
