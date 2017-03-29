/**
 *
 * Author: Jocelyn Shen, Michelle Chao, Sam Xifaras, Ryan Goggins
 * Date: March 28, 2017
 * Course: CSC630: Data Structures and Algorithms
 */

import java.util.*;
import java.lang.reflect.*;

public class Inspector {
  private Thread t;
  private Thread inspecteeThread;
  
  public Inspector() {};
  
  
  private ArrayList<MethodReport> InspectAll(String className) {
    ArrayList<MethodReport> report = new ArrayList<MethodReport>();
    try {
        Class<?> c = Class.forName(className);
        Object t = c.newInstance();
        Method[] methods = c.getDeclaredMethods();
        for (Method m: methods) {
            System.out.println("\n" + m);
            Parameter[] p = m.getParameters();
            Type type = p[0].getParameterizedType();
            // TODO: Invoke method w/ argument(s) of proper type
            
            
            
            
            
            report.add(new MethodReport(m.getName()));
        }
    } catch (Throwable e) {
        System.err.println(e);
    }
    return report;
  }
  
  private void run() {
      for(int i = 4; i > 0; i--) {
          try {
              /* We will eventually talk about maps.  For now, just use
               * these lines together!
               */
              Map<Thread,StackTraceElement[]> stacks = Thread.getAllStackTraces();
              System.out.println(Arrays.toString(stacks.get(inspecteeThread)));

              // Pause the thread for 50 milliseconds
              Thread.sleep(50);
          } catch (InterruptedException e) {
              /* Sleep in Java needs to be caught, because it throws
               * exception when it completes.
               */
          }
      }
  }

   public static void main(String args[]) {
     Inspector a = new Inspector();
     a.InspectAll("LinkedList");
     
    }
}
