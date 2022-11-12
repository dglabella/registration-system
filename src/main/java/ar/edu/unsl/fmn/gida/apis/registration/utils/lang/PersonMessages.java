package ar.edu.unsl.fmn.gida.apis.registration.utils.lang;

public class PersonMessages extends EntityMessages {

    public PersonMessages(Language lang) {
        super(lang);
    }

    @Override
    public String getByIdErrorMessage(int id) {
        String ret = null;

        switch (this.getLanguage().getLang()) {
            case "EN":
                ret = "there is no person with id: " + id;
                break;
            case "ES":
                ret = "no existe una persona con id: " + id;
                break;
            default:
                ret = "there is no person with id: " + id;
        }
        return ret;
    }

    @Override
    public String updateErrorMessage(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getByDniErrorMessage(String dni) {
        String ret = null;

        switch (this.getLanguage().getLang()) {
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
