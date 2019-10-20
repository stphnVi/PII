
public class Nodo {
    Nodo siguiente;
    Object dato;
    Nodo prev;


    Nodo(Object dato){
        this.dato = dato;
        this.siguiente = null;
        this.prev = null;

    }
    Nodo VerSig()
    {
        return this.siguiente;
    }
    void agregar(Nodo n) {

        this.siguiente = n;
    }
    Object VerDato() {
        return this.dato;
    }


}