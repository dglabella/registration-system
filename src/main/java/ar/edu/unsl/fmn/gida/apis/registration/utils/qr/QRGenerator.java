package ar.edu.unsl.fmn.gida.apis.registration.utils.qr;

public interface QRGenerator {
    /**
     * Generates a QR image with the underlying implementation.
     * <p>
     * ES: Genera una imagen QR con la implementación subyacente.
     * 
     * @param data The data that will be placed into QR image.
     *        <p>
     *        ES: <b>data</b>
     *        <p>
     *        el dato que se pondrá en la imagen QR.
     * @return the byte array containing the image.
     *         <p>
     *         ES: el arreglo de bytes que contiene la imagen.
     */
    byte[] generate(String data, int width, int height);
}
