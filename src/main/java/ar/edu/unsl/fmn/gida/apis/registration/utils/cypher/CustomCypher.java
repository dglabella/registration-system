package ar.edu.unsl.fmn.gida.apis.registration.utils.cypher;

import org.jasypt.util.text.StrongTextEncryptor;

public class CustomCypher implements Cypher {

    private StrongTextEncryptor encryptor;

    public CustomCypher() {
        this.encryptor = new StrongTextEncryptor();
        this.encryptor.setPassword("159753zseqsc");
    }

    @Override
    public String encrypt(String data) {
        return this.encryptor.encrypt(data);
    }

    @Override
    public String decrypt(String encryptedData) {
        return this.encryptor.decrypt(encryptedData);
    }
}
