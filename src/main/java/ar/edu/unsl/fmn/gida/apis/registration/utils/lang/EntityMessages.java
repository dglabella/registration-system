package ar.edu.unsl.fmn.gida.apis.registration.utils.lang;

public abstract class EntityMessages {

    private Language language;

    public EntityMessages(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public abstract String getByIdErrorMessage(int id);

    public abstract String updateErrorMessage(int id);
}
