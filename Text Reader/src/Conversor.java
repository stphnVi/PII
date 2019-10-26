import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.StringTokenizer;

public class Conversor extends Stage{
    static Lista Evaluacion = new Lista();
    int validar = 1;
    String sa = "";
    int p = 0;
    int numTokens = 0;
    final HighlightableTextArea highlightableTextArea = new HighlightableTextArea();


    /***
     *
     * @param n es la fruta del arbol
     * @param X es el texto de cada uno de los archivos previamente cambiado para poder obtener su lectura
     *          indifeentemente del tipo de texto que sea
     * @param limite es la cantidad de palabras en e texto
     * @static EvaluacionBusq es la lista donde están los elementos
     *         que el usuario quiere buscar
     *
     * si la lista solo tiene un elemento, hace la validación
     * y si no se llama la función ComOración
     *
     *
     */

    String a;
    String sh="";

    public void Recorrer(Tree.NodoArbol n, String X, int limite){
        StringTokenizer st = new StringTokenizer(X);
        String cambio = Evaluacion.ver(0).toString();

        if(n!=null) {
            sa = st.nextToken();

            if (limite != n.llave) {
                if (Evaluacion.tamaño == 1) {
                    if (n.Contenido.equals(cambio.hashCode())) {

                        System.out.println("contenido de la fruta: "+ n.Contenido +" y el contenido de la busqueda: "+ cambio +"  , son iguales");
                        a = a + n.Contenido;
                    }


                } else {
                    CompOracion(n);
                }
                Recorrer(n.Izquierda, X, limite);

                System.out.println("indice: " + n.llave + "---" + "contenido: " + n.Contenido);
                Recorrer(n.Derecha, X, limite);



            }else{

                if (n.Contenido.equals(cambio.hashCode())) {
                    System.out.println("contenido de la fruta: "+ n.Contenido +" y el contenido de la busqueda: "+ cambio +"  , son iguales");
                }
                System.out.println("terminó el recorrido");


                Stage stage = new Stage();
                try {
                    Armar(X,stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }

    }


    /***
     *
     * @param n fruta del arbol a evaluar en ua lista con más de una elemento
     * este método busca la palabra en el arbol, ua vez que la encuentra
     * pasa al siguiente valor en la lista y consecuentemente al la llave siguente del arbol
     * para hacer las validaciones en orden
     */

    public void CompOracion(Tree.NodoArbol n){

        if(validar == Evaluacion.tamaño+1){

        }else {
            String cambio = Evaluacion.ver(validar - 1).toString();

            if (n.Contenido.equals(cambio.hashCode())) {
                System.out.println("contenido de la fruta: "+ n.Contenido +" y el contenido de la busqueda: "+ cambio +", son iguales");
                validar++;

            } else {

            }
        }
    }

    /***
     *
     * @param a texto previamente cambiado para poder leerlo indiferentemente del tipo de texto que sea
     * @param stage inicio de una nueva ventana donde estará el texto
     * a es el texto previamete editado para mostrar al usario con la parte econtrada ya subrayada
     *
     */

    public void Armar(String a, Stage stage){


        //System.out.println(a);

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        Scene sc = new Scene(root, 600, 600);
        stage.setScene(sc);
        stage.show();
        VBox.setVgrow(highlightableTextArea, Priority.ALWAYS);



        /***
         * @see HighlightableTextArea con parametros,aca se coloca de que letra a que letra se quiere subrayar en cualquier texto
         *
         */
        highlightableTextArea.highlight(0, 2);



        StringTokenizer st = new StringTokenizer(a);
        while (st.hasMoreTokens()){
            sa = st.nextToken();

            highlightableTextArea.setText(sa);
            highlightableTextArea.getTextArea().setWrapText(true);
            highlightableTextArea.getTextArea().setStyle("-fx-font-size: 20px;");

            numTokens++;
            p++;

            System.out.println(sa + sa.length());
        }




       root.getChildren().addAll(highlightableTextArea);



    }

}

