package ar.edu.unsl.fmn.gida.apis.registration.model.auxiliaries;

import java.time.LocalDateTime;

public class Check {

    private Integer accessId;
    private String encryptedData;
    private LocalDateTime time;

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

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
