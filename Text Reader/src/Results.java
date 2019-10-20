import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Results extends Controller {
    public void newRes() throws IOException {
        Scene scene = new Scene(new Group());
        Stage primaryStage = new Stage();
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        primaryStage.setTitle("Proyecto II - Text Reader");

        VBox container = new VBox();
        ((Group) scene.getRoot()).getChildren().addAll(container);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
