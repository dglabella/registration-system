package ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic;

import ar.edu.unsl.fmn.gida.apis.registration.services.messages.Messages;

public class CredentialBusinessLogicMessages extends EntityBusinessLogicMessages {

    public CredentialBusinessLogicMessages(Messages lang) {
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
