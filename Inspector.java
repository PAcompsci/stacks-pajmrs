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

public class Inspector {
  private int[] integer_options;
  private String[] string_options;
  private Thread t;
  private Thread inspecteeThread;
  
  public Inspector() {
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
      ArrayList<MethodReport> report = new ArrayList<MethodReport>();
      try {
      Class<?> c = Class.forName(className);
      Object t = c.newInstance();
      Method[] methods = c.getDeclaredMethods();
      for(Method m: methods) {
        try {
          System.out.println("\n" + m);
          Class<?>[] p = m.getParameterTypes();
          Object[] new_params = new Object[p.length];
          for(int i = 0; i < p.length; i++) {
            Random rand = new Random();
            System.out.println(p[i]);
            if(p[i].isInstance(int.class)) {
              new_params[i] = integer_options[rand.nextInt(integer_options.length)];
            }
            else if(p[i].isInstance(String.class)) {
              new_params[i] = string_options[rand.nextInt(string_options.length)];
            }
            else { 
              // Unsure of how to deal with generic types
              new_params[i] = integer_options[rand.nextInt(integer_options.length)];
            }
          }
          Object class_instance = 
          Class.forName(className).newInstance();
          System.out.println("  Input: " + Arrays.toString(new_params));
          System.out.println("  Returned: " + m.invoke(class_instance, new_params));
          report.add(new MethodReport(m.getName()));
        } catch(Throwable e) {
          System.out.println("Exception thrown during method execution: " + e);
        }
     }
   } catch(Throwable e) {
     System.out.println(e);
   }
   return report;

  }
  
  // not sure if we need this method or not
  // 
  // private void run() {
  //     for(int i = 4; i > 0; i--) {
  //         try {
  //             /* We will eventually talk about maps.  For now, just use
  //              * these lines together!
  //              */
  //             Map<Thread,StackTraceElement[]> stacks = Thread.getAllStackTraces();
  //             System.out.println(Arrays.toString(stacks.get(inspecteeThread)));
  // 
  //             // Pause the thread for 50 milliseconds
  //             Thread.sleep(50);
  //         } catch (InterruptedException e) {
  //             /* Sleep in Java needs to be caught, because it throws
  //              * exception when it completes.
  //              */
  //         }
  //     }
  // }

   public static void main(String args[]) {
     Inspector a = new Inspector();
     a.InspectAll(args[0]);
     
    }
}
