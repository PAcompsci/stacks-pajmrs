/**
 * Inspects a class for its methods. If the methods have primitive arguments,
 * the Inspector calls these methods with arguments.
 *
 * Author: Jocelyn Shen, Michelle Chao, Sam Xifaras, Ryan Goggins
 * Date: March 28, 2017
 * Sources: Java reflect API
 *          http://stackoverflow.com/questions/6094575/creating-an-instance-using-the-class-name-and-calling-constructor
 */

// A lot of method calls don't work with the LinkedList class because of recursive next

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.lang.reflect.*;

public class Inspector implements Runnable{
  private int[] integer_options;
  private String[] string_options;
  private Thread t;
  private Thread inspecteeThread;

  private final Object lock = new Object();

  public Inspector(Thread iT){
    inspecteeThread = iT;
    integer_options = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    string_options = new String[]{"dog", "cat," ,"house", "computer science"};
  }

  public Object getLock() {
    return lock;
  }

    /**
    * Inspects the given class for its methods with their return values if
    * callable, and adds each method to a string.
    * @param className the name of the class to be inspected
    * @return an ArrayList containing MethodReport objects for each method
    * in the class
    */
    private ArrayList<MethodReport> InspectAll(String className) throws NotImplementedException{
      throw new NotImplementedException();
  }


  public void run()
  {
    int counter = 0;
    ArrayList<Map<Thread, StackTraceElement[]>> list = new ArrayList<Map<Thread, StackTraceElement[]>>();
      while(inspecteeThread.getState()!=Thread.State.TERMINATED)
      {
        list.add(Thread.getAllStackTraces());
        counter++;

      }
      for (int i = 0; i < list.size(); i++) {
        System.out.println(Arrays.toString(list.get(i).get(inspecteeThread)));
      }
      //System.out.println(Arrays.toString(stacks.get(inspecteeThread)));

      System.out.println("LOOPED " + counter + " TIMES");
  }

  public void start () {
      if (t == null) {
          t = new Thread(this, "Inspector object's thread");
          t.setPriority(Thread.MAX_PRIORITY);
          t.start();
      }
  }

   public static void main(String args[]) {
     Inspectee R2 = new Inspectee("TestClass");
     Inspector R1 = new Inspector(R2.getT());
     R2.setLock(R1.getLock());
     R1.start();
     R2.start();
     //R1.InspectAll(args[0]);

    }
}
