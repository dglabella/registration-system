package ar.edu.unsl.fmn.gida.apis.registration.utils.messages;

public abstract class EntityMessages {

    private Messages messages;

    public EntityMessages(Messages messages) {
        this.messages = messages;
    }

    public Messages getMessages() {
        return this.messages;
    }

    public abstract String notFoundErrorMessage(int id);

    public abstract String updateErrorMessage(int id);
}
