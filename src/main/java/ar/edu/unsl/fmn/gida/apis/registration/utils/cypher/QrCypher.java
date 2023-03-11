package ar.edu.unsl.fmn.gida.apis.registration.utils.cypher;

// import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class QrCypher implements Cypher {

	// private StrongTextEncryptor encryptor;
	private TextEncryptor encryptor;

	public QrCypher() {
		// this.encryptor = new StrongTextEncryptor();
		// this.encryptor.setPassword("159753zseqsc");
		this.encryptor = Encryptors.text(CypherProperties.PASSWORD, CypherProperties.SALT);
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
