import javafx.scene.control.Alert;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Read {


    Alert alert = new Alert(Alert.AlertType.ERROR);

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
     *
     *          TXT
     * Ya viene contenida
     *
     */

    public void leer(String x, String b) {
        //PDF

        if (b == "pdf") {

            /*try (PDDocument doc = PDDocument.load(new File(x))) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(doc);

                Conversor conv= new Conversor();
                conv.Trans(x);


                System.out.println("Text size: " + text.length());
                System.out.println("name Document: " + x);
            } catch (Exception e) {
                alert.setContentText("no se pude leer el archivo");
                alert.show();
            }

        //TXT


        } else if (b == "txt") {
            try{
            String cadena;
            FileReader f = new FileReader(x);
           //System.out.println(x.length());
            BufferedReader w = new BufferedReader(f);


            while((cadena = w.readLine())!=null) {
                //System.out.println(cadena);
            }
            w.close();

            Conversor conv= new Conversor();
            conv.Trans(x);


            }catch(Exception e){
                alert.setContentText("no se pude leer el archivo");
                alert.show();

            }*/


        //DOCX(solucionar librería)

        } else {

        }
    }
}
