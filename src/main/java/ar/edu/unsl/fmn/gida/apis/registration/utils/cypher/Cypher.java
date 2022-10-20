package ar.edu.unsl.fmn.gida.apis.registration.utils.cypher;

public interface Cypher {

    String encrypt(String data);

    String decrypt(String encryptedData);
}
