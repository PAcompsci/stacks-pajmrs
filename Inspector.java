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

  private ArrayList<Map<Thread, StackTraceElement[]>> data;
  private Boolean is_terminated = false;
  private final Object lock = new Object();

  public Inspector(){
    inspecteeThread = null;
    integer_options = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    string_options = new String[]{"dog", "cat," ,"house", "computer science"};
    data = null;
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
      Inspectee R2 = new Inspectee(className);
      this.inspecteeThread = R2.getT();
      this.start();
      R2.start();

      while (true) {
        Boolean tmp;
        synchronized(is_terminated) {
          tmp = is_terminated;
        }
        if (tmp.equals(true)) break;
      }

      ArrayList<MethodReport> report = R2.getReport();
      for (int i = 0; i < report.size(); i++) {
        report.get(i).get
      }
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

      this.data = list;
      //System.out.println(Arrays.toString(stacks.get(inspecteeThread)));

      System.out.println("LOOPED " + counter + " TIMES");


      synchronized(is_terminated) {
        is_terminated = true;
      }
  }

  public void start () {
      if (t == null) {
          t = new Thread(this, "Inspector object's thread");
          t.setPriority(Thread.MAX_PRIORITY);
          t.start();
      }
  }

   public static void main(String args[]) {
     //org.junit.runner.JUnitCore.main("TestClass");
     //Inspectee R2 = new Inspectee("TestClass");
     //Inspector R1 = new Inspector(R2.getT());
     //R2.setLock(R1.getLock());
     //R1.start();
     //R2.start();
     //R1.InspectAll(args[0]);
      LinkedList<String> test
    }
}
