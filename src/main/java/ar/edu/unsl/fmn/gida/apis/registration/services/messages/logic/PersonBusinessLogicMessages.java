package ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic;

import ar.edu.unsl.fmn.gida.apis.registration.services.messages.Messages;

public class PersonBusinessLogicMessages extends EntityBusinessLogicMessages {

    public PersonBusinessLogicMessages(Messages messages) {
        super(messages);
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
