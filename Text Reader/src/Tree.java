public class Tree {
    int validar = 1;
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

    /***
     *
     * @param n es la fruta del arbol
     * @static EvaluacionBusq es la lista donde están los elementos
     * que el usuario quiere buscar
     *
     * si la lista solo tiene un elemento, hace la validación
     * y si no se llama la función ComOración
     *
     *
     */
    public void Recorrer(NodoArbol n){

        if(n!=null){

            if(Conversor.Evaluacion.tamaño ==1) {
                String cambio = Conversor.Evaluacion.ver(0).toString();

                if (n.Contenido.equals(cambio.hashCode())) {

                    System.out.println("contenido de una fruta y búsqueda son iguales");
                }
            }else{
                CompOracion(n);

            }
            // Cambiar el orden de las recursiones para recorrer alrevez el arbol;
            Recorrer(n.Izquierda);
            System.out.println("indice: " + n.llave + "---" + "contenido: " + n.Contenido);
            Recorrer(n.Derecha);

        }

    }

    /***
     *
     * @param n fruta del arbol a evaluar en ua lista con más de una elemento
     * este método busca la palabra en el arbol, ua vez que la encuentra
     * pasa al siguiente valor en la lista y consecuentemente al la llave siguente del arbol
     * para hacer las validaciones en orden
     */

    public void CompOracion(NodoArbol n){


        if(validar == Conversor.Evaluacion.tamaño+1){
            System.out.println("termino la búsqueda");
        }else {
            String cambio = Conversor.Evaluacion.ver(validar - 1).toString();

            if (n.Contenido.equals(cambio.hashCode())) {
                System.out.println("contenido de una fruta y búsqueda son iguales: " + n.Contenido);
                validar++;

            } else {

            }
        }
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

   /*
                System.out.println("parte Izquierda del Arbol(raiz no se lee)");
                arbol.Recorrer(arbol.raiz.Izquierda);
                System.out.println("parte Derecha del Arbol(raiz no se lee)");
                arbol.Recorrer(arbol.raiz.Derecha);


                */
}
