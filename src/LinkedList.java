/**
 * Generic LinkedList
 * @author kino
 */
public class LinkedList<T>{
  private Node<T> head;
  private int size;
  
  public LinkedList(){
    head = null;
  }
  
  public void addLast(T data){
    Node<T> dataNode = new Node<T>(data);
    size++;
    if(head==null){
      head = dataNode;
      return;
    }
    Node<T> cur = head;
    while(cur.getNext()!=null){
      cur = cur.getNext();  
    }
    cur.setNext(dataNode);
    return;
  }
  
  /**
   * Returns an item in the LinkedList at a specified index, without
   * deleting the item. 
   * @param index Index of the item to be found. First item is at index 0.
   * @return An item whose object type depends on how the LinkedList was
   * instantiated, since it is a generic LinkedList to allow for re-use.
   */
  public T peek(int index){
    if(index<0 || index>=size)
      return null;
    Node<T> cur = head;
    for(int x=0;x<index;x++)
      cur = cur.getNext();
    return cur.getData();
  }
  
  public boolean isEmpty(){
    if(head==null)
      return true;
    return false;
  }
  
  public int size(){
    return size;
  }
}