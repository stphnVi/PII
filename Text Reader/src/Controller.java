import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class Controller {
    static String busqueda= "";
    static int HashVal = 0;
    static int temp;        //variable temporal

    @FXML ComboBox<String> combo;
    @FXML Button UploadFiles;
    @FXML Button UploadDir;
    @FXML ComboBox<String> orderBy;
    @FXML Stage stage;
    @FXML TableView<String> InfoTable;
    @FXML VBox files;
    @FXML Button search;
    @FXML TextField txt;
    @FXML VBox name;
    @FXML VBox size;
    @FXML VBox date;
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

    }
    public void SearchButton(){
        /***
         * @see
         * Cuando el usuario busca, la palabra se envía a la clase conversor
         */
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                busqueda= txt.getText();
                HashVal = busqueda.hashCode();
                StringTokenizer st = new StringTokenizer(busqueda);
                String sa = "";

                while (st.hasMoreTokens()){
                    sa = st.nextToken();
                    System.out.println("palabras que se buscan: "+ sa);
                }

                Results buscar = new Results();
                try {
                    buscar.newRes();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
    public void DirChooserButton(ActionEvent event){
        UploadDir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Upload Files");
                try {
                    DirectoryChooser dir_chooser = new DirectoryChooser();
                    File carpeta = dir_chooser.showDialog(stage);

                    String[] ficheros = carpeta.list();
                    if (ficheros == null) {
                        System.out.println("No hay ficheros en el directorio especificado");
                    } else {
                        MenuButton folderDisplay = new MenuButton();
                        int length = (carpeta.getName().length())+400;
                        folderDisplay.setPrefSize(length,40);
                        folderDisplay.setText(carpeta.getName());

                        for (int x = 0; x < ficheros.length; x++) {
                            if (ficheros[x].endsWith(".txt")) {
                                MenuItem txt = new MenuItem(new File(ficheros[x]).getName());
                                folderDisplay.getItems().addAll(txt);
                                temp++;
                            } else if (ficheros[x].endsWith(".pdf")) {
                                MenuItem pdf = new MenuItem(new File(ficheros[x]).getName());
                                folderDisplay.getItems().addAll(pdf);
                                temp++;
                            } else if (ficheros[x].endsWith(".docx")) {
                                MenuItem docx = new MenuItem(new File(ficheros[x]).getName());
                                folderDisplay.getItems().addAll(docx);
                                temp++;
                            }

                        }if(temp == 0) {
                            Alert extentionError = new Alert(Alert.AlertType.WARNING);
                            extentionError.setTitle("Warning");
                            extentionError.setHeaderText("Chosen directory does not contain valid files");
                            extentionError.show();
                        }


                        files.getChildren().addAll(folderDisplay);
                        InfoTable a = new InfoTable();
                        a.addInfo(carpeta, name,size,date);
                        temp = 0;


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
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
                            TXT read= new TXT();
                            read.leer(file.getAbsolutePath());
                        }else if(tempPath.endsWith(".pdf")){
                            addFile(file.getName(), new TextField());
                            System.out.println("file PDF");
                            PDF read = new PDF();
                            read.leer(file.getAbsolutePath());
                        }else if (tempPath.endsWith(".docx")){
                            addFile(file.getName(), new TextField());
                            System.out.println("file DOCX");
                            DOCX read= new DOCX();
                            read.leer(file.getAbsolutePath());

                        }else{
                            Alert extentionError = new Alert(Alert.AlertType.ERROR);
                            extentionError.setTitle("Error");
                            extentionError.setContentText("Invalid file extention \n Program only allows .txt/ .pdf/ .docx");
                            extentionError.show();
                        }

                        System.out.println("  selected/hadle part:  "+(file.getAbsolutePath()));
                        InfoTable a = new InfoTable();
                        a.addInfo(file, name,size,date);

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

    public void addDir(String dirName, MenuButton carpeta){
        int length = (dirName.length())+400;
        carpeta.setPrefSize(length,40);
        carpeta.setText(dirName);
        files.getChildren().addAll(carpeta);
    }




}

