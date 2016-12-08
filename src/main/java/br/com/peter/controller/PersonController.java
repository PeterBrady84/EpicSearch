package br.com.peter.controller;

import br.com.peter.convertor.ConverterSHA1;
import br.com.peter.model.dao.HibernateDAO;
import br.com.peter.model.dao.InterfaceDAO;
import br.com.peter.model.entities.PersonSite;
import br.com.peter.model.entities.Person;
import br.com.peter.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class PersonController implements Serializable {

    private static final long serialVersionUID = 1L;
    private String confirmPassword;
    private Person person = new Person();
    private PersonSite personSite = new PersonSite();
    private List<Person> persons;
    private List<PersonSite> personSites;

    public PersonController() {
    	System.out.println("personController-personController");
    }

    private InterfaceDAO<Person> personDAO() {
    	System.out.println("personController-personDAO");
        InterfaceDAO<Person> personDAO = new HibernateDAO<Person>(Person.class, FacesContextUtil.getRequestSession());
        System.out.println("personController-personDAO END");
        return personDAO;
    }

    private InterfaceDAO<PersonSite> personSiteDAO() {
    	System.out.println("personController-personSiteDAO");
        InterfaceDAO<PersonSite> personSiteDAO = new HibernateDAO<PersonSite>(PersonSite.class, FacesContextUtil.getRequestSession());
        return personSiteDAO;
    }

    public String implPerson() {
    	System.out.println("personController-implPerson");
    	person = new Person();
        personSite = new PersonSite();
        return editPerson();
    }

    public String editPerson() {
        return "/restrict/registerperson.faces";
    }

    public String addPerson() {
        if (person.getPersonId() == null || person.getPersonId() == 0) {
            insertPerson();
        } else {
            updatePerson();
        }
        return null;
    }

    private void insertPerson() {
        person.setPassword(ConverterSHA1.cipher(person.getPassword()));
        if (person.getPassword() == null ? confirmPassword == null : person.getPassword().equals(ConverterSHA1.cipher(confirmPassword))) {
            person.setPermission("ROLE_USER");
            personDAO().save(person);
//            personSite.setPerson(person);
//            personSiteDAO().save(personSite);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Person Added Successfully", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Passwords do not match", ""));
        }
    }

    private void updatePerson() {
        personDAO().update(person);
        //personSiteDAO().update(personSite);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Person Updated Successfully", ""));
    }

    public String deletePerson() {
        personDAO().remove(person);
        personSiteDAO().remove(personSite);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Deleted Person", ""));
        return null;
    }

    public List<Person> getPersons() {
    	System.out.println("personController-getPersons");
        persons = personDAO().getEntities();
        System.out.println("personController-getPersons END : "+persons);
        return persons;
    }

    public void setPersons(List<Person> persons) {
    	System.out.println("personController-setPersons");
        this.persons = persons;
    }

    public List<PersonSite> getPersonSites() {
    	System.out.println("personController-getPersonSites");
        personSites = personSiteDAO().getEntities();
        return personSites;
    }

    public void setPersonSites(List<PersonSite> personSites) {
    	System.out.println("personController-setPersonSites");
        this.personSites = personSites;
    }

    public Person getPerson() {
    	System.out.println("personController-getPerson");
        return person;
    }

    public void setPerson(Person person) {
    	System.out.println("personController-setPerson");
        this.person = person;
    }

    public PersonSite getPersonSite() {
    	System.out.println("personController-getPersonSite");
        return personSite;
    }

    public void setPersonSite(PersonSite personSite) {
    	System.out.println("personController-setPersonSite");
        this.personSite = personSite;
    }

    public String getConfirmPassword() {
    	System.out.println("personController-getConfirmPassword");
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
    	System.out.println("personController-setConfirmPassword");
        this.confirmPassword = confirmPassword;
    }
}
