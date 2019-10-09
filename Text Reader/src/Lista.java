
/***
 * @see
 * Lista se encaga de contener los nodos y operarlos
 */
public class Lista {

    private Nodo cabeza;
    private int tamaño;

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


    public int getTamaño() {
        return tamaño;
    }
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public void ordenar(){

    }

    public void radixSort(int i, int j){       //i es el indice de las filas, depende del archivo que se ordene
                                               //j es el indice de columnas que se debe ordenar (nombre = 0, tamaño = 1, fecha = 2)

    }

}



