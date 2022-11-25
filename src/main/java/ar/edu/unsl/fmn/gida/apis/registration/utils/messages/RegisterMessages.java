package ar.edu.unsl.fmn.gida.apis.registration.utils.messages;

public class RegisterMessages extends EntityMessages {

    public RegisterMessages(Messages lang) {
        super(lang);
    }

    @Override
    public String notFoundErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "there is no register with id: " + id;
                break;
            case "ES":
                ret = "no existe un registro con id " + id;
                break;
            default:
                ret = "there is no register with id: " + id;
        }
        return ret;
    }

    @Override
    public String updateErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "update register operation not available...";
                break;
            case "ES":
                ret = "operación de actualización de registro no habilitada...";
                break;
            default:
                ret = "update register operation not available...";
        }
        return ret;
    }

    public String dateValueSpecificationErrorMessage() {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "\"from\" date cannot be a later date than \"to\" date";
                break;
            case "ES":
                ret = "la fecha \"from\" no puede ser posterior a la fecha \"to\"";
                break;
            default:
                ret = "\"from\" date cannot be a later date than \"to\" date";
        }
        return ret;
    }

    public String dateFormatSpecificationErrorMessage() {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "date format is wrong, make sure that date format follows the right specification";
                break;
            case "ES":
                ret = "el formato de la fecha es erróneo, asegúrese que el formato de las fechas siga las especificaciones correctas";
                break;
            default:
                ret = "date format is wrong, make sure that date format follows the right specification";
        }
        return ret;
    }
}
