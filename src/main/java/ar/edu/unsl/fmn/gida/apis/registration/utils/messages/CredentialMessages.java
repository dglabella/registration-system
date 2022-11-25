package ar.edu.unsl.fmn.gida.apis.registration.utils.messages;

public class CredentialMessages extends EntityMessages {

    public CredentialMessages(Messages lang) {
        super(lang);
    }

    @Override
    public String notFoundErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "there is no credential with id: " + id;
                break;
            case "ES":
                ret = "no existe una credencial con el id " + id;
                break;
            default:
                ret = "there is no credential with id: " + id;
        }
        return ret;
    }

    @Override
    public String updateErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "cannot update credential with id " + id + " because it doesn't exist";
                break;
            case "ES":
                ret = "no puede actualizarse la credencial con id: " + id + " porque no existe";
                break;
            default:
                ret = "cannot update credential with id " + id + " because it doesn't exist";
        }
        return ret;
    }

    public String notFoundByPersonIdErrorMessage(int personId) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "FATAL ERROR: database integrity may be corrupted; person " + personId
                        + " has not a credential";
                break;
            case "ES":
                ret = "FATAL ERROR: no existe credencial para la persona con id: " + personId;
                break;
            default:
                ret = "FATAL ERROR: database integrity may be corrupted; person " + personId
                        + " has not a credential";
        }
        return ret;
    }
}
