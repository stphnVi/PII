import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class TXT extends Conversor {
    Lista EvaluacionBusq = new Lista();
    Tree arbol = new Tree();
    Conversor recorrida = new Conversor();
    String K= "";
    int numTokens = 0;

    /***
     *
     * @param a texto TXT que se leerá,
     *          se separará por cada palabra y se intriducirá como una
     *          nueva fruta en el árbol
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
            //OBSERVARR
            s1 = br.readLine();

            K = s1;


            StringTokenizer st = new StringTokenizer(s1);


            int i = 0;
            while (st.hasMoreTokens()) {
                s2 = st.nextToken();
                numTokens++;
                arbol.Insertar(numTokens, s2.hashCode());
                i++;
            }


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


