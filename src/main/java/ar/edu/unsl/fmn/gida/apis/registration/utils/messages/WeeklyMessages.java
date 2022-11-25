package ar.edu.unsl.fmn.gida.apis.registration.utils.messages;

public class WeeklyMessages extends EntityMessages {

    public WeeklyMessages(Messages lang) {
        super(lang);
    }

    @Override
    public String notFoundErrorMessage(int id) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "there is no weekly with id: " + id;
                break;
            case "ES":
                ret = "no existe un semanario con id " + id;
                break;
            default:
                ret = "there is no weekly with id: " + id;
        }
        return ret;
    }

    @Override
    public String updateErrorMessage(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getCurrentWeeklyErrorMessage(int personId) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "FATAL ERROR: database integrity may be corrupted; person " + personId
                        + " has not a current weekly";
                break;
            case "ES":
                ret = "ERROR FATAL: la integridad de la base de datos puede estar corrupta; la persona "
                        + personId + " no tiene semanario actual";
                break;
            default:
                ret = "FATAL ERROR: database integrity may be corrupted; person " + personId
                        + " has not a current weekly";
        }
        return ret;
    }

    public String wrongWeeklyDatetimeErrorMessage() {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "cannot insert/update a new weekly with start datetime before the current clock";
                break;
            case "ES":
                ret = "No es posible insertar/actualizar un semanario con fecha de inicio anterior al día de hoy inclusive";
                break;
            default:
                ret = "cannot insert/update a new weekly with start datetime before the current clock";
        }
        return ret;
    }
}
