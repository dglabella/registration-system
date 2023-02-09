package ar.edu.unsl.fmn.gida.apis.registration.utils.cypher;

public interface Cypher {

	/**
	 * This method encrypts the data using the underlying implementation.
	 * <p>
	 * ES: Este método encripta el dato usando la implementación subyacente.
	 * 
	 * @param data is the data to be encrypted.
	 *        <p>
	 *        ES: es el dato a ser encriptado.
	 * @return the encrypted data.
	 *         <p>
	 *         ES: El dato encriptado.
	 */
	String encrypt(String data);

	/**
	 * This method decrypts the data using the underlying implementation.
	 * <p>
	 * ES: Este método desencripta el dato usando la implementación subyacente.
	 * 
	 * @param encryptedData is the data to be decrypted.
	 *        <p>
	 *        ES: <b>encryptedData</b> es el dato a ser desencriptado.
	 * @return the decrypted data.
	 *         <p>
	 *         ES: El dato desencriptado.
	 */
	String decrypt(String encryptedData);
}
