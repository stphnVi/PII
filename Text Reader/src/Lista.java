import java.util.Arrays;

/***
 * @see
 * Lista se encaga de contener los nodos y operarlos
 */
public class Lista extends InfoTable {

    Nodo cabeza;
    int tamaño;

    /***
     * @see
     *  Nodo cabeza, es el primer nodo de
     *  la lista enlazada
     */
    public Lista(){
        cabeza = null;
        setTamaño(0);
    }

    /***
     * @param o
     *
     * El objeto o es el que se agrega en la lista (Adelante)
     */
    public void agregarDelante(Object o) {
        if(cabeza ==null){
            cabeza = new Nodo(o);
        }else {
            Nodo temp = cabeza;
            Nodo nuevo = new Nodo(o);
            nuevo.agregar(temp);
            cabeza = nuevo;
        }
        tamaño++;
    }

    void addLast(Object e) {     //arreglar

        if (this.cabeza != null) {

            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            Nodo n = new Nodo(e);
            temp.siguiente = n;
            n.prev = temp;
            tamaño++;

        } else {
            cabeza = new Nodo(e);
            tamaño++;
        }

    }
    static void printList(Lista lista)
    {
        for (int i=0; i<lista.tamaño; i++)
            System.out.print(lista.ver(i)+" ");
    }

    /***
     * @param index
     *
     * Elimina nodos por medio de la posición en la que se encuentra
     */
    public void eliminar(int index){
        if(index ==0){
            cabeza =cabeza.VerSig();
        }else{
            int contador = 0;
            Nodo temp = cabeza;
            while(contador< index-1){
                temp = temp.VerSig();
                contador++;
            }
            temp.agregar(temp.VerSig().VerSig());
        }
        tamaño--;
    }

    /***
     * @param indice
     * observar el elemento de la lista usando el indice dode se encuentra
     * @return
     * retorna el elemento en la posición seleccionada
     */

    public Object ver(int indice) {
        Nodo temp = cabeza;
        for(int i=0; i<indice; i++) {
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

    /***
     *obtiene el tamaño de la lista
     * @return
     * retorna el resultado
     *
     */

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public void ordenar(String orderBy){  //llamar funcion al ejecutar el boton search
        if(orderBy.equals("Nombre")){
            //llamar a bubble
            matriz.bubbleSort();
        }else if(orderBy.equals("Tamaño")){
            //llamar a Radix
        }else if(orderBy.equals("Fecha")){
            //llamar a quick
        }
    }

    //_____________________________________/ BUBBLE SORT /__________________________________________________
    public void bubbleSort() {
        if (tamaño > 1) {
            boolean wasChanged;


            do {
                Nodo current =cabeza;
                Nodo previous = null;
                Nodo next = cabeza.siguiente;
                wasChanged = false;

                while (next != null) {
                    char character_1 = ((String) current.dato).charAt(0);
                    char character_2 = ((String) next.dato).charAt(0);

                    if ( Character.hashCode(character_1) > Character.hashCode(character_2)) {

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


    static int getMax(Lista list, int n) {
        int mx = (int) list.cabeza.dato;
        for (int i = 1; i < n; i++)
            if ((int)list.getElemento(i).dato > mx)
                mx = (int) list.getElemento(i).dato;
        return mx;
    }

    static void countSort(Lista list, int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < n; i++)
            count[((int)list.getElemento(i).dato / exp) % 10]++;

        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (i = n - 1; i >= 0; i--) {
            output[count[((int)list.getElemento(i).dato / exp) % 10] - 1] = (int) list.getElemento(i).dato;
            count[((int)list.getElemento(i).dato / exp) % 10]--;
        }

        for (i = 0; i < n; i++)
            list.getElemento(i).dato = output[i];
    }


    static void radixsort(Lista list) {
        int n = list.tamaño;

        int m = getMax(list, n);

        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(list, n, exp);
    }

    //_____________________________________/ QUICK SORT /__________________________________________________

    public void init_quick(Lista list){
        int begin = 0;
        int end = list.tamaño-1;
        quickSort(list, begin, end);
    }
    public void quickSort(Lista list, int begin, int end) {
        char char_begin = ((String)list.ver(begin)).charAt(0);
        char char_end =  ((String)list.ver(end)).charAt(0);
        if (Character.hashCode(char_begin) > Character.hashCode(char_end)) {
            int partitionIndex = partition(list, begin ,end);

            quickSort(list, begin, partitionIndex-1);
            quickSort(list, partitionIndex+1, end);
        }

    }
    private int partition(Lista list, int begin, int end) {
        System.out.println(end);
        String pivot = (String) list.ver(end);
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            char temp = ((String)list.ver(j)).charAt(0);
            char char_pivot = pivot.charAt(0);
            if (Character.hashCode(temp) <= Character.hashCode(char_pivot)) {
                i++;

                String swapTemp = (String) list.ver(i);
                String val = (String) list.ver(i);
                val = (String) list.ver(j);
                String val2 = (String) list.ver(i);
                 val2 = swapTemp;
            }
        }

        Nodo swapTemp = list.getElemento(i+1);
        Nodo val = list.getElemento(i+1);
        val = list.getElemento(end);
        Nodo val2 = list.getElemento(end);
        val2 = swapTemp;


        return i+1;
    }
}