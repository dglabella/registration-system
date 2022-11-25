package ar.edu.unsl.fmn.gida.apis.registration.utils.messages;

public class DependencyMessages extends EntityMessages {

    public DependencyMessages(Messages lang) {
        super(lang);
    }

    @Override
    public String notFoundErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "there is no dependency with id: " + id;
                break;
            case "ES":
                ret = "no existe una dependencia con el id " + id;
                break;
            default:
                ret = "there is no dependency with id: " + id;
        }
        return ret;
    }

    @Override
    public String updateErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "cannot update dependency with id " + id + " because it doesn't exist";
                break;
            case "ES":
                ret = "no puede actualizarse la dependencia con id " + id + " porque no existe";
                break;
            default:
                ret = "cannot update dependency with id " + id + " because it doesn't exist";
        }
        return ret;
    }
}
