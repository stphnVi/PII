import java.awt.*;

public class QuickSort extends Lista {
    public Nodo quickSort(Lista list) {
        System.out.println("entra");
        if (list.cabeza == null || list.cabeza.VerSig() == null){
            System.out.println(list.cabeza.siguiente);
            return list.cabeza;
        }
        return quick(list.cabeza, null);
    }

    private Nodo quick(Nodo start, Nodo end){
        if (start == null || start == end || start.siguiente== end){
            System.out.println(start);
            return start;
        }

        Nodo[] result = partition(start, end);
        //System.out.println(result);
        Nodo resultLeft = quick(result[0], result[1]);
        Nodo resultRight = quick(result[1].VerSig(), end);
        return resultLeft;

    }

    private Nodo[] partition(Nodo start, Nodo end){
        // start inclusive
        // end exclusive
        // return the new start node and the pivot node

        if (start == null || start == end || start.VerSig() == end){
            return new Nodo[] {start, start};
        }
        Nodo dummy = new Nodo(0).VerSig();
        dummy = start;

        for (Nodo j = start; j != null && j.VerSig() != null && j.VerSig() != end; j = j.VerSig()) {
            while (j.VerSig() != null && (j.VerSig()).hashCode() <= (start.VerDato()).hashCode()){
                Nodo tmp = j.VerSig();
                j.siguiente = j.VerSig().VerSig();
                tmp.siguiente = dummy.VerSig();
                dummy.siguiente = tmp;
                System.out.println(j);
            }
        }
        return new Nodo[] {dummy.VerSig(), start};
    }

    public static void quicksort(int A[], int izq, int der) {

        int pivote=A[izq]; // tomamos primer elemento como pivote
        int i=izq; // i realiza la búsqueda de izquierda a derecha
        int j=der; // j realiza la búsqueda de derecha a izquierda
        int aux;

        while(i<j){            // mientras no se crucen las búsquedas
            while(A[i]<=pivote && i<j) i++; // busca elemento mayor que pivote
            while(A[j]>pivote) j--;         // busca elemento menor que pivote
            if (i<j) {                      // si no se han cruzado
                aux= A[i];                  // los intercambia
                A[i]=A[j];
                A[j]=aux;
            }
        }
        A[izq]=A[j]; // se coloca el pivote en su lugar de forma que tendremos
        A[j]=pivote; // los menores a su izquierda y los mayores a su derecha
        if(izq<j-1)
            quicksort(A,izq,j-1); // ordenamos subarray izquierdo
        if(j+1 <der)
            quicksort(A,j+1,der); // ordenamos subarray derecho
        System.out.println(A[0]);
    }

}
