import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class InfoTable extends Controller {

    private final ObservableList<String> data =
            FXCollections.observableArrayList(
                    new String("a@example.com"),
                    new String("b@example.com")
            );
    public void add(){

    }



}