package ar.edu.unsl.fmn.gida.apis.registration.utils.messages;

public class AccessMessages extends EntityMessages {

    public AccessMessages(Messages lang) {
        super(lang);
    }

    @Override
    public String notFoundErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "there is no access with id: " + id;
                break;
            case "ES":
                ret = "no existe un acceso con id: " + id;
                break;
            default:
                ret = "there is no access with id: " + id;
        }
        return ret;
    }

    @Override
    public String updateErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "cannot update access with id " + id + " because it doesn't exist";
                break;
            case "ES":
                ret = "no puede actualizarse el acceso con id: " + id + " porque no existe";
                break;
            default:
                ret = "cannot update access with id " + id + " because it doesn't exist";
        }
        return ret;
    }
}
