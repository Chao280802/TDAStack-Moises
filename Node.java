/**
 * Clase que implementa nodos con referencia para su integracion en una pila ligada
 * @author
 * @version 1.0 (Septiembre 2022)
 */
public class Node<E> {
    private Node<E> ref;
    private E value;

    public Node(E initialValue, Node<E> initialRef){
        this.value = initialValue;
        this.ref = initialRef;
    }

    /**
     * Método modificador del valor que almacena el nodo
     * @param newValue Nuevo valor por almacenar en el nodo
     */
    public void setValue(E newValue){
        this.value = newValue;
    }

    /**
     * Método modificador de la referencia al siguiente nodo
     * @param newValue Nueva referencia a un siguiente nodo por asignar
     */
    public void setRef(Node<E> newRef){
        this.ref = newRef;
    }

    /**
     * Método consultor del valor que almacena el nodo
     * @return Devuelve el valor almacenado en el nodo
     */
    public E getValue(){
        return this.value;
    }

    /**
     * Método consultor de la referencia al siguiente nodo
     * @return Devuelve la referencia al nodo siguiente registrada en este nodo
     */
    public Node<E> getRef(){
        return this.ref;
    }
}
