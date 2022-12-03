package ar.edu.unsl.fmn.gida.apis.registration.services.messages;

public class UserMessages extends EntityMessages {

    public UserMessages(Messages lang) {
        super(lang);
    }

    public String notFoundByAccountErrorMessage(String account) {
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
}
