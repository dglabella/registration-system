package ar.edu.unsl.fmn.gida.apis.registration.services.messages.logic;

import ar.edu.unsl.fmn.gida.apis.registration.services.messages.Messages;

public class UserBusinessLogicMessages extends EntityBusinessLogicMessages {

    public UserBusinessLogicMessages(Messages lang) {
        super(lang);
    }

    public String notFoundByAccount(String account) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "there is no user with account name '" + account + "'";
                break;
            case "ES":
                ret = "no existe usuario con el nombre de cuenta '" + account + "'";
                break;
            default:
                ret = "there is no user with account name '" + account + "'";
        }

        return ret;
    }

    public String updateAccessViolation(String account) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "the user " + account + " cannot update data from other user";
                break;
            case "ES":
                ret = "el usuario " + account + " no puede cambiar datos de otro usuario";
                break;
            default:
                ret = "the user " + account + " cannot update data from other user";
        }

        return ret;
    }

    public String userPrivilegeIntegrityCorruption(String account) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "the user " + account
                        + " has no privilege assigned. Data integrity corrupted or exist a failure loading privileges";
                break;
            case "ES":
                ret = "el usuario " + account
                        + " no tiene privilegio asignado. La integridad de los datos esta corrupta o existe una falla en la caraga de los privilegios";
                break;
            default:
                ret = "the user " + account
                        + " has no privilege assigned. Data integrity corrupted or exist a failure loading privileges";
        }

        return ret;
    }

    public String accesssViolation(String account) {
        String ret = null;

        switch (this.getMessages().getLang()) {
            case "EN":
                ret = "the user " + account + " is not registered. Access Violation";
                break;
            case "ES":
                ret = "el usuario " + account + " no esta registrado. Violaci??n de acceso";
                break;
            default:
                ret = "the user " + account + " is not registered. Access Violation";
        }

        return ret;
    }
}
