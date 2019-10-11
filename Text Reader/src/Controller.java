import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {
    static String busqueda= "";
    static int HashVal = 0;

    @FXML ComboBox<String> combo;
    @FXML Button UploadFiles;
    @FXML ComboBox<String> orderBy;
    @FXML Stage stage;
    @FXML TableView<String> InfoTable;
    @FXML VBox files;
    @FXML Button search;
    @FXML TextField txt;
    int temp1;
    int temp2;



    public void ComboOptions(){
        if(temp1 == 0){
            combo.setEditable(true);
            ObservableList<String> textType = FXCollections.observableArrayList("Frase", "Palabra");
            combo.getItems().addAll(textType);
            combo.setEditable(false);
            temp1++;
        }
        /***
         * @see
         * Cuando el usuario busca, la palabra se envía a la clase conversor
         */
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                busqueda= txt.getText();
                HashVal = busqueda.hashCode();




            }
        });
    }


    public void OrderOptions(){
        if(temp2 ==0){
            orderBy.setEditable(true);
            ObservableList<String> textType = FXCollections.observableArrayList("Nombre", "Tamaño", "Fecha");
            orderBy.getItems().addAll(textType);
            orderBy.setEditable(false);
            temp2++;
        }
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
                            addFile(file.getName(), new TextField());
                            System.out.println("file TXT");
                            Read read= new Read();
                            read.leer(file.getAbsolutePath(), "txt");
                        }else if(tempPath.endsWith(".pdf")){
                            addFile(file.getName(), new TextField());
                            System.out.println("file PDF");
                            Read read= new Read();
                            read.leer(file.getAbsolutePath(), "pdf");
                        }else if (tempPath.endsWith(".docx")){
                            //NEWWWW
                            addFile(file.getName(), new TextField());
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
                        InfoTable a = new InfoTable();
                        a.AddName();

                    }

                } catch (Exception e) {
                    System.out.println(e);
                    Alert nullError = new Alert(Alert.AlertType.ERROR);
                    nullError.setTitle("Error");
                    nullError.setContentText("El archivo seleccionado es nulo");
                    nullError.show();
                }
            }
        });
    }

    public void addFile(String fileName, TextField archivo){
        int length = (fileName.length())+500;
        archivo.setPrefSize(length,40);
        archivo.setText(fileName);
        archivo.setEditable(false);
        files.getChildren().addAll(archivo);
    }




}

