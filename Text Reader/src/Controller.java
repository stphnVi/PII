import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {

    @FXML
    public ComboBox combo;
    @FXML
    Button UploadFiles;
    Stage stage;

    public void ComboOptions(){
        combo.setEditable(true);
        ObservableList<String> textType = FXCollections.observableArrayList("Frase", "Palabra");
        combo.getItems().addAll(textType);

    }

    public void FileChooserButton(ActionEvent event) {

        UploadFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Upload Files");
                try {

                    FileChooser fil_chooser = new FileChooser();
                    File file = fil_chooser.showOpenDialog(stage);

                    if (file != null) {
                        String tempPath = file.getCanonicalPath().toLowerCase();
                        if (tempPath.endsWith(".txt")){
                            System.out.println("file TXT");
                            Read read= new Read();
                            read.leer(file.getAbsolutePath(), "txt");
                        }else if(tempPath.endsWith(".pdf")){
                            System.out.println("file PDF");
                            Read read= new Read();
                            read.leer(file.getAbsolutePath(), "pdf");
                        }else if (tempPath.endsWith(".docx")){
                            //NEWWWW
                            System.out.println("file DOCX");
                            Read read= new Read();
                            read.leer(file.getAbsolutePath(), "docx");

                        }else{
                            Alert extentionError = new Alert(Alert.AlertType.ERROR);
                            extentionError.setTitle("Error");
                            extentionError.setContentText("Invalid file extention \n Program only allows .txt/ .pdf/ .docx");
                            extentionError.show();
                        }

                        System.out.println("  selected/hadle part:  "+(file.getAbsolutePath()));

                    }

                } catch (Exception e) {
                    Alert nullError = new Alert(Alert.AlertType.ERROR);
                    nullError.setTitle("Error");
                    nullError.setContentText("El archivo seleccionado es nulo");
                    nullError.show();
                }
            }
        });
    }




}

