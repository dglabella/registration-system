package ar.edu.unsl.fmn.gida.apis.registration.utils.qr;

import net.glxn.qrgen.QRCode;
import java.io.ByteArrayOutputStream;
import net.glxn.qrgen.image.ImageType;

public class CustomQRGenerator implements QRGenerator {
    /**
     * {@inheritDoc}
     * <p>
     * This implementation uses png format for the image.
     * <p>
     * ES: Esta implementación usa formato png para la imagen.
     */
    @Override
    public byte[] generate(String data, int width, int height) {
        ByteArrayOutputStream out =
                QRCode.from(data).to(ImageType.PNG).withSize(width, height).stream();
        return out.toByteArray();
    }
}
