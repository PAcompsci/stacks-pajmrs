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

import java.util.*;
import java.lang.reflect.*;

public class Inspector implements Runnable{
  private int[] integer_options;
  private String[] string_options;
  private Thread t;
  private Thread inspecteeThread;

  public Inspector(Thread iT)
    iT = inspecteeThread;
    integer_options = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    string_options = new String[]{"dog", "cat," ,"house", "computer science"};
  }

    /**
    * Inspects the given class for its methods with their return values if
    * callable, and adds each method to a string.
    * @param className the name of the class to be inspected
    * @return an ArrayList containing MethodReport objects for each method
    * in the class
    */
    private ArrayList<MethodReport> InspectAll(String className) {
    
  }


  public void run()
  {
      while(thread.getState()!=Thread.State.TERMINATED)
      {
        try
        {
          Map<Thread,StackTraceElement[]> stacks = Thread.getAllStackTraces();
          System.out.println(Arrays.toString(stacks.get(inspecteeThread)));
        }
        catch (InterruptedException e)
        {
        }
      }
  }

  public void start () {
      if (t == null) {
          t = new Thread(this, "Inspector object's thread");
          t.start();
      }
  }

   public static void main(String args[]) {
     Inspectee R2 = new Inspectee();
     Inspector R1 = new Inspector(R2.getT());
     R1.start();
     R2.start();
     R1.InspectAll(args[0]);

    }
}
