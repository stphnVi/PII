import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        Lista B = new Lista();
        B.addLast(3);
        B.addLast(1);
        QuickSort A = new QuickSort();
        B.radixsort(B);
        B.printList(B);





        primaryStage.setScene(scene);
        primaryStage.setTitle("Proyecto II - Text Reader");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}


