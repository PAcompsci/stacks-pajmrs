/**
 * Get the methods of a class specified at runtime and list its methods.
 *
 * Author: Nicholas Zufelt
 * Date: March 28, 2017
 * Course: CSC630 Data Structures and Algorithms
 */
package mwe;
import java.lang.reflect.*;

public class GetMethods {

    public void foo() {System.out.println("Hello from foo!");}

    public int bar() {return 84;}

    public String baz(String param) {return param;}

    public static void main(String args[])
    {
        String className = args[0];
        try {
            Class<?> c = Class.forName(className);
            Object t = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            for (Method m: methods) {
                System.out.println("\n" + m);

                // Danger zone!  Only run methods when you know the class
                if (!m.getName().equals("main") && !m.getName().equals("baz")) {
                    if (m.getReturnType().equals(Void.TYPE))
                        m.invoke(t);
                    else
                        System.out.println(m.invoke(t));
                }
                if (m.getName().equals("baz"))
                    System.out.println(m.invoke(t, "my parameter String goes here!"));
            }
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}
