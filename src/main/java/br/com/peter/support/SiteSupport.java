package br.com.peter.support;

import br.com.peter.model.dao.HibernateDAO;
import br.com.peter.model.dao.InterfaceDAO;
import br.com.peter.model.entities.Site;
import br.com.peter.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="siteSupport")
@RequestScoped
public class SiteSupport  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<Site> sites;

    public List<Site> getSites() {
        InterfaceDAO<Site> siteDAO = new HibernateDAO<Site>(Site.class, FacesContextUtil.getRequestSession());
        return siteDAO.getEntities();
    }
}
