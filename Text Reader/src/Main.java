import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML
    VBox name;
    VBox size;
    VBox date;
    @Override
    public void start(Stage primaryStage) throws Exception{


        Parent root = new Pane();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("./TextReader.fxml"));

        fxmlLoader.setRoot(root);
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);

        QuickSort A = new QuickSort();
        Lista B = new Lista();
        B.addLast("hola");
        B.addLast("adios");
        B.printList();
        //int[] C = new int[]{5,2,3};
        //A.quicksort(C, 0, 0);
        BubbleSort C = new BubbleSort();
        B.sort();
        B.printList();




        primaryStage.setScene(scene);
        primaryStage.setTitle("Proyecto II - Text Reader");
        //primaryStage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }


}


