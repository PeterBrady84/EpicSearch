package br.com.peter.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="personSite")
public class PersonSite implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    @Column(name="PersonSiteId", nullable=false)
    private Integer personSiteId;
    
    @ManyToOne(optional=false, fetch= FetchType.LAZY)
    @ForeignKey(name="PersonSitePerson")
    @JoinColumn(name = "PersonId", referencedColumnName = "PersonId")
    private Person person;
    
    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    @ForeignKey(name="PersonSiteSite")
    @JoinColumn(name = "SiteId", referencedColumnName="SiteId")
    private Site site;   

    public PersonSite() {
        this.site = new Site();
        this.person = new Person();
    }    
    
    public Integer getPersonSiteId() {
        return personSiteId;
    }

    public void setPersonSiteId(Integer personSiteId) {
        this.personSiteId = personSiteId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.personSiteId != null ? this.personSiteId.hashCode() : 0);
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
        final PersonSite other = (PersonSite) obj;
        if (this.personSiteId != other.personSiteId && (this.personSiteId == null || !this.personSiteId.equals(other.personSiteId))) {
            return false;
        }
        return true;
    }
        
}
