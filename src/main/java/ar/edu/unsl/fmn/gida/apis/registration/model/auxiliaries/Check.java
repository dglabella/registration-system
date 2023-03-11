package ar.edu.unsl.fmn.gida.apis.registration.model.auxiliaries;

public class Check {

    private Integer accessId;
    private String encryptedData;

    public Integer getAccessId() {
        return this.accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getEncryptedData() {
        return this.encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }
}
