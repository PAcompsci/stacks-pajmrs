/**
 * A mininmal working example of an Inspector: a class that can view the
 * stack trace of different Thread.
 *
 * Author: Ryan, Sam, Jocelyn, Michelle
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
    //.getAllStackTraces
    Map<Thread,StackTraceElement[]> stacks = Thread.getAllStackTraces();
    for (Thread newT : stacks) {
      StackTraceElement[] newStack = stacks.get(newT);
      String recentMethod = "";
      for (int i = 0; i < newStack.length; i++) {
        //this goes through the specific stacktrace element array and checks whether the previous
        //contained the same method
        StackTraceElement stackElement = newStack[i];
        if (stackElement.getMethodName().equals(recentMethod)) {
          //2 methods used in a row?
          //does this mean that its recursive??
          return true;
        }
        recentMethod = stackElement.getMethodName();
      }
      //get the new thread iterate through the array of stack traceelements
      //look at each stack trace element
      //determine whether it is recursive
    }
    return false;
  }

  // not done
  public boolean isBranchedRecursive() {
    //branched recursive is when the method calls another method and then goes back and returns to being recursive


    return false;

  }
}
