import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 * Lee y trabaja archivos del tipo .pdf
 */
public class PDF {

    Tree arbol = new Tree();
    Lista EvaluacionBusq = new Lista();
    Conversor recorrida = new Conversor();

    /**
     * Lee e inicia la busqueda en archivos .pdf
     * @param a String a buscar
     */
    public void leer(String a){
        try (PDDocument document = PDDocument.load(new File(a))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);


                StringTokenizer st = new StringTokenizer( pdfFileInText );
                String sa = "";
                int p = 0;
                int numTokens = 0;
                /***
                 * insertar letra por letra al arbol
                 */
                while (st.hasMoreTokens()){
                    sa = st.nextToken();
                    numTokens++;
                    arbol.Insertar(numTokens, sa.hashCode());
                    p++;

                }

                StringTokenizer leer = new StringTokenizer(Controller.busqueda);
                String su = "";
                /***
                 *
                 * insertar la frase o palabra que el usuario buscará a una lista enlazada
                 */
                while (leer.hasMoreTokens()) {
                    su = leer.nextToken();
                    EvaluacionBusq.addLast(su);
                }
                Conversor.Evaluacion = EvaluacionBusq;
                Controller.Docu = pdfFileInText;
                recorrida.Recorrer(arbol.raiz, pdfFileInText, numTokens);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
