package ar.edu.unsl.fmn.gida.apis.registration.utils.messages;

public abstract class EntityMessages {

    private Messages messages;

    public EntityMessages(Messages messages) {
        this.messages = messages;
    }

    public Messages getMessages() {
        return this.messages;
    }

    public String notFoundErrorMessage(String entityName, int id) {
        String ret = null;

        switch (this.messages.getLang()) {
            case "EN":
                ret = "there is no \"" + entityName + "\" with id: " + id;
                break;
            case "ES":
                ret = "no existe \"" + entityName + "\" con id " + id;
                break;
            default:
                ret = "there is no \"" + entityName + "\" with id: " + id;
        }
        return ret;
    }

    public String updateNonExistentEntityErrorMessage(String entityName, int id) {
        String ret = null;

        switch (this.messages.getLang()) {
            case "EN":
                ret = "cannot update \"" + entityName + "\" with id " + id
                        + " because it doesn't exist";
                break;
            case "ES":
                ret = "no puede actualizarse \"" + entityName + "\" con id " + id
                        + " porque no existe";
                break;
            default:
                ret = "cannot update \"" + entityName + "\" with id " + id
                        + " because it doesn't exist";
        }
        return ret;
    }

    public String constraintsErrorMessage(String entityName, String attributeName, String value) {
        String ret = null;

        switch (this.messages.getLang()) {
            case "EN":
                ret = "cannot insert/update \"" + entityName + "\". " + attributeName + " " + value
                        + " already exist";
                break;
            case "ES":
                ret = "no puede insertarse/actualizarse \"" + entityName + "\". " + attributeName
                        + " " + value + " ya existe";
                break;
            default:
                ret = "cannot insert/update \"" + entityName + "\". " + attributeName + " " + value
                        + " already exist";
        }
        return ret;
    }
}
