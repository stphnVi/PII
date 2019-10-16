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


    public void Recorrer(NodoArbol n){
        //Validargit git
        if(n!=null){
            //System.out.println(Conversor.EvaluacionBusq.tamaño );
            if(Conversor.EvaluacionBusq.tamaño ==1) {
                String cambio = Conversor.EvaluacionBusq.ver(0).toString();

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

    public void CompOracion(NodoArbol n){
        System.out.println("validar: "+ validar);
        System.out.println("lista de busqueda: "+Conversor.EvaluacionBusq.tamaño);

        if(validar == Conversor.EvaluacionBusq.tamaño+1){
            System.out.println("terminé");
        }else {
            String cambio = Conversor.EvaluacionBusq.ver(validar - 1).toString();

            if (n.Contenido.equals(cambio.hashCode())) {
                System.out.println("contenido de una fruta y búsqueda son iguales" + n.Contenido);
                validar++;

            } else {
                // System.out.println("contenido de una fruta y búsqueda no son iguales");

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
         if(Conversor.EvaluacionBusq.tamaño == 0){
                String cambio = Conversor.EvaluacionBusq.ver(n.llave-1).toString();
                if(n.Contenido.equals(cambio.hashCode())){
                    System.out.println("contenido de una fruta y búsqueda son iguales");
                }

            }else{

                System.out.println(n.llave);

                String cambio = Conversor.EvaluacionBusq.ver(n.llave-1).toString();
                if(n.Contenido.equals(cambio.hashCode())){
                    System.out.println("contenido de una fruta y búsqueda son iguales");
                    System.out.println(n.Contenido);
                }else{
                    System.out.println("contenido de una fruta y búsqueda no son iguales");

                }
            }
         */

}
