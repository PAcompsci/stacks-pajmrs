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

  public Inspector(){
    inspecteeThread = null;
    integer_options = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    string_options = new String[]{"dog", "cat," ,"house", "computer science"};
    data = null;
  }

    /**
    * Inspects the given class for its methods with their return values if
    * callable, and adds each method to a string.
    * @param className the name of the class to be inspected
    * @return an ArrayList containing MethodReport objects for each method
    * in the class
    */
    public ArrayList<MethodReport> InspectAll(String className) throws NotImplementedException{
      Inspectee R2 = new Inspectee(className);
      this.inspecteeThread = R2.getT();
      this.start();
      R2.start();

      // Poll until the thread is terminated
      while (this.t.getState()!=Thread.State.TERMINATED) {
        try { Thread.sleep(50); } catch (Exception e) {}
      }

      ArrayList<MethodReport> report = R2.getReport();

      // Create a list of method names
      ArrayList<String> methodNames = new ArrayList<String>();
      for (int i = 0; i < report.size(); i++) {
        methodNames.add(report.get(i).getMethodName());
      }

      // Create the timeline object by getting method names of all the StackTraceElement objects that belong to the Inspectee thread
      LinkedList<Stack<String>> timeline = new LinkedList<Stack<String>>();
      for (int i = 0; i < data.size(); i++) {
        Stack<String> entry = new Stack<String>();
        StackTraceElement[] elements = data.get(i).get(inspecteeThread);
        try {
          for (int j = elements.length - 1; j >= 0; j--) {
            entry.push(elements[j].getMethodName());
          }
          timeline.add(entry);
        }
        catch (NullPointerException e) {
          e.printStackTrace();
        }
      }

      // Clean up the timeline by removing any entries that do not have a method from the class on the top
      for (int i = 0; i < timeline.size(); i++) {
        if (methodNames.contains(timeline.get(i).peek()) == false) {
          timeline.remove(i);
          i--;
        }
      }

      for (int i = 0; i < report.size(); i++) {
        report.get(i).setTimeline(timeline);
      }

      return report;
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
  }

  public void start () {
      if (t == null) {
          t = new Thread(this, "Inspector object's thread");
          t.setPriority(Thread.MAX_PRIORITY);
          t.start();
      }
  }

   public static void main(String args[]) {
     org.junit.runner.JUnitCore.main("UnitTests");
    }
}
