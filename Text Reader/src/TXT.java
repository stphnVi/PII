import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * Clase para trabajar con archivos del tipo .txt
 */
public class TXT extends Conversor {
    Lista EvaluacionBusq = new Lista();
    Tree arbol = new Tree();
    Conversor recorrida = new Conversor();
    String K= "";
    int numTokens = 0;

    /**
     * Realiza la lectura de archivos .txt
     * @param a String a buscar
     */
    public void leer(String a) {
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

            K = s1;


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
                EvaluacionBusq.addLast(sa);
            }
            Conversor.Evaluacion = EvaluacionBusq;
            Controller.Docu = s1;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
            cerrar el archivo e imprimir las frutas del arbol
             */

            try {

                System.out.println("Todo el Arbol");
                recorrida.Recorrer(arbol.raiz, K, numTokens);
                if (null != fr) {
                    fr.close();
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}


