/**
 * A mininmal working example of a thread, set up to work with an
 * Inspector object.
 *
 * Author: Nicholas Zufelt
 * Date: March 28, 2017
 * Course: CSC630: Data Structures and Algorithms
 */

class Inspectee implements Runnable {
    private Thread t;

    Inspectee() {
        t = new Thread(this, "Inspectee object's thread");
    }

    public Thread getT() {return t;}

    public void run() {
        waitAWhile(50);
    }
    
    /**
    * Inspects the given class for its methods with their return values if
    * callable, and adds each method to a string.
    * @param className the name of the class to be inspected
    * @return an ArrayList containing MethodReport objects for each method
    * in the class
    */
    private ArrayList<MethodReport> InspectAll() {
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
