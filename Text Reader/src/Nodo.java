
public class Nodo {
    Nodo siguiente;
    Object dato;
    Nodo prev;

    /***
     *
     * @param dato
     * inicialización del nodo
     */


    Nodo(Object dato){
        this.dato = dato;
        this.siguiente = null;
        this.prev = null;

    }
    Nodo VerSig()
    {
        return this.siguiente;
    }

    /***
     *
     * @param n
     * nodo que se agregará a la lista
     */
    void agregar(Nodo n) {

        this.siguiente = n;
    }
    Object VerDato() {
        return this.dato;
    }


}