
public class Nodo {
    private Nodo siguiente;
    private Object dato;

    Nodo(Object dato){
        this.dato = dato;
        this.siguiente = null;
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