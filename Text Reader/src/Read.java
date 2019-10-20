import javafx.scene.control.Alert;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

            try (PDDocument document = PDDocument.load(new File(x))) {

               Conversor conv = new Conversor();
               conv.PDF(x);

            } catch (IOException e) {
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
                System.out.println(cadena);
            }
            w.close();

            Conversor conv= new Conversor();
            conv.TXT(x);


            }catch(IOException e){
                alert.setContentText("no se pude leer el archivo");
                alert.show();

            }


        //DOCX(solucionar librería)

        } else {
            Conversor conv = new Conversor();
            conv.DOCX(x);

            }

        }
    }

