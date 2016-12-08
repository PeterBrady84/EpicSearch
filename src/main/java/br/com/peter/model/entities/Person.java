package br.com.peter.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.ForeignKey;


@Entity
@Table (name="person")
public class Person implements Serializable {
    
    private static final long serialVersionUID =  1L;
    
    @Id
    @GeneratedValue
    @Column(name="PersonId", nullable=false)
    private Integer personId;
    @Column (name="First_Name", nullable = false, length = 80 )
    private String firstName;
    @Column (name="Last_Name", nullable = false, length = 80 )
    private String lastName;
	@Column (name="DateOfBirth", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    @Column (name="Email", nullable = false, length = 80 )
    private String email;
        
    @Column(name = "Login", unique=true, length = 25)
    private String login;
    @Column(name = "Password", length = 40)
    private String password;
    @Column(name = "Permission", length = 36)
    private String permission;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="PersonSite")
    private PersonSite personSite;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	
	public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
	
	public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonSite getPersonSite() {
        return personSite;
    }

    public void setPersonSite(PersonSite personSite) {
        this.personSite = personSite;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
         
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.personId != null ? this.personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.personId != other.personId && (this.personId == null || !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }
             
}
