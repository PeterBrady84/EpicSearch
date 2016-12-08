package br.com.peter.controller;

import br.com.peter.model.dao.HibernateDAO;
import br.com.peter.model.dao.InterfaceDAO;
import br.com.peter.model.entities.Site;
import br.com.peter.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "siteController")
@RequestScoped
public class SiteController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Site site = new Site();
    private List<Site> sites;

    public SiteController() {
    }

    private InterfaceDAO<Site> siteDAO() {
        InterfaceDAO<Site> siteDAO = new HibernateDAO<Site>(Site.class, FacesContextUtil.getRequestSession());
        return siteDAO;
    }

    public String implSite() {
        site = new Site();
        return editSite();
    }

    public String editSite() {
        return "/restrict/register.faces";
    }

    public String addSite() {
        if (site.getSiteId() == null || site.getSiteId() == 0) {
            insertSite();
        } else {
            updateSite();
        }
        implSite();
        return null;
    }

    private void insertSite() {
        siteDAO().save(site);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Site Added Successfully", ""));
    }

    private void updateSite() {
        siteDAO().update(site);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Site Updated Successfully", ""));
    }
    
    public void deleteSite(){
        siteDAO().remove(site);        
    }
    
    public List<Site> getSites() {       
        sites = siteDAO().getEntities();
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
        
}
