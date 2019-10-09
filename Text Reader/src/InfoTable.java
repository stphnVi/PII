import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoTable extends Controller {

    private final ObservableList<String> data =
            FXCollections.observableArrayList(
                    new String("a@example.com"),
                    new String("b@example.com")
            );
    public void addInfo(File file) throws IOException {
        TextField newName = new TextField();
        newName.setText(file.getName());
        name.getChildren().add(newName);

        TextField newSize = new TextField();
        double size = (file.length()/1024);
        newSize.setText(size + "kb");

        TextField newDate =  new TextField();
        BasicFileAttributes atributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class); //atributos del archivo
        FileTime time = atributes.creationTime();  //fecha de creacion
        String pattern = "yyyy-MM-dd HH:mm:ss";    //formato del patron para la fecha
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);  //asignar el patron
        String formatted = simpleDateFormat.format( new Date( time.toMillis() ) );
        newDate.setText(formatted);
        date.getChildren().add(newDate);





    }



}