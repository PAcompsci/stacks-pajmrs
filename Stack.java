/**
 * A Stack class
 *
 * Author: Ryan Goggins & Sam Xifaras
 * Date: 3/28/17
 * Course: CSC630 Data Structures and ALgorithms
 */
 public class Stack<T> extends LinkedList<T> {

  private T contents;
  private Stack<T> head;

  //no args constructor
  public Stack() {
    contents = null;
  }

  public boolean empty() {
    //This method tests if the Stack is empty
    return (head == null);
  }

  public T peek() {
    return contents;
  }

  public T pop() {
    //add it to index 0

  }

  public T push(T item) {

  }

  public int search(Object o) {

  }

  public String toString() {

  }
}
