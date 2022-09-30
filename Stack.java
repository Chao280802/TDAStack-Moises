import java.util.EmptyStackException;

/**
 * Clase que desarrolla la implementación de una pila ligada
 * @author Moisés Corpus García 116005560
 * @version 1.0 (Septiembre 2022)
 * @see TDAStack
 * @see Node
 */
public class Stack<T> implements TDAStack<T> {

  /**
   * Atributo apuntador que fija el ultimo elemento introducido a la pila
   */
  private Node<T> head;

  /**
   * Atributo que representa el numero de elementos almacenados en la pila
   */
  private int size=0;
  
  /**
   * Método que introduce un nuevo elemento a la pila
   * @param e Elemento por almacenar en la pila
   */
  @Override
  public void push(T e){
    Node<T> actual = new Node<T>(e,null);
    if(size == 0)
      this.head = actual;
    else{
      actual.setRef(head);
      this.head = actual;
    }
    this.size++;
  }

  /**
   * Método que borra y devuelve el elemento que se encuentra en la cima de la pila
   * @return Devuelve el último elemento almacenado en la pila
   * @throws EmptyStackException En caso de que la pila esté vacía cuando se llame a este método
   */
  @Override
  public T pop() throws EmptyStackException {
    if(this.isEmpty())
      throw new EmptyStackException();
    Node<T> erased = this.head;
    if(this.head.getRef()==null)
      this.head = null;
    else{
      this.head = this.head.getRef();
    }
    this.size--;
    return erased.getValue();
  }
  
  /**
   * Método que devuelve el elemento en la cima de la pila sin borrarlo
   * @return Devuelve el último elemento almacenado en la pila
   * @throws EmptyStackException En caso de que la pila esté vacia cuando se llame a este método
   */
  @Override
  public T top() throws EmptyStackException {
    if(this.isEmpty())
      throw new EmptyStackException();
    return this.head.getValue();
  }

  /**
   * Método que indica si la pila se encuentra vacía o no
   * @return Devuelve un booleano true si está vacia o false de lo contrario
   */
 @Override
  public boolean isEmpty() {
    return this.size == 0;
  }
  
  /**
   * Método que borra todos los elementos de la pila
   */
  @Override
  public void clear(){
    this.head = null;
    this.size = 0;
  }

  /**
   * Método que imprime los elementos almacenados en la pila
   */
  @Override
  public void show(){
    Node<T> pointer=this.head;
    while(pointer != null){
      System.out.println(pointer.getValue());
      pointer = pointer.getRef();
    }
  }
}