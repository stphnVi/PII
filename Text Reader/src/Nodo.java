/**
 * Crea los nodos que conforman una lista enlazada
 */
public class Nodo {
    Nodo siguiente;
    Object dato;
    Nodo prev;

    /**
     * Contructor de nodo
     * @param dato contenido del nuevo nodo
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

    /**
     * agrega nodos con el dato
     * @param n nuevo nodo a agregar
     */
    void agregar(Nodo n) {

        this.siguiente = n;
    }

    /**
     * Muesta el dato contenido en el nodo
     * @return Contenido del nodo
     */
    Object VerDato() {
        return this.dato;
    }


}