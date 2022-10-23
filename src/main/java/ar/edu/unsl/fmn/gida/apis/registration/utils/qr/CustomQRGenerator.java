package ar.edu.unsl.fmn.gida.apis.registration.utils.qr;

import java.io.File;
import net.glxn.qrgen.QRCode;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import net.glxn.qrgen.image.ImageType;
import ar.edu.unsl.fmn.gida.apis.registration.utils.cypher.Cypher;

public class CustomQRGenerator implements QRGenerator {
    // ------------------------------ Fields ------------------------------
    private Cypher cypher;

    public CustomQRGenerator(Cypher cypher) {
        this.cypher = cypher;
    }

    // ------------------------------ Getters & Setters ------------------------------

    // ------------------------------ Public Methods ------------------------------
    // /**
    // * EN: This method generates the QR code in a JPG image. The location where the QR code will
    // be
    // * storaged can be setted using the setPath method.
    // * <p>
    // * ES: Este metodo genera el codigo QR en una imagen JPG. El directorio donde se almacenara el
    // * codigo QR puede ser configurado usando el metodo setPath.
    // *
    // * @param fileName EN: The desired fileName (without extension). ----- ES: El nombre de
    // archivo
    // * deseado (sin extension).
    // * @param data EN: The data to be encrypted. ----- ES: El dato a ser encriptado.
    // *
    // */
    // public void generate(String fileName, String data, int width, int height) {
    // String encryptedData = this.cypher.encrypt(data);
    // ByteArrayOutputStream out =
    // QRCode.from(encryptedData).to(ImageType.PNG).withSize(width, height).stream();
    // File file = new File(fileName + ".png");
    // try {
    // FileOutputStream fileOutputStream = new FileOutputStream(file);
    // fileOutputStream.write(out.toByteArray());
    // fileOutputStream.flush();
    // fileOutputStream.close();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation uses png format for the image.
     * <p>
     * ES: Esta implementación usa formato png para la imagen.
     */
    @Override
    public byte[] generate(String data, int width, int height) {
        ByteArrayOutputStream out = QRCode.from(this.cypher.encrypt(data)).to(ImageType.PNG)
                .withSize(width, height).stream();
        return out.toByteArray();
    }
}
