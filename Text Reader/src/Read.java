import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class Read {
    //Contructor
    public Read() {

    }
    /***
     * @param x la dirección del archivo
     * @param b identificador de tipo de texto (provicional)
     *
     *          PDF
     * Descargar de:https://pdfbox.apache.org/download.cgi
     * La librería: pdfbox-2.0.17.jar 2.6MB, pre-built binary ASC SHA512
     * Ejemplos: https://pdfbox.apache.org/2.0/examples.html
     *          DOCX
     *          TXT
     *
     */

    public void leer(String x, String b) throws IOException {
        //PDDocument document = PDDocument.load(new File("test.pdf"));
        if(b=="pdf"){

        try (PDDocument doc = PDDocument.load(new File(x))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);
            System.out.println("Text size: " + text.length());
            System.out.println("name Document: "+ x);
        }


        }else if(b=="txt") {


        }else{

        }

    }



}
