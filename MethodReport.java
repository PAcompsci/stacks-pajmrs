/**
 * A mininmal working example of an Inspector: a class that can view the
 * stack trace of different Thread.
 *
 * Author: Jocelyn Shen, Michelle Chao, Sam Xifaras, Ryan Goggins
 * Date: March 28, 2017
 * Course: CSC630: Data Structures and Algorithms
 */

import java.util.Arrays;

public class MethodReport{
  private String methodName;
  private LinkedList<Stack<String>> timeline;

  public LinkedList getTimeline() {
    return timeline;
  }

  /**
  * Return true if the method is recursive (branched or otherwise).
  */
  public boolean isRecursive() {

  }

  public boolean isBranchedRecursive() {

  }
}
