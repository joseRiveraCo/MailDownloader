package mailDownloader.beta;

import javax.mail.Multipart;
import javax.mail.Part;

public class analizaParteDeMensaje {

    public void analizaParteDeMensaje(Part unaParte, String pathdownload) {
        DescargaFichero props = new DescargaFichero();

        try {
            // Si es multipart, se analiza cada una de sus partes recursivamente.
            if (unaParte.isMimeType("multipart/*")) {
                Multipart multi;
                multi = (Multipart) unaParte.getContent();

                for (int j = 0; j < multi.getCount(); j++) {
                    analizaParteDeMensaje(multi.getBodyPart(j), pathdownload);
                }
            } else {
                // Si es texto, se escribe el texto.
                if (unaParte.isMimeType("text/*")) {
                    System.out.println("Texto " + unaParte.getContentType());
                    System.out.println("---------------------------------");
                } else {
                    // Si es imagen, se guarda en fichero y se visualiza en JFrame
                    if (unaParte.isMimeType("application/x-zip-compressed")) {
                        System.out.println("Fichero=" + unaParte.getFileName());
                        System.out.println("---------------------------------");

                        props.DescargaFichero(unaParte, pathdownload);
                    } else if(unaParte.isMimeType("application/pdf")){
                        System.out.println(
                                "Recibido " + unaParte.getContentType());
                        System.out.println("---------------------------------");
                        props.DescargaFichero(unaParte, pathdownload);
                    } else {
                        System.out.println(
                                "Recibido " + unaParte.getContentType());
                        System.out.println("---------------------------------");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
