package ar.edu.unsl.fmn.gida.apis.registration.model;

public class Person {
    private int id;
    private String lastName;
    private String name;
    private int dni;

    private boolean active;

    public Person() {

    }

    public int getId() {

        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDni() {
        return this.dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
