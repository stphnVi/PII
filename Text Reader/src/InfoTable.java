import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Agrega la informacion como nombre, tamano y fecha de cada archivo subido por el usuario donde se realizara la busqueda
 */
public class InfoTable extends Controller {


    // [[nombre, tamaño, fecha],  nombre = matriz[0][0], [1][0], [2][0]
    //  [nombre, tamaño, fecha],  tamaño = matriz[0][1], [1][1], [2][1]
    //  [nombre, tamaño, fecha]]  fecha  = matriz[0][2], [1][2], [2][2]


    /**
     * Crea una tabla con las caracteristicas de cada archivo subido e ingresa el archivo a una lista enlazada
     * @param file Archivo por agregar
     * @param name Columna para los nombres de los archivos
     * @param size  Columna para el peso del archivo
     * @param date  Columna para la fecha de creacion del archivo
     * @throws IOException Excepcion para .readAttributes
     */
    public void addInfo(File file, VBox name, VBox size, VBox date) throws IOException {
        datos.addLast(file);
        //datos.printList(datos);
        TextField newName = new TextField();
        newName.setText(file.getName());
        name.getChildren().add(newName);

        TextField newSize = new TextField();
        double num = (file.length()/1024);
        newSize.setText(num + "kb");
        size.getChildren().add(newSize);


        TextField newDate =  new TextField();
        BasicFileAttributes atributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class); //atributos del archivo
        FileTime time = atributes.creationTime();  //fecha de creacion
        String pattern = "yyyy-MM-dd HH:mm:ss";    //formato del patron para la fecha
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);  //asignar el patron
        String formatted = simpleDateFormat.format( new Date( time.toMillis() ) );
        newDate.setText(formatted);
        date.getChildren().add(newDate);




    }

    /**
     * Borra los archivos que se eliminan de la Vbox files de la tabla de informacion y de la lista
     * @param index Indice donde se encuentra el archivo a
     * @param name
     * @param date
     * @param size
     */
    public void deleteFile(int index, VBox name, VBox date, VBox size){
        name.getChildren().remove(name.getChildren().get(index));
        date.getChildren().remove(date.getChildren().get(index));
        size.getChildren().remove(size.getChildren().get(index));
        datos.eliminar(index-1);
    }




}