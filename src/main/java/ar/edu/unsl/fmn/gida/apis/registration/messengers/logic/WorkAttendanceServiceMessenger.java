package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import java.time.LocalDate;
import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class WorkAttendanceServiceMessenger extends ServiceMessenger {

    public WorkAttendanceServiceMessenger(Messenger messenger) {
        super(messenger);
    }

    public String notFoundByWeeklyIdAndDate(Integer weeklyId, LocalDate date) {
        String ret = null;

        switch (this.getMessenger().getLang()) {
            case "EN":
                ret = "there is no work attendance from weekly " + weeklyId + " with date " + date;
                break;
            case "ES":
                ret = "no existe presentismo del semanario " + weeklyId + " con fecha " + date;
                break;
            default:
                ret = "there is no work attendance from weekly " + weeklyId + " with date " + date;
        }

        return ret;
    }

    public String crossDate() {
        String ret = null;

		switch (this.getMessenger().getLang()) {
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

    public String dateFormatSpecificationError() {
		String ret = null;

		switch (this.getMessenger().getLang()) {
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
