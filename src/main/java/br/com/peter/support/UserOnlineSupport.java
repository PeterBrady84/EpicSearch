package br.com.peter.support;

import br.com.peter.model.entities.Person;
import br.com.peter.util.FacesContextUtil;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ManagedBean(name = "userOnlineSupport")
@SessionScoped
public class UserOnlineSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private String login;
    SecurityContext context = SecurityContextHolder.getContext();

    public UserOnlineSupport() {
    }

    //WE CAN LEAVE OUR BACKING BEAN WIRED USING MORE JUST BELOW CODE OF PASSAGE
    public Person searchPerson() {
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                login = (((User) authentication.getPrincipal()).getUsername());
            }
        }
        Session session = FacesContextUtil.getRequestSession();
        Query query = session.createQuery("from User user where user.login like ?");
        query.setString(0, login);
        return (Person) query.uniqueResult();
    }
    
    /*IN THE FORM OF THE FOLLOWING WILL ORIGINAL DESIGN
     private User user;

    public UserOnlineSupport() {
        user = new User();
        SecurityContext context = SecurityContextHolder.getContext();
        if(context instanceof SecurityContext){
            Authentication authentication = context.getAuthentication();
            if(authentication instanceof Authentication){
                usuario.setLogin(((User) authentication.getPrincipal()).getUsername());
            }
        }
    }

    public User searchUser(){
        String login = getUserLogin();
        Session session = FacesContextUtil.getRequestSession();
        Query query = session.createQuery("from User user where user.login like ?");
        query.setString(0, login);
        return (Pessoa) query.uniqueResult();
    }

    private String getUserLogin() {
        return user.getLogin();
    }
     */
    
}
