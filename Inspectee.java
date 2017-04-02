/**
 * A mininmal working example of a thread, set up to work with an
 * Inspector object.
 *
 * Author: Nicholas Zufelt
 * Date: March 28, 2017
 * Course: CSC630: Data Structures and Algorithms
 */
import java.util.*;
import java.lang.reflect.*;

class Inspectee implements Runnable {
    private int[] integer_options;
    private String[] string_options;
    private Thread t;
    private Thread inspecteeThread;
    private Method[] methods;
    ArrayList<MethodReport> report;
    private String className;

    private Object lock;

    public void setLock(Object obj) { this.lock = obj; }



    public Inspectee(String clazz) {
      className = clazz;
      integer_options = new int[]{100};//1,2,3,4,5,6,7,8,9,10,11,12,13,14};
      string_options = new String[]{"dog", "cat," ,"house", "computer science"};
      t = new Thread(this, "Inspectee object's thread");
      report = this.getMethods(this.className);//new ArrayList<MethodReport>();
    }

    public Thread getT() {
      return t;
    }
    
    public ArrayList<MethodReport> getReport() {
      return report;
    }

    public void run() throws NullPointerException {
        int n = 10000;

        if (lock == null) {
            throw new NullPointerException();
        }
      for(Method m: methods) {
        try {
          //System.out.println("\n" + m);
          Class<?>[] p = m.getParameterTypes();
          Object[] new_params = new Object[p.length];
          for(int i = 0; i < p.length; i++) {
            Random rand = new Random();
            //System.out.println(p[i]);
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
          System.out.println("INVOKING");
          for (int i = 0; i < n; i++){
                m.invoke(class_instance, new_params);
            }

          //System.out.println("  Input: " + Arrays.toString(new_params));
          //System.out.println("  Returned: " + m.invoke(class_instance, new_params));
          //System.out.println("  Is recursive or not: " );
        } catch(Throwable e) {
          System.out.println("Exception thrown during method execution: " + e);
        }
      }
    }
    
    /**
    * Inspects the given class for its methods with their return values if
    * callable, and adds each method to a string.
    * @param className the name of the class to be inspected
    * @return an ArrayList containing MethodReport objects for each method
    * in the class
    */
    private ArrayList<MethodReport> getMethods(String className) {
      report = new ArrayList<MethodReport>();
      try {
      Class<?> c = Class.forName(className);
      Object t = c.newInstance();
      this.methods = c.getDeclaredMethods();
      for(Method m: methods) {
        try {
          report.add(new MethodReport(m.getName()));
        } catch(Throwable e) {
          System.out.println(e);
        }
     }
   } catch(Throwable e) {
     System.out.println(e);
   }
   return report;
  }

    // This method was made to be recursive for the Inspector's perusal
    public void waitAWhile(int n) {
        if (n == 1) return;
        try {
            // Total sleep time = n * 20 milliseconds
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
        waitAWhile(n-1);
    }

    public void start() {
        t.start();
    }
}
