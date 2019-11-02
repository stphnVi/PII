import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;

/***
 *Crea y trabaja con listas enlazadas y los nodos de la misma
 */
public class Lista extends InfoTable {

    Nodo cabeza;
    int size;

    /***
     * @see
     *  Nodo cabeza, es el primer nodo de
     *  la lista enlazada
     */
    public Lista() {
        cabeza = null;
        setSize(0);
    }

    /***
     * @param o objeto que se quiere agregar al inicio de la lista
     * El objeto o es el que se agrega en la lista (Adelante)
     */
    public void agregarDelante(Object o) {
        if (cabeza == null) {
            cabeza = new Nodo(o);
        } else {
            Nodo temp = cabeza;
            Nodo nuevo = new Nodo(o);
            nuevo.agregar(temp);
            cabeza = nuevo;
        }
        size++;
    }

    /**
     * Agrega objetos al final de una lista enlazada
     * @param e objeto que se quiere agregar al final de la lista
     */
    void addLast(Object e) {     //arreglar

        if (this.cabeza != null) {

            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            Nodo n = new Nodo(e);
            temp.siguiente = n;
            n.prev = temp;
            size++;

        } else {
            cabeza = new Nodo(e);
            size++;
        }

    }

    /**
     * Imprime una lista enlazada
     * @param lista lista por imprimir
     */
    static void printList(Lista lista) {
        if(lista.cabeza == null){
            System.out.println("No hay archivos");
        }else {
            for (int i = 0; i < lista.size; i++)
                if(lista.ver(i) != null) {
                    System.out.print(lista.ver(i) + " ");
                }else{
                    System.out.println("esta vacio");
                }
        }
    }

    /***
     * @param index indice en la lista a eliminar
     * Elimina nodos por medio de la posición en la que se encuentra
     */
    public void eliminar(int index) {
        if (cabeza == null) {
            return;
        }
        Nodo temp = cabeza;

        if (index == 0)
        {
            cabeza = temp.siguiente;
        }

        // Find previous node of the node to be deleted
        for (int i=0; temp!=null && i<index-1; i++) {
            temp = temp.siguiente;
        }

        if (temp == null || temp.siguiente == null) {
            return;
        }

        Nodo next = temp.siguiente.siguiente;

        temp.siguiente = next;  // Unlink the deleted node from list
        size--;
    }

    /***
     * @param indice indice donde se encuentra el elemento que se busca
     * observar el elemento de la lista usando el indice dode se encuentra
     * @return retorna el elemento en la posición seleccionada
     */

    public Object ver(int indice) {
        Nodo temp = cabeza;
        for (int i = 0; i < indice; i++) {
            temp = temp.siguiente;
        }
        return temp.VerDato();

    }

    public Nodo getElemento(int indice) {
        Nodo temp = cabeza;
        for (int i = 0; i < indice; i++) {
            temp = temp.siguiente;

        }
        return temp;
    }

    /**
     * Define el tamano de la lista
     * @param size nuevo tamano de la lista
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Envia a ordenar segun lo que el usuario haya seleccionado
     * @param orderBy Valor obtenido por el ComboBox "orderBy", define segun que condiciones se va a ordenar la busqueda
     * @throws IOException Excepcion para .readAttributes
     */
    public void ordenar(String orderBy) throws IOException {  //llamar funcion al ejecutar el boton search
        if (orderBy.equals("Nombre")) {

            datos.bubbleSort();
            System.out.println("DATOS ES: ");
            printList(datos);


        } else if (orderBy.equals("Tamaño")) {
            Lista actual = new Lista();
            for(int i=0; i<datos.size; i++){      //hace una lista y guarda los tamaños para ordenarlos
                File arch = (File) datos.ver(i);
                actual.addLast((int)arch.length()/1024);
            }
            radixsort(actual);
            printList(actual);
            for(int j=0; j<datos.size; j++){
                for(int z=0; z<actual.size; z++){
                    if(((File)datos.ver(j)).length()/1024 == (int)actual.ver(z)){
                        datos.swap(z,j);
                    }
                }
            } printList(datos);

        } else if (orderBy.equals("Fecha")) {
            Lista actual = new Lista();
            for(int i=0; i<datos.size; i++){      //hace una lista y guarda las fechas para ordenarlos
                File arch = (File) datos.ver(i);
                BasicFileAttributes atributes = Files.readAttributes(arch.toPath(), BasicFileAttributes.class); //atributos del archivo
                FileTime time = atributes.creationTime();  //fecha de creacion
                actual.addLast(time);
            }
            init_quick(actual);
            printList(actual);

            for(int j=0; j<datos.size; j++) {
                File arch = (File) datos.ver(j);
                BasicFileAttributes atributes = Files.readAttributes(arch.toPath(), BasicFileAttributes.class); //atributos del archivo
                FileTime time = atributes.creationTime();  //fecha de creacion
                for (int z = 0; z < actual.size; z++) {
                    if ( time == actual.ver(z)) {
                        datos.swap(z, j);
                    }
                }
            }printList(datos);
        }
    }

