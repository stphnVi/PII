import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * Realiza todas las funciones relacionadas con el uso de funciones de interfaz grafica del archivo .fxml
 */
public class Controller {
    static String busqueda= "";
    static int HashVal = 0;
    static int temp;        //variable temporal
    static String Docu = "";
    @FXML ComboBox<String> combo;
    @FXML Button UploadFiles;
    @FXML Button UploadDir;
    @FXML ComboBox<String> orderBy;
    @FXML Stage stage;
    @FXML VBox files;
    @FXML Button search;
    @FXML TextField txt;
    @FXML VBox name;
    @FXML VBox size;
    @FXML VBox date;
    int temp1;
    int temp2;
    static Lista datos = new Lista();


    /**
     * El usuario elige si quiere buscar una frase o una palabra
     */
    public void ComboOptions(){
        if(temp1 == 0){
            combo.setEditable(true);
            ObservableList<String> textType = FXCollections.observableArrayList("Frase", "Palabra");
            combo.getItems().addAll(textType);
            combo.setEditable(false);
            temp1++;
        }

    }

    /***
     * Cuando el usuario busca, la palabra se envía a la clase conversor
     */
    public void SearchButton(){
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

                try {
                    datos.ordenar(orderBy.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    SendRead();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    /**
     * El usuario selecciona como quiere ordenar la busqueda
     */
    public void OrderOptions(){
        if(temp2 ==0){
            orderBy.setEditable(true);
            ObservableList<String> textType = FXCollections.observableArrayList("Nombre", "Tamaño", "Fecha");
            orderBy.getItems().addAll(textType);
            orderBy.setEditable(false);
            temp2++;
        }
    }

    /**
     * Sube los archivos ubicados dentro de un directorio a la busqueda
     * @param event evento de presionar el boton Upload Directory
     */
    public void DirChooserButton(ActionEvent event){

        UploadDir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    DirectoryChooser dir_chooser = new DirectoryChooser();
                    File carpeta = dir_chooser.showDialog(stage);
                    File[] ficheros = carpeta.listFiles();
                    Lista avilableFiles =  new Lista();


                    if (ficheros == null) {
                        System.out.println("No hay ficheros en el directorio especificado");
                    } else {
                        MenuButton folderDisplay = new MenuButton();
                        int length = (carpeta.getName().length()) + 400;
                        folderDisplay.setPrefSize(length, 40);
                        folderDisplay.setText(carpeta.getName());
                        InfoTable a = new InfoTable();


                        for (int x = 0; x < ficheros.length; x++) {
                            if (ficheros[x].getName().endsWith(".txt")) {
                                MenuItem txt = new MenuItem(ficheros[x].getName());
                                folderDisplay.getItems().addAll(txt);
                                a.addInfo(ficheros[x], name, size, date);
                                avilableFiles.addLast(ficheros[x]);
                                temp++;
                            } else if (ficheros[x].getName().endsWith(".pdf")) {
                                MenuItem pdf = new MenuItem(ficheros[x].getName());
                                folderDisplay.getItems().addAll(pdf);
                                a.addInfo(ficheros[x], name, size, date);
                                avilableFiles.addLast(ficheros[x]);
                                temp++;
                            } else if (ficheros[x].getName().endsWith(".docx")) {
                                MenuItem docx = new MenuItem(ficheros[x].getName());
                                folderDisplay.getItems().addAll(docx);
                                a.addInfo(ficheros[x], name, size, date);
                                avilableFiles.addLast(ficheros[x]);
                                temp++;
                            }

                        }
                        if (temp == 0) {
                            Alert extentionError = new Alert(Alert.AlertType.WARNING);
                            extentionError.setTitle("Warning");
                            extentionError.setHeaderText("Chosen directory does not contain valid files");
                            extentionError.show();
                        }
                        files.getChildren().addAll(folderDisplay);
                        temp = 0;

                        folderDisplay.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent event) {
                                if (event.getCode() == KeyCode.DELETE) {
                                    Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
                                    delete.setTitle("Confirmación");
                                    delete.setHeaderText("¿Desea eliminar este directorio?");
                                    delete.setContentText("La búsqueda ya no se realiza en los archivos relacionados");
                                    Optional<ButtonType> result = delete.showAndWait();

                                    if (delete.getResult() == ButtonType.OK) {
                                        System.out.println("remove");
                                        files.getChildren().remove(folderDisplay);
                                        InfoTable a = new InfoTable();
                                        for (int j = 0; j < datos.size; j++) {
                                            for(int z = 0; z < ficheros.length; z++){
                                                if(datos.ver(j) !=null && ficheros[z].getName().equals(((File)datos.ver(j)).getName())){
                                                    a.deleteFile(j + 1, name, date, size);
                                                }
                                            }

                                        }

                                    } else if (result.get() == ButtonType.CANCEL) {
                                        delete.close();
                                    }
                                }
                            }

                        });

                    }

                } catch (Exception e) {
                }
            }

        });
    }

    /**
     * Sube los archivos ubicados dentro de un directorio a la busqueda
     * @param event Evento de presionar el boton Upload Files
     */
    public void FileChooserButton(ActionEvent event) {

        UploadFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    FileChooser fil_chooser = new FileChooser();
                    File file = fil_chooser.showOpenDialog(stage);

                    if (file != null) {
                        InfoTable a = new InfoTable();
                        String tempPath = file.getCanonicalPath().toLowerCase();
                        if (tempPath.endsWith(".txt")){
                            addFile(file.getName(), new TextField());
                            a.addInfo(file, name,size,date);

                        }else if(tempPath.endsWith(".pdf")){
                            addFile(file.getName(), new TextField());
                            a.addInfo(file, name,size,date);
                        }else if (tempPath.endsWith(".docx")){
                            addFile(file.getName(), new TextField());
                            a.addInfo(file, name,size,date);

                        }else{
                            Alert extentionError = new Alert(Alert.AlertType.ERROR);
                            extentionError.setTitle("Error");
                            extentionError.setContentText("Invalid file extention \n Program only allows .txt/ .pdf/ .docx");
                            extentionError.show();
                        }


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

    /**
     * Agrega un TextField al Vbox contador de archivos subidos de nombre "files" y ejecuta accion de eliminar cuando se presiona la tecla "Delete"
     * @param fileName Nombre del archivo
     * @param archivo nuevo TextField por agregar
     */
    public void addFile(String fileName, TextField archivo){
        int length = (fileName.length())+500;
        archivo.setPrefSize(length,40);
        archivo.setText(fileName);
        archivo.setEditable(false);
        files.getChildren().addAll(archivo);

        archivo.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()== KeyCode.DELETE){
                    Alert delete = new Alert(Alert.AlertType.CONFIRMATION);
                    delete.setTitle("Confirmación");
                    delete.setHeaderText("¿Desea eliminar este archivo?");
                    delete.setContentText("Ya no formará parte de la búsqueda");
                    Optional<ButtonType> result = delete.showAndWait();

                    if(delete.getResult() == ButtonType.OK){
                        System.out.println("remove");
                        files.getChildren().remove(archivo);
                        InfoTable a = new InfoTable();

                        for(int i=0; i<datos.size; i++){
                            File act = (File) datos.ver(i);
                            String archName = (archivo.getCharacters()).toString();
                            if(act.getName().equals(archName)){
                                System.out.println(i);
                                a.deleteFile(i+1, name, date, size);
                            }
                        }
                    }else if(result.get() == ButtonType.CANCEL){
                        delete.close();
                    }


            }
        }
        });
    }

    /**
     * Una vez se presiona el boton "Search", una vez la lista "datos", la cual almacena los archivos, esta ordenada se procede a
     * extraer y leer cada archivo de la misma para realizar la respectiva busqueda
     * @throws IOException Excepcion para .getCanonicalPath
     */
    public void SendRead() throws IOException {

        for(int i=0; i<datos.size ; i++) {

            File file = (File)datos.ver(i);
            String tempPath = file.getCanonicalPath().toLowerCase();

            if (tempPath.endsWith(".txt")) {
                TXT read = new TXT();
                read.leer(file.getAbsolutePath());
            } else if (tempPath.endsWith(".pdf")) {
                PDF read = new PDF();
                read.leer(file.getAbsolutePath());
            } else if (tempPath.endsWith(".docx")) {
                DOCX read = new DOCX();
                read.leer(file.getAbsolutePath());

            }
        }

    }




}

