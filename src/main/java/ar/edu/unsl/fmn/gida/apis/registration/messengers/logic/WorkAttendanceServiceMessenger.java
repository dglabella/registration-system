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
}
