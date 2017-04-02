/**
 * A Stack class
 *
 * Author: Ryan Goggins, Sam Xifaras, Jocelyn Shen, Michelle Chao
 * Date: 3/28/17
 * Course: CSC630 Data Structures and ALgorithms
 */

 public class Stack<T> {
  private LinkedList<T> contents;

  /**
   * Creates empty stack
   */
  public Stack() {
    contents = new LinkedList<T>();
  }

  /**
   * Creates stack starting with obj
   * @param obj
   * 		the first object in the stack
   */
  public Stack(T obj) {
    contents = new LinkedList<T>(obj);
  }

  /**
   * Returns true if the stack is empty
   * @return
   */
  public boolean empty() {
    //This method tests if the Stack is empty
    return contents.size() == 0;
  }

  /**
   * Returns object at the top of the stack
   * @return
   * 		Object at top of the stack
   */
  public T peek() {
    return contents.get(0);
  }

  /**
   * Removes object at the top of the stack
   * @return
   * 		Object removed
   */
  public T pop() {
    return contents.remove(0);
    //add it to index 0

  }

  /**
   * Adds item to the top of the list
   * @param item
   * 		Object being added
   * @return
   * 		Object added
   */
  public T push(T item) {
    contents.add(0,item);
    return item;
  }

  /**
   * Returns the index of the specified object
   * @param o
   * 		Object searching the stack for
   * @return
   * 		Index
   */
  public int search(T o) {
    int index = contents.indexOf(o);
    if(index == -1) return -1;
    return index;

  }

  /**
   * Returns the contents of the stack in a readable format
   */
  public String toString() {
    return contents.toString();
  }


  /**
   * Returns the contents of the stack in a LinkedList
   */
  public LinkedList<T> allElements() {
    return contents;
  }

  public static void main(String[] args) {
    // Stack<String> stack1 = new Stack<String>();
    // stack1.push("hello");
    // stack1.push("sam");
    // stack1.push("ryan");
    // stack1.pop();
    // stack1.pop();
    // System.out.println("Pop last here:");
    // stack1.pop();
    // System.out.println(stack1.toString());
    org.junit.runner.JUnitCore.main("StackTestingClass");
  }

}
