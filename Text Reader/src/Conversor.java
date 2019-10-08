import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Conversor {



    public void Trans(String a){
        byte b;
        Tree arbol = new Tree();

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (a);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            //String linea;
            String s2;
            String s1;
            s1 = br.readLine();


            int numTokens = 0;
            StringTokenizer st = new StringTokenizer(s1);

            // bucle por todas las palabras
            while (st.hasMoreTokens())
            {

                s2 = st.nextToken();
                numTokens++;
                arbol.Insertar(numTokens, s2);
               // System.out.println ("    Palabra " + numTokens + " es: " + s2);
            }



            /*
            int i = 0;
            while((linea=br.readLine())!=null)
                arbol.Insertar(i,linea);
                System.out.println(linea+ "--");
                i++;

             */
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{

                System.out.println("Todo el Arbol");
                arbol.Recorrer(arbol.raiz);
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
















        //introducir al arbol
        /*

        for(int i =0; i< a.length();i++){
            b = (byte)a.charAt(i);

            arbol.Insertar(b,a.charAt(i));

        }
        System.out.println("Todo el Arbol");
        arbol.Recorrer(arbol.raiz);

        System.out.println("parte Izquierda del Arbol(raiz no se lee)");
        arbol.Recorrer(arbol.raiz.Izquierda);
        System.out.println("parte Derecha del Arbol(raiz no se lee)");
        arbol.Recorrer(arbol.raiz.Derecha);
*/
    }
}
