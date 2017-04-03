/**
 * The Inspectee class works with conjunction with the Inspector class. The
 * Inspectee class runs the test-class methods, and the Inspector class 
 * observes the stack traces in the Inspectee class.
 *
 * Author: Jocelyn Shen, Michelle Chao, Sam Xifaras, Ryan Goggins
 * April 2, 2017
 */
 
import java.util.*;
import java.lang.reflect.*;

class Inspectee implements Runnable {
    // options for parameters in method invoking
    private int[] integer_options;
    private String[] string_options;
    private char[] char_options;
    private boolean[] boolean_options;
    
    private Thread t;
    private Method[] methods;
    ArrayList<MethodReport> report;
    private String className;
    private Object lock;

    public void setLock(Object obj) { 
      this.lock = obj; 
    }

    public Inspectee(String clazz) {
      className = clazz;
      integer_options = new int[]{100}; 
      string_options = new String[]{"dog", "cat," ,"house", "computer science"};
      char_options = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
                      'p','q','r','s','t','u','v','w','x','y','z'};   
      boolean_options = new boolean[]{true,false};
      t = new Thread(this, "Inspectee object's thread");
      report = this.getMethods(this.className);
    }

    public Thread getT() {
      return t;
    }
    
    public ArrayList<MethodReport> getReport() {
      return report;
    }
    
    // Runs the thread
    public void run() throws NullPointerException {
        int n = 1;
        // if (lock == null) {
        //     throw new NullPointerException();
        // }
      for(Method m: methods) {
        // Go through each method and invoke it with parameters, if the appropriate
        // parameters can be obtained.
        try {
          System.out.println("\n" + m.getName());
          Class<?>[] p = m.getParameterTypes();
          Object[] new_params = new Object[p.length];
          for(int i = 0; i < p.length; i++) {
            Random rand = new Random();
            if(p[i].isInstance(int.class)) {
              // Parameter is an int
              new_params[i] = integer_options[rand.nextInt(integer_options.length)];
            }
            else if(p[i].isInstance(String.class)) {
              // Parameter is a String
              new_params[i] = string_options[rand.nextInt(string_options.length)];
            }
            else if(p[i].isInstance(char.class)) {
              // Parameter is a character
              new_params[i] = char_options[rand.nextInt(char_options.length)];
            }
            else if(p[i].isInstance(boolean.class)) {
              // Parameter is a boolean
              new_params[i] = boolean_options[rand.nextInt(boolean_options.length)];
            }
            else {
              // Unsure of how to deal with generic parameter types
              new_params[i] = integer_options[rand.nextInt(integer_options.length)];
            }
          }
          Object class_instance = Class.forName(className).newInstance();
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
    
    
    public static void main(String[] args) {
      Inspectee r = new Inspectee("TestClass");
      r.run();
    }
}
