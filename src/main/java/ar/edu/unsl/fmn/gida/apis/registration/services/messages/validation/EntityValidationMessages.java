package ar.edu.unsl.fmn.gida.apis.registration.services.messages.validation;

import ar.edu.unsl.fmn.gida.apis.registration.services.messages.Messages;

public abstract class EntityValidationMessages {

    private Messages messages;

    public EntityValidationMessages(Messages messages) {
        this.messages = messages;
    }

    public Messages getMessages() {
        return this.messages;
    }

    public String idNotRequired() {
        String ret = null;

        switch (this.messages.getLang()) {
            case "EN":
                ret = "id cannot be specified in entity";
                break;
            case "ES":
                ret = "no puede especificarse el id en la entidad";
                break;
            default:
                ret = "id cannot be specified in entity";
        }

        return ret;
    }

    public String attributeRequired(String entityName, String attributeName) {
        String ret = null;

        switch (this.messages.getLang()) {
            case "EN":
                ret = "'" + entityName + "' " + attributeName + " is required";
                break;
            case "ES":
                ret = "'" + entityName + "' " + attributeName + " es requerido";
                break;
            default:
                ret = "'" + entityName + "' " + attributeName + " is required";
        }

        return ret;
    }

    public String invalidAttributeSize(String entityName, String attributeName, int minLenght,
            int maxLenght) {
        String ret = null;

        switch (this.messages.getLang()) {
            case "EN":
                ret = "invalid " + entityName + " " + attributeName + ": must contain between "
                        + minLenght + " and " + maxLenght + " characters";
                break;
            case "ES":
                ret = entityName + " " + attributeName + " inválido: debe contener entre"
                        + minLenght + " y " + maxLenght + " caracteres";
                break;
            default:
                ret = "invalid " + entityName + " " + attributeName + ": must contain between "
                        + minLenght + " and " + maxLenght + " characters";
        }

        return ret;
    }
}
