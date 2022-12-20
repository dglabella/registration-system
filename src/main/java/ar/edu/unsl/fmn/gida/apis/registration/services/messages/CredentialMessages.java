package ar.edu.unsl.fmn.gida.apis.registration.services.messages;

public class CredentialMessages extends EntityMessages {

    public CredentialMessages(Messages lang) {
        super(lang);
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
