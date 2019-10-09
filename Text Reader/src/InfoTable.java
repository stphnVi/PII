import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoTable extends Controller {

    static Lista matriz = new Lista();      //matriz que guarda los atributos de los archivos

    public void addInfo(File file, VBox name, VBox size, VBox date) throws IOException {
        System.out.println("in");
        TextField newName = new TextField();
        newName.setText(file.getName());
        System.out.println(name);
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

        Lista datos = new Lista();  //datos del archivo

        datos.agregarDelante(newName);
        datos.agregarDelante(newSize);
        datos.agregarDelante(newDate);

        matriz.agregarDelante(datos);   //agrega una fila dentro de la matriz





    }


}