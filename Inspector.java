/**
 * Inspects a class for its methods. If the methods have primitive or common
 * arguments, the Inspector calls these methods with arguments.
 *
 * Author: Jocelyn Shen, Michelle Chao, Sam Xifaras, Ryan Goggins
 * Date: April 2, 2017
 * Sources: Java reflect API
 *          http://stackoverflow.com/questions/6094575/creating-an-instance-using-the-class-name-and-calling-constructor
 */
 
 /*
 
 NOTE: While we were able to successfully obtained stack traces from recursive functions, we
 were unable to determine from the stack traces if the method was or was not
 a recursive function. 
 */

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.lang.reflect.*;

import static org.junit.Assert.assertFalse;

public class Inspector implements Runnable{
  private Thread t;
  private Thread inspecteeThread;
  private ArrayList<Map<Thread, StackTraceElement[]>> data;

  public Inspector() {
    inspecteeThread = null;
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
        if (elements == null) continue;
        for (int j = elements.length - 1; j >= 0; j--) {
          entry.push(elements[j].getMethodName());
        }
        timeline.add(entry);
      }

      // Clean up the timeline by removing any entries that do not have a method from the class on the top
      for (int i = 0; i < timeline.size(); i++) {
        // Remove non-class methods from the top of the stack
        while ((!methodNames.contains(timeline.get(i).peek())) && 
               (timeline.get(i).allElements().size() != 0)) {
          timeline.get(i).pop();
          //i--;
        }

        // Remove stack trace if it has no elements left
        if (timeline.get(i).allElements().size() == 0) {
          timeline.remove(i);
          i--;
          continue;
        }

        // Remove non-class methods from the bottom of the stack
        Stack<String> inverted = timeline.get(i).invert();
        while ((!methodNames.contains(inverted.peek())) && (inverted.allElements().size() != 0)) {
          inverted.pop();
        }
        timeline.set(i, inverted.invert());

        // Remove stack trace if it has no elements left
        if (timeline.get(i).allElements().size() == 0) {
          timeline.remove(i);
          i--;
        }
      }

      for (int i = 0; i < report.size(); i++) {
        report.get(i).setTimeline(timeline);
      }

      return report;
  }


  public void run() {
    int counter = 0;
    ArrayList<Map<Thread, StackTraceElement[]>> list = new ArrayList<Map<Thread, StackTraceElement[]>>();
      while(inspecteeThread.getState()!=Thread.State.TERMINATED)
      {
        list.add(Thread.getAllStackTraces());
        counter++;
      }

      this.data = list;

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
     Inspector ins = new Inspector();
     ArrayList<MethodReport> report = ins.InspectAll("TestClass");
     for (MethodReport r : report) {
       if (r.getMethodName() == "addRecursive") {
         boolean val = r.isBranchedRecursive();
       }
       if (r.getMethodName() == "notRecursive") {
         //assertFalse(r.isBranchedRecursive());
       }
     }
      org.junit.runner.JUnitCore.main("UnitTests");

    }
}
