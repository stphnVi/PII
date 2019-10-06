import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = new Pane();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("./TextReader.fxml"));

        fxmlLoader.setRoot(root);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);


        primaryStage.setScene(scene);
        primaryStage.setTitle("Proyecto II - Text Reader");
        primaryStage.show();

        //Pruebas del arbol
         Arbol arbol = new Arbol();
        /***
         * indice donde lo quiero poner y lo que quiero insertar/fruta
         */
        System.out.println("Observar el orden en el que se inserta: 1,2,8,4,0");
        //OBSERVAR EL ORDEN EN QUE SE INSERTA Y EN QUE SE IMPRIME
        arbol.Insertar(1,"Manzana");
        arbol.Insertar(2,"Banano");
        arbol.Insertar(8,"Pera");
        arbol.Insertar(4,"Jocote");
        arbol.Insertar(0,"Mango");


        System.out.println("Todo el Arbol");
        arbol.Recorrer(arbol.raiz);
        System.out.println("parte Izquierda del Arbol(raiz no se lee)");
        arbol.Recorrer(arbol.raiz.Izquierda);
        System.out.println("parte Derecha del Arbol(raiz no se lee)");
        arbol.Recorrer(arbol.raiz.Derecha);




    }


    public static void main(String[] args) {
        launch(args);
    }


}


