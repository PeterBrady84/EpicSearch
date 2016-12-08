package br.com.peter.security;

import br.com.peter.model.entities.Person;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assembler")
public class Assembler {

    public Assembler() {
    }

    @Transactional(readOnly = true)
    User buildUserFromUserEntity(Person person) {

        String username = person.getLogin();
        String password = person.getPassword();
        boolean enabled = true; //userBean.isActive()
        boolean accountNonExpired = true;//userBean.isActive()
        boolean credentialsNonExpired = true; //userBean.isActive()
        boolean accountNonLocked = true; //userBean.isActive()

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl(person.getPermission()));      

        User user = new User(
                username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        return user;
    }
}