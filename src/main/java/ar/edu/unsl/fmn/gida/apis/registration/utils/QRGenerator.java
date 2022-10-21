package ar.edu.unsl.fmn.gida.apis.registration.utils;

import java.io.File;
import net.glxn.qrgen.QRCode;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import net.glxn.qrgen.image.ImageType;
import org.jasypt.util.text.StrongTextEncryptor;

public class QRGenerator {
    // ------------------------------ Fields ------------------------------
    private String outPutPath;

    public QRGenerator() {}

    // ------------------------------ Getters & Setters ------------------------------

    // ------------------------------ Public Methods ------------------------------
    /**
     * EN: This method generates the QR code in a JPG image. The location where the QR code will be
     * storaged can be setted using the setPath method. ----- ES: Este metodo genera el codigo QR en
     * una imagen JPG. El directorio donde se almacenara el codigo QR puede ser configurado usando
     * el metodo setPath.
     *
     * @param fileName EN: The desired fileName (without extension). ----- ES: El nombre de archivo
     *        deseado (sin extension).
     * @param data EN: The data to be encrypted. ----- ES: El dato a ser encriptado.
     *
     */
    public void generate(String fileName, String data) {
        String encryptedData = this.encryptor.encrypt(data);
        ByteArrayOutputStream out =
                QRCode.from(encryptedData).to(ImageType.PNG).withSize(350, 350).stream();
        File file = new File(this.outPutPath + fileName + ".png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(out.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] generateNoStore(String data) {
        String encryptedData = this.encryptor.encrypt(data);
        ByteArrayOutputStream out =
                QRCode.from(encryptedData).to(ImageType.PNG).withSize(350, 350).stream();
        return out.toByteArray();
    }

    /**
     * EN: This method decrypts the data returned from the WebCamQRCapturer once it reads a QRcode
     * image. ----- ES: Este metodo desencripta los datos retornados por el WebCamQRCapturer una vez
     * que lee la imagen con el codigo QR.
     * 
     * @param encryptedData EN: The data to be decrypted. ----- ES: El dato a ser desencriptado
     * @return EN: The decrypted data. ----- ES: El dato desencriptado.
     */
    public String decode(String encryptedData) {
        return this.encryptor.decrypt(encryptedData);
    }
}
