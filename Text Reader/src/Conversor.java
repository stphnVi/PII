import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.StringTokenizer;

public class Conversor {
    static Lista Evaluacion = new Lista();
    int validar = 1;
    String sa = "";
    int p = 0;
    int numTokens = 0;
    /***
     * insertar letra por letra al arbol
     */


    /***
     *
     * @param n es la fruta del arbol
     * @static EvaluacionBusq es la lista donde están los elementos
     * que el usuario quiere buscar
     *
     * si la lista solo tiene un elemento, hace la validación
     * y si no se llama la función ComOración
     *
     *
     */

    String a;
    String sh="";

    public void Recorrer(Tree.NodoArbol n, String X, int limite){
        String cambio = Evaluacion.ver(0).toString();

        if(n!=null) {
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
                //System.out.println("indice: " + n.llave + "---" + "contenido: " + n.Contenido);
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
     * @param a
     * a es el texto previamete editado para mostrar al usario con la parte econtrada ya subrayada
     *
     */

    public void Armar(String a, Stage stage){


        StringTokenizer st = new StringTokenizer(a);

        while (st.hasMoreTokens()){
            sa = st.nextToken();
            numTokens++;
            p++;
        }

        System.out.println(a);

        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        Scene sc = new Scene(root, 600, 600);
        stage.setScene(sc);
        stage.show();


        final HighlightableTextArea highlightableTextArea = new HighlightableTextArea();
        highlightableTextArea.setText(a);
        highlightableTextArea.getTextArea().setWrapText(true);
        highlightableTextArea.getTextArea().setStyle("-fx-font-size: 20px;");
        VBox.setVgrow(highlightableTextArea, Priority.ALWAYS);


        root.getChildren().addAll(highlightableTextArea);



    }

}

