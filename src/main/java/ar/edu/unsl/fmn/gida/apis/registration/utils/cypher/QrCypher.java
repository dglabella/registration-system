package ar.edu.unsl.fmn.gida.apis.registration.utils.cypher;

// import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class QrCypher implements Cypher {

	// private StrongTextEncryptor encryptor;
	private TextEncryptor encryptor;

	public QrCypher() {
		// this.encryptor = new StrongTextEncryptor();
		// this.encryptor.setPassword("159753zseqsc");
		StringKeyGenerator stringKeyGenerator = KeyGenerators.string();
		this.encryptor = Encryptors.text("159753zseqsc", stringKeyGenerator.generateKey());
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
