import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.StringTokenizer;

public class Conversor {
    static Lista Evaluacion = new Lista();
    int validar = 1;
    String sa = "";
    static VBox Ocurrencias = new VBox();
    TextFlow acomodo = new TextFlow();
    Stage stage = new Stage();
    String palabra = "";
    TitledPane cajaTexto = new TitledPane();
    int busqOracion = 0;

    public Conversor() {
        cajaTexto.setPrefSize(100, 300);
        cajaTexto.setText(Controller.Docu);
        cajaTexto.setContent(acomodo);
        stage.setScene(new Scene(Ocurrencias));
        stage.show();
    }


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

    public void Recorrer(Tree.NodoArbol n, String X, int limite) {


        StringTokenizer st = new StringTokenizer(X);
        String cambio = Evaluacion.ver(0).toString();

        if (n != null) {
            sa = st.nextToken();

            if (limite != n.llave) {
                if (Evaluacion.tamaño == 1) {
                    if (n.Contenido.equals(cambio.hashCode())) {

                        System.out.println("contenido de la fruta: " + n.Contenido + " y el contenido de la busqueda: " + cambio + "  , son iguales");

                        Stage stage = new Stage();
                        palabra = cambio;
                        try {
                            ArmarPalabra(X, stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                } else {
                    CompOracion(n, X);
                }
                Recorrer(n.Izquierda, X, limite);

                System.out.println("indice: " + n.llave + "---" + "contenido: " + n.Contenido);
                Recorrer(n.Derecha, X, limite);


            } else {

                if (n.Contenido.equals(cambio.hashCode())) {
                    System.out.println("contenido de la fruta: " + n.Contenido + " y el contenido de la busqueda: " + cambio + "  , son iguales");
                }
                if (Evaluacion.tamaño > 1) {
                    System.out.println("buscar Oración");
                    ArmarOracion(X);

                } else {
                    System.out.println("terminó el recorrido");

                    Ocurrencias.getChildren().add(cajaTexto);

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

    public void CompOracion(Tree.NodoArbol n, String X) {

        busqOracion++;
        //System.out.println(busqOracion);

        if (validar == Evaluacion.tamaño + 1) {

        } else {
            String cambio = Evaluacion.ver(validar - 1).toString();

            if (n.Contenido.equals(cambio.hashCode())) {
                System.out.println("contenido de la fruta: " + n.Contenido + " y el contenido de la busqueda: " + cambio + ", son iguales");
                validar++;
                palabra = cambio;


            } else {


            }
        }
    }

    /***
     *
     * @param path texto previamente cambiado para poder leerlo indiferentemente del tipo de texto que sea
     * @param stage inicio de una nueva ventana donde estará el texto
     * a es el texto previamete editado para mostrar al usario con la parte econtrada ya subrayada
     *
     */

    public void ArmarPalabra(String path, Stage stage) {


        ObservableList textoM = acomodo.getChildren();

        StringTokenizer t = new StringTokenizer(path);
        String word;


        while (t.hasMoreTokens()) {
            word = t.nextToken();
            Text text = new Text(word);
            String su;
            su = word + " ";
            Text sutext = new Text(su);


            if (word.equalsIgnoreCase(palabra)) {
                acomodo.setTextAlignment(TextAlignment.JUSTIFY);
                // acomodo.setPrefSize(600,300);
                acomodo.setLineSpacing(5.0);
                sutext.setFill(Color.TOMATO);
                sutext.setFont(new Font(30));
                text.setFont(Font.font(null, FontWeight.BOLD, 20));
                sutext.setOpacity(0.5);


                String finalWord = word;

                sutext.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        t.consume();
                    }
                });

                textoM.add(sutext);


            } else {
                acomodo.setTextAlignment(TextAlignment.JUSTIFY);
                acomodo.setPrefSize(600, 300);
                acomodo.setLineSpacing(5.0);
                //text.setEditable(false);
                textoM.add(sutext);

            }


            // System.out.println(word);
        }

    }

    public void ArmarOracion(String path) {

        ObservableList textoM = acomodo.getChildren();
        StringTokenizer t = new StringTokenizer(path);
        String word;

        int i = 0;
        System.out.println(Evaluacion.ver(0));
        System.out.println(Evaluacion.ver(1));
        System.out.println(Evaluacion.tamaño);
        String Temp = "";
        Lista foodos = new Lista();
        int s = 0;
        while (t.hasMoreTokens()) {
            word = t.nextToken();
            //System.out.println("TODO---------------------------" + word);
            Text textdos = new Text();
            String su;
            su = word + " ";
            Text sutextdos = new Text(su);

            if (i == Evaluacion.tamaño) {
                i = 0;

            }

            if (word.hashCode() == Evaluacion.ver(i).hashCode()) {

                int foo = i;
                int cont = 0;


                while (foo <= Evaluacion.tamaño - 1) {

                    //System.out.println("igualdad---------- " + word);

                    if (word.hashCode() == Evaluacion.ver(foo).hashCode()) {
                        if (foo == Evaluacion.tamaño - 1) {
                            cont++;
                            foo++;
                            //System.out.println("igualdad---------- " + word);
                            foodos.addLast(word);
                            s++;
                        } else {
                            cont++;
                            foo++;
                            //System.out.println("igualdad----------- " + word);
                            foodos.addLast(word);
                            word = t.nextToken();
                            s++;
                        }
                    } else {
                        foo = Evaluacion.tamaño;
                        int cuenta = 0;



                       while(cuenta<s){
                           System.out.println("sin igualdad-- "+foodos.ver(foodos.tamaño-1-cuenta));
                             cuenta++;

                       }




                        System.out.println("sin igualdadada-- "+word);
                        acomodo.setTextAlignment(TextAlignment.JUSTIFY);
                        acomodo.setPrefSize(600, 300);
                        acomodo.setLineSpacing(5.0);
                        //text.setEditable(false);
                        textoM.add(sutextdos);




                    }

                }

                if (cont == Evaluacion.tamaño) {
                    s=0;
                    int sal = 0;
                    while (sal <= Evaluacion.tamaño - 1) {

                        sa = Evaluacion.ver(sal) + " ";
                        Text sutextf = new Text(sa);

                        acomodo.setTextAlignment(TextAlignment.JUSTIFY);
                        // acomodo.setPrefSize(600,300);
                        acomodo.setLineSpacing(5.0);
                        sutextf.setFill(Color.TOMATO);
                        sutextf.setFont(new Font(30));
                        text.setFont(Font.font(null, FontWeight.BOLD, 20));
                        sutextf.setOpacity(0.5);


                        String finalWord = word;

                        sutextf.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent t) {
                                t.consume();
                            }
                        });

                        textoM.add(sutextf);


                        System.out.println("igualdad (OFICIAL) en: " + Evaluacion.ver(sal));
                        sal++;
                    }
                }

                i++;
            } else {//System.out.println("salida de la recursion");

                acomodo.setTextAlignment(TextAlignment.JUSTIFY);
                acomodo.setPrefSize(600, 300);
                acomodo.setLineSpacing(5.0);
                //text.setEditable(false);
                textoM.add(sutextdos);
                System.out.println("sin igualdahd-- " + word);
                i = 0;


            }
        }
        Ocurrencias.getChildren().add(cajaTexto);
    }
}





/*

    public void ArmarOracion(String path) {

        ObservableList textoM = acomodo.getChildren();
        StringTokenizer t = new StringTokenizer(path);
        String word;

        int i = 0;
        System.out.println(Evaluacion.ver(0));
        System.out.println(Evaluacion.ver(1));
        System.out.println(Evaluacion.tamaño);
        String Temp="";

        while (t.hasMoreTokens()) {
            word = t.nextToken();
            //System.out.println("TODO-------" + word);

            if(i == Evaluacion.tamaño){
                i=0;

            }

            if (word.hashCode() == Evaluacion.ver(i).hashCode()) {

                int foo = i;

                while(foo<=Evaluacion.tamaño-1){


                    if(word.hashCode()== Evaluacion.ver(foo).hashCode()){

                        if(foo == Evaluacion.tamaño-1){
                            foo++;
                            System.out.println("igualdad-- " + word);
                        }else{
                            foo++;
                            System.out.println("igualdad-- " + word);
                            word= t.nextToken();
                        }

                    }else{
                        foo = Evaluacion.tamaño;
                        //word=t.nextToken();
                        //System.out.println("inicio parecido no hay comparacion alguna"+foo);



                    }

                }

                //System.out.println("salida de la recursion");
                i++;

            } else {
                System.out.println("sin igualdad-- " + word);
                i=0;


            }


            // System.out.println(word);

        }
        }

    }


 */