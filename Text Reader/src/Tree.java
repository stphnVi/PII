public class Tree {
    NodoArbol raiz;
    public Tree(){
        raiz = null;
    }

    //método para insertar

    /***
     *
     * @param i
     * i es indice
     * @param fruta
     * fruta el nuevo nodo que insertaré
     */
    public void Insertar(int i, Object fruta){

        NodoArbol n = new NodoArbol(i);
        n.Contenido = fruta;
        //Validar si la raiz es nula para crear el arbol

        if(raiz == null){
            raiz = n;

            // si no se crea un nodo auxiliar para ver donde se coloca(izq/der)
        }else{
            NodoArbol aux = raiz;
            //Ahora se escala dentro del arbol mientras el auxiliar no sea null

            while (aux!=null){
                n.Padre = aux;
                if(n.llave >= aux.llave){
                    aux = aux.Derecha;
                }else{
                    aux = aux.Izquierda;
                }

            }

            //Ahora que ya se sabe donde debe ir el nodo, este se debe enlazar
            if(n.llave< n.Padre.llave){
                n.Padre.Izquierda =n;
            }else{
                n.Padre.Derecha = n;
            }

        }

    }


public void Recorrer(NodoArbol n){
        //Validar
        if(n!=null){
            if(n.Contenido.equals(Conversor.EvaluacionBusq.ver(1))){
                System.out.println(n.Contenido);
                System.out.println(Controller.HashVal);
                System.out.println("contenido de una fruta y búsqueda son iguales");

            }
                // Cambiar el orden de las recursiones para recorrer alrevez el arbol;
                Recorrer(n.Izquierda);
                System.out.println("indice: " + n.llave + "---" + "contenido: " + n.Contenido);
                Recorrer(n.Derecha);

        }

}

    public void BusOración(NodoArbol n){




    }

    public void BusPalabra(){



    }



    public class NodoArbol{
        // Padre es el nodo dentro del arbol del cual podran nacer fruticas
        public NodoArbol Padre;
        // Direccion de la frutica
        public NodoArbol Izquierda;
        public NodoArbol Derecha;
        // Indice que corresponde al nodo/frutica
        public int llave;
        //Contenido del nodo/frutica (object para poner cualquier tipo)
        public Object Contenido;



        //CONSTRUCTOR
        public NodoArbol(int indice){
            llave = indice;
            Derecha = null;
            Izquierda = null;
            Padre = null;
        }


    }
}
