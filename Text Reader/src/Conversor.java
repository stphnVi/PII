import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Conversor {

    static Lista EvaluacionBusq = new Lista();

    public void Trans(String a){
        byte b;
        Tree arbol = new Tree();


        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            /*
            Abrir el archivo y leerlo
             */
            archivo = new File (a);
            fr = new FileReader (archivo);
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
            while (st.hasMoreTokens())
            {
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

            while (leer.hasMoreTokens()){
                sa = leer.nextToken();
                EvaluacionBusq.agregarDelante(sa);
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            /*
            cerrar el archivo e imprimir las frutas del arbol
             */

            try{


                /*
                System.out.println("parte Izquierda del Arbol(raiz no se lee)");
                arbol.Recorrer(arbol.raiz.Izquierda);
                System.out.println("parte Derecha del Arbol(raiz no se lee)");
                arbol.Recorrer(arbol.raiz.Derecha);
                */
                System.out.println("Todo el Arbol");
                arbol.Recorrer(arbol.raiz);
                if( null != fr ){
                    fr.close();
                }

                for(int i=0; i<= EvaluacionBusq.tamaño  -1; i++){
                    System.out.println(EvaluacionBusq.ver(i));
                }



            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
