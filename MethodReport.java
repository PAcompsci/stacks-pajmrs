/**
 * A mininmal working example of an Inspector: a class that can view the
 * stack trace of different Thread.
 *
 * Author: Jocelyn Shen, Michelle Chao, Sam Xifaras, Ryan Goggins
 * Date: March 28, 2017
 * Course: CSC630: Data Structures and Algorithms
 */
 
 
 // NOTE: Use reflection API to call methods by name

import java.util.Arrays;

public class MethodReport{
  private String methodName;
  private LinkedList<Stack<String>> timeline;
  
  public MethodReport(String name) {
    methodName = name;
  }

  public LinkedList getTimeline() {
    return timeline;
  }

  /**
  * Return true if the method is recursive (branched or otherwise).
  */
  
  // not done
  public boolean isRecursive() {
    return false;

  }
  // not done
  public boolean isBranchedRecursive() {
    return false;

  }
}
