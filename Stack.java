import java.util.EmptyStackException;

/**
 * Implementación de una pila basada en listas.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 Septiembre 2022.
 * @since Estructuras de datos 2023-1.
 */
public class Stack<T> implements TDAStack<T> {

  private Node<T> head;
  private int size=0;
  
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
  
  @Override
  public T top() throws EmptyStackException {
    if(this.isEmpty())
      throw new EmptyStackException();
    return this.head.getValue();
  }

 @Override
  public boolean isEmpty() {
    return this.size == 0;
  }
  
  @Override
  public void clear(){
    this.head = null;
    this.size = 0;
  }

  @Override
  public void show(){
    Node<T> pointer=this.head;
    while(pointer != null){
      System.out.println(pointer.getValue());
      pointer = pointer.getRef();
    }
  }
}