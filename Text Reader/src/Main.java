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




    }


    public static void main(String[] args) {
        launch(args);
    }


}


