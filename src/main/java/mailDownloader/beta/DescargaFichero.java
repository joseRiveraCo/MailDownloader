package mailDownloader.beta;

import javax.mail.MessagingException;
import javax.mail.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DescargaFichero {

    public void DescargaFichero(Part unaParte,String pathdownload) throws MessagingException, IOException {
        BetaApplication props = new BetaApplication();
        FileOutputStream files = new FileOutputStream( pathdownload + unaParte.getFileName());
        System.out.println(pathdownload + unaParte.getFileName());
        InputStream zip = unaParte.getInputStream();
        byte[] bytes = new byte[10485760];
        int leidos;
        while ((leidos = zip.read(bytes)) > 0) {
            files.write(bytes, 0, leidos);
        }
        zip.close();
        System.out.println("Descarga OK del documento nombre "+ unaParte.getFileName()+ "con peso " + unaParte.getSize());

    }

}
