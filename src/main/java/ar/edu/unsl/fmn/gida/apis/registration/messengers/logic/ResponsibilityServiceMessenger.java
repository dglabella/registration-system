package ar.edu.unsl.fmn.gida.apis.registration.messengers.logic;

import ar.edu.unsl.fmn.gida.apis.registration.messengers.Messenger;

public class ResponsibilityServiceMessenger extends ServiceMessenger {

    public ResponsibilityServiceMessenger(Messenger messenger) {
        super(messenger);
    }

    public String crossTimes() {
        String ret = null;

        switch (this.getMessenger().getLang()) {
            case "EN":
                ret = "cannot insert a new responsibility with cross times";
                break;
            case "ES":
                ret = "No es posible insertar una responsabilidad con horarios cruzadas";
                break;
            default:
                ret = "cannot insert a new responsibility with cross times";
        }

        return ret;
    }

    public String overlappedTimes() {
        String ret = null;

        switch (this.getMessenger().getLang()) {
            case "EN":
                ret = "cannot insert a new responsibility with overlapped times with other responsibilities from the same day";
                break;
            case "ES":
                ret = "No es posible insertar una responsabilidad nueva que tenga horarios superpuestos con otras responsabilidades del mismo dia";
                break;
            default:
                ret = "cannot insert a new responsibility with overlapped times with other responsibilities from the same day";
        }

        return ret;
    }
}
