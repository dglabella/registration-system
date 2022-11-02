package ar.edu.unsl.fmn.gida.apis.registration.responses;

import java.util.List;

public abstract class Page<T> {

    private int pageNumber;
    private int quantityPerPage;

    private List<T> resouces;

    public Page() {}

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

    public List<T> getResouces() {
        return this.resouces;
    }

    public void setResouces(List<T> resouces) {
        this.resouces = resouces;
    }
}
