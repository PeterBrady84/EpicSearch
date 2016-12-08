package br.com.peter.security;

import br.com.peter.model.dao.HibernateDAO;
import br.com.peter.model.dao.InterfaceDAO;
import br.com.peter.model.entities.Person;
import br.com.peter.util.FacesContextUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("peterUserService")
public class UserServiceImpl implements UserDetailsService, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && username.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        } else {
            try {
                Person person = findUser(username);

                String login = person.getLogin();
                String password = person.getPassword();
                boolean enabled = true; //userBean.isActive()
                boolean accountNonExpired = true;//userBean.isActive()
                boolean credentialsNonExpired = true; //userBean.isActive()
                boolean accountNonLocked = true; //userBean.isActive()

                Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                //Use if you are using Spring Security 3.0.5.RELEASE
                authorities.add(new GrantedAuthorityImpl(person.getPermission()));
                //In the version 3.1.3.RELEASE this class has been deprecated and you should use as the excerpt below
                //authorities.add(new SimpleGrantedAuthority(user.getPermissao()));
                User user = new User(
                        login,
                        password,
                        enabled,
                        accountNonExpired,
                        credentialsNonExpired,
                        accountNonLocked,
                        authorities);
                return user;
            } catch (Exception e) {
                return null;
            }
        }

    }

    public Person findUser(String login) {
        String stringQuery = "from Person person where person.login = "+ login;
//        return personDAO().getEntityByHQLQuery(stringQuery);
        Session session = FacesContextUtil.getRequestSession();
        Query query = session.createQuery(stringQuery);
        //query.setString(0, login);
        return (Person) query.uniqueResult();
    }
    
    private InterfaceDAO<Person> personDAO() {
        InterfaceDAO<Person> personDAO = new HibernateDAO<Person>(Person.class, FacesContextUtil.getRequestSession());
        return personDAO;
    }
}