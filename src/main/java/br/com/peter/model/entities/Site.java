package br.com.peter.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="site")
public class Site implements Serializable{
    
    private static final long serialVersionUID =  1L; 
    
    @Id
    @GeneratedValue
    @Column(name="SiteId", nullable=false)
    private Integer siteId;
	
    @Column(name="Name", length=80, nullable=false)
    private String name;
	
	@Column(name="Url", length=80, nullable=false)
    private String url;
	
	@Column(name="Active", length=80, nullable=false)
    private int active;

    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
    @ForeignKey(name="PersonSite")
    private List<PersonSite> personSites;
    
    public Site() {
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<PersonSite> getPersonSites() {
        return personSites;
    }

    public void setPersonSites(List<PersonSite> personSites) {
        this.personSites = personSites;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.siteId != null ? this.siteId.hashCode() : 0);
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
        final Site other = (Site) obj;
        if (this.siteId != other.siteId && (this.siteId == null || !this.siteId.equals(other.siteId))) {
            return false;
        }
        return true;
    }
    
}
