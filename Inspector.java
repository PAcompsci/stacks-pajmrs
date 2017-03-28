/**
 * A mininmal working example of an Inspector: a class that can view the
 * stack trace of different Thread.
 *
 * Author: Nicholas Zufelt
 * Date: March 28, 2017
 * Course: CSC630: Data Structures and Algorithms
 */

import java.util.Map;
import java.util.Arrays;

class Inspector implements Runnable {
  public ArrayList<MethodReport> InspectAll() {
    
  }

   public static void main(String args[]) {
      Inspectee R2 = new Inspectee();

      Inspector R1 = new Inspector(R2.getT());
      R1.start();

      R2.start();
    }
}
