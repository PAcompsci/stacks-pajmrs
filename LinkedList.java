/**
 * A basic implementation of a LinkedList.  Note that the generic type `T`
 * is being used here.
 *
 * Author: Ryan Goggins, Sam Xifaras, Jocelyn Shen, Michelle Chao
 * Date: March 23, 2017
 * Course: CSC630 Data Structures and Algorithms
 */

public class LinkedList<T> {
    private T contents;
    private LinkedList<T> next;

    /**
     * Creates a LinkedList beginning with obj
     * @param obj
     * 		object of type T that will be the first element of the linked list
     */
    public LinkedList(T obj){
      contents = obj;
    }

    /**
     * Creates an empty LinkedList
     */
    public LinkedList() {
        contents = null;
        next = null;
    }

    /**
     * Adds o to the end of the LinkedList
     * @param o
     * 		object to add to end of list
     * @return
     * 		true when object is added
     */
    public boolean add(T o) throws IllegalArgumentException{
      if (o == null) {
          throw new IllegalArgumentException();
      }
      // Handle case where the current node is uninitialized
        if (this.contents == null) {
            this.contents = o;
            return true;
        }
      LinkedList<T> last=this;
      while(last.next!=null){
        last=last.next;
      }
      LinkedList<T> _new = new LinkedList<T>(o);
      last.next=_new;
      return true;
    }

    /**
     * Returns next
     * @return
     * 	next
     */
    public LinkedList<T> getNext(){
      return next;
    }

    /**
     * Make a shallow copy of this LinkedList object
     * @return
     *  the copy of the object
     */
    public LinkedList<T> copy() {
       LinkedList<T> _copy = new LinkedList<T>(this.contents);
       _copy.next = this.next;
       return _copy;
    }

    /**
     * Adds object o of type T at indicated index
     * @param index
     * 		index where object is added in linked list
     * @param o
     * 		object to add
     * @return
     * 		true when object is added in proper index
     */
    public boolean add(int index, T o) throws IllegalArgumentException{
      if (o == null || index < 0 || index >= this.size()) {
        if (index != 0) {
          throw new IllegalArgumentException();
        }
      }
      // Handle special case for 0

        // Handle case where the current node is uninitialized
        if (this.contents == null && index == 0) {
            this.contents = o;
            return true;
        }
      if (index == 0) {
          LinkedList<T> tmp = new LinkedList<T>(o);
          tmp.next = this.copy();
          this.contents = tmp.contents;
          this.next = tmp.next;
          return true;
      }
      LinkedList<T> _new = new LinkedList<T>(o);
      LinkedList<T> prev = null;
      LinkedList<T> last = this;
      for (int i = 0; i < index; i++) {
        prev = last;
        last = last.next;
      }
      if (prev != null) {
        prev.next = _new;
      }
      _new.next = last;
      return true;
    }

    /**
     * Return value in linked list at index
     * @param index
     * 		index of the value being returned
     * @return
     * 		contents of index
     */
    public T get(int index) throws IllegalArgumentException{
        if (index < 0) {
            throw new IllegalArgumentException();
        }

      LinkedList<T> last = this;
      for (int i = 0; i < index; i++){
        last = last.next;
      }
      return last.contents;
    }

    /**
    * Checks through the list to see if there exist
    * any objects with contents the same as o
    *
    */
    public boolean contains(T o){
      LinkedList<T> last=this;
      while(last.next!=null){
        if(last.contents.equals(o)){
          return true;
        }
        last=last.next;
      }
      return false;
    }

    /**
    * Checks through the list to see if there exist
    * any objects with contents the same as o
    * if there exists any, it returns the index of it
    * otherwise it returns -1
    */
    public int indexOf(T o){
      LinkedList<T> last=this;
      int counter=0;
      while(last!=null){
        if(last.contents.equals(o)){
          return counter;
        }
        last=last.next;
        counter++;
      }
      return -1;
    }

    /**
    * Goes through the list and counts
    * the size of it
    */
    public int size(){
      LinkedList<T> last=this;
      int counter=0;
      if (this.contents==null) {
        return 0;
      }
      else if (this.next == null) {
        return 1;
      }
      counter++;
      while(last.next!=null){
        last=last.next;
        counter++;
      }
      return counter;
    }

    /**
     * Clears the linked list
     */
    public void clear(){
        this.next = null;
        this.contents = null;
    }

    /**
     * Remove object at given index
     * @param index
     * 		index to remove object from
     * @return
     * 		return object removed
     */
    public T remove(int index) throws IllegalArgumentException{
        if (index < 0 || index >= this.size()) {
          if (index != 0) {
            throw new IllegalArgumentException();
          }
          else {
            System.out.println("size is actually zero finally");
          }
        }
        // Handle special case for 0
        if (index == 0) {
            T save_contents = this.contents;
            if (this.next != null) {
                this.contents = this.next.contents;
                this.next = this.next.next;
            }
            else {
              this.contents = null;
            }
            return save_contents;
        }
      LinkedList<T> prev = null;
      LinkedList<T> last = this;
      for (int i = 0; i < index; i++){
        prev = last;
        last = last.next;
      }
      if (prev != null) {
        prev.next = last.next;
      }
      last.next = null;
      return last.contents;
    }

    /**
     * Prints linked list in format "[...,...,...]"
     */
    public String toString(){
    	String str = "[";
    	LinkedList<T> last = this;
    	while(last.next != null){
    		str += last.contents.toString() + ", ";
    		last = last.next;
    	}
      if (last.contents == null) {
        str += "]";
      }
      else {
        str += last.contents.toString() + "]";

      }
    	return str;
    }
}
