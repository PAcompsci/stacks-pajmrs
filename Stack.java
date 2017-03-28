/**
 * A Stack class
 *
 * Author: Ryan Goggins & Sam Xifaras
 * Date: 3/28/17
 * Course: CSC630 Data Structures and ALgorithms
 */
 
 public class Stack<T> {
  private LinkedList<T> contents;
  
  //no args constructor
  public Stack() {
    contents = null;
  }
  
  public boolean empty() {
    //This method tests if the Stack is empty
    return contents.size() == 0;
  }

  public T peek() {
    return contents.get(0);
  }

  public T pop() {
    contents.remove(0);
    //add it to index 0

  }

  public T push(T item) {
    contents.add(0,item);
  }

  public int search(Object o) {
    int index = contents.indexOf(o);
    if(index == -1) return -1;
    return index+1;

  }

  public String toString() {
    return contents + "";
  }
}
