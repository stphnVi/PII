public class Tree {

    NodoArbol raiz;
    public Tree(){
        raiz = null;
    }

    //método para insertar

    /***
     *
     * @param i indice o llave del elemento a insertar
     * @param fruta contenido del nuevo nodo
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

    /**
     * Crea los nodos del arbol
     */
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

        /**
         * Constructor del nodo
         * @param indice llave del nuevo nodo
         */
        public NodoArbol(int indice){
            llave = indice;
            Derecha = null;
            Izquierda = null;
            Padre = null;
        }


    }

   /*
                System.out.println("parte Izquierda del Arbol(raiz no se lee)");
                arbol.Recorrer(arbol.raiz.Izquierda);
                System.out.println("parte Derecha del Arbol(raiz no se lee)");
                arbol.Recorrer(arbol.raiz.Derecha);


                */
}