    //_____________________________________/ BUBBLE SORT /__________________________________________________

    /**
     * Ordena por el metodo BubbleSort, sirve para odenar por nombre del archivo
     */
    public void bubbleSort() {
        if (size > 1) {
            boolean wasChanged;


            do {
                Nodo current = cabeza;
                Nodo previous = null;
                Nodo next = cabeza.siguiente;
                wasChanged = false;

                while (next != null) {
                    char character_1 = ((File)current.dato).getName().charAt(0);
                    char character_2 = ((File)next.dato).getName().charAt(0);

                    if (Character.hashCode(character_1) > Character.hashCode(character_2)) {

                        wasChanged = true;

                        if (previous != null) {
                            Nodo sig = next.siguiente;

                            previous.siguiente = next;
                            next.siguiente = current;
                            current.siguiente = sig;
                        } else {
                            Nodo sig = next.siguiente;

                            cabeza = next;
                            next.siguiente = current;
                            current.siguiente = sig;
                        }

                        previous = next;
                        next = current.siguiente;
                    } else {
                        previous = current;
                        current = next;
                        next = next.siguiente;
                    }
                }
            } while (wasChanged);
        }
    }


    //_____________________________________/ RADIX SORT /__________________________________________________

    /**
     * Obtiene el maximo valor de la lista
     * @param list lista con la que se trabaja
     * @param n tamano de la lista
     * @return maximo valor de la lista
     */
    static int getMax(Lista list, int n) {
        long mx = (int) list.cabeza.dato;
        for (int i = 1; i < n; i++)
            if ((int) list.getElemento(i).dato > mx)
                mx = (int) list.getElemento(i).dato;
        return (int) mx;
    }

    /**
     * Ordena datos de una lista enlazada apoyandose en Arrays
     * @param list Lista con la que se trabaja
     * @param n tamano de la lista
     * @param exp
     */
    static void countSort(Lista list, int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[((int) list.getElemento(i).dato / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[((int) list.getElemento(i).dato / exp) % 10] - 1] = (int) list.getElemento(i).dato;
            count[((int) list.getElemento(i).dato / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            list.getElemento(i).dato = output[i];
    }


    /**
     * Implementa las funciones del RadixSort, sirve para ordenar por peso del archivo
     * @param list Lista a ordenar
     */
    static void radixsort(Lista list) {
        int n = list.size;

        int m = getMax(list, n);

        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(list, n, exp);
    }

    //_____________________________________/ QUICK SORT /__________________________________________________


    /**
     * Inicializa los metodos del QuickSort, sirve para ordenar por fecha
     * @param list Lista a ordenar
     */
    public void init_quick(Lista list) {
        int begin = 0;
        int end = list.size - 1;
        quickSort(list, begin, end);
    }

    /**
     * Implementa las funciones del QuickSort
     * @param list Lista a ordenar
     * @param begin Indice del inicio de la lista
     * @param end Indice del final de la lista
     */
    public void quickSort(Lista list, int begin, int end) {
        char char_begin = (list.ver(begin).toString()).charAt(0);
        char char_end = (list.ver(end).toString()).charAt(0);
        if (Character.hashCode(char_begin) > Character.hashCode(char_end)) {
            int partitionIndex = partition(list, begin, end);

            quickSort(list, begin, partitionIndex - 1);
            quickSort(list, partitionIndex + 1, end);
        }

    }

    /**
     * Crea una particion para separar y reordenar la lista
     * @param list Lista que se ordena
     * @param begin Indice de inicio
     * @param end Indice de final
     * @return int i, valor relacionado con indices de la lista
     */
    private int partition(Lista list, int begin, int end) {
        System.out.println(end);
        String pivot = (String) list.ver(end);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            char temp = ((String) list.ver(j)).charAt(0);
            char char_pivot = pivot.charAt(0);
            if (Character.hashCode(temp) <= Character.hashCode(char_pivot)) {
                i++;
                swap(j, i);
            }
        }

        swap(end, i+1);

        return i + 1;
    }

    /**
     * Intercambia dos nodos en una lista
     * @param i primer nodo a intercambiar
     * @param j segundo nodo a intercambiar
     */
    public void swap(int i, int j) {

        Nodo ithNode = cabeza;
        for (int z = 0; z < i; z++) {
            ithNode = ithNode.siguiente;
        }

        Nodo jthNode = cabeza;
        for (int q = 0; q < j; q++) {
            jthNode = jthNode.siguiente;
        }

        // Swap the data
        Object data = ithNode.dato;
        ithNode.dato = jthNode.dato;
        jthNode.dato = data;
    }
}