package ar.edu.unsl.fmn.gida.apis.registration.responses;

import java.util.List;
import ar.edu.unsl.fmn.gida.apis.registration.model.Person;

public class PersonPage {
    private int pageNumber;
    private int quantityPerPage;

    private List<Person> persons;

    public PersonPage() {}

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getQuantityPerPage() {
        return this.quantityPerPage;
    }

    public void setQuantityPerPage(int quantityPerPage) {
        this.quantityPerPage = quantityPerPage;
    }

    public List<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
