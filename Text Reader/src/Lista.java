
/***
 * @see
 * Lista se encaga de contener los nodos y operarlos
 */
public class Lista {

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
    void printList() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println(temp.dato);
            temp = temp.siguiente;
        }
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
            temp = temp.VerSig();
        }
        return temp.VerDato();
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

    public void ordenar(){

    }

    public void sort() {
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



}



