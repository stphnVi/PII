import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Conversor {

    static Lista EvaluacionBusq = new Lista();
    Tree arbol = new Tree();
    public void TXT(String a) {
        byte b;



        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            /*
            Abrir el archivo y leerlo
             */
            archivo = new File(a);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String s2;
            String s1;
            s1 = br.readLine();
            int numTokens = 0;
            StringTokenizer st = new StringTokenizer(s1);

            /***
             *separar las letras de la linea para poder insertar en el arbol
             */
            int i = 0;
            while (st.hasMoreTokens()) {
                s2 = st.nextToken();
                numTokens++;
                arbol.Insertar(numTokens, s2.hashCode());
                i++;
            }
            /***
             * Separar letras del texto de busqueda para insertar a la lista y así
             * buscar palabra por palabra cuando se busca una frase
             */

            StringTokenizer leer = new StringTokenizer(Controller.busqueda);
            String sa = "";

            while (leer.hasMoreTokens()) {
                sa = leer.nextToken();
                EvaluacionBusq.agregarDelante(sa);
            }

            for (int k = 0; k <= EvaluacionBusq.tamaño - 1; k++) {
                //System.out.print(EvaluacionBusq.ver(k));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
            cerrar el archivo e imprimir las frutas del arbol
             */

            try {

                System.out.println("Todo el Arbol");
                arbol.Recorrer(arbol.raiz);
                if (null != fr) {
                    fr.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void PDF(String a) {


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
                //INSERTAR AL ARBOL
                while (st.hasMoreTokens()){
                    sa = st.nextToken();
                    numTokens++;
                    arbol.Insertar(numTokens, sa.hashCode());
                    p++;

                }

                StringTokenizer leer = new StringTokenizer(Controller.busqueda);
                String su = "";

                while (leer.hasMoreTokens()) {
                    su = leer.nextToken();
                    EvaluacionBusq.agregarDelante(su);
                }

                arbol.Recorrer(arbol.raiz);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
