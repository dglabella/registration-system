package ar.edu.unsl.fmn.gida.apis.registration.utils.cypher;

import org.springframework.security.crypto.keygen.KeyGenerators;

public interface CypherProperties {
    // String PASSWORD = "159753zseqsc";
    String PASSWORD = "$B&E)H@McQfTjWnZq4t7w!z%C*F-JaNd";
    String SALT = KeyGenerators.string().generateKey();
}
