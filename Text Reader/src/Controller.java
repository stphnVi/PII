import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Controller {

    @FXML
    ComboBox combo;

    void ComboBoxValues(){
        combo.getItems().removeAll(combo.getItems());
        combo.getItems().removeAll("Phrases");
        combo.getSelectionModel().select("Word");
    }


}
