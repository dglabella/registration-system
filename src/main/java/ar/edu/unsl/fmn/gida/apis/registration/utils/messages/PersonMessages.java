package ar.edu.unsl.fmn.gida.apis.registration.utils.messages;

public class PersonMessages extends EntityMessages {

    public PersonMessages(Messages messages) {
        super(messages);
    }

    @Override
    public String notFoundErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "there is no person with id: " + id;
                break;
            case "ES":
                ret = "no existe una persona con id: " + id;
                break;
            default:
                ret = "there is no person with id: " + id;
        }
        return ret;
    }

    @Override
    public String updateErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "cannot update person with id " + id + " because it doesn't exist";
                break;
            case "ES":
                ret = "no puede actualizarse la persona con id: " + id + " porque no existe";
                break;
            default:
                ret = "cannot update person with id " + id + " because it doesn't exist";
        }
        return ret;
    }

    public String notFoundByDniErrorMessage(String dni) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "there is no person with dni: " + dni;
                break;
            case "ES":
                ret = "no existe una persona con dni: " + dni;
                break;
            default:
                ret = "there is no person with dni: " + dni;
        }
        return ret;
    }
}
