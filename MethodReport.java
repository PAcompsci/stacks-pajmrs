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
import java.util.ArrayList;

public class MethodReport{
  private String methodName;
  private LinkedList<Stack<String>> timeline;

  public MethodReport(String name) {
    methodName = name;
  }

  /**
  * Returns timeline
  */
  public LinkedList getTimeline() {
    return timeline;
  }

  /**
  * Return true if the method is recursive (branched or otherwise).
  */
  public boolean isRecursive() {
    String previous = "";
    for (int r = 0; r < timeline.size(); r++) {
      if (timeline.get(r).peek().equals(previous)) {
        return true;
      }
      previous = timeline.get(r).peek();
    }
    return false;
  }

  /**
  * Checks if a method is recursive (branched)
  */
  public boolean isBranchedRecursive() {
    //if it goes up by one
    //branch recursion would be multiple calls ina stack but not increasing/decreasing by one
    //only returns true
    ArrayList<Integer> totalMethodCalls = new ArrayList<Integer>(timeline.size()-1);
    //because array is initialized to empty
    for (int i = 0; i < totalMethodCalls.size(); i++) {
      totalMethodCalls.set(i, 0);
    }
    for (int r = 0; r < timeline.size(); r++) {
      //check if monotonic
      //NEED THE METHOD: public LinkedList<T> allElements() {
      LinkedList<String> singleStack = timeline.get(r).allElements();
      int counter = 0;
      for (int i = 0; i < singleStack.size(); i++) {
        if (singleStack.get(i).equals(methodName)) {
          totalMethodCalls.set(r, totalMethodCalls.get(r) + 1);
        }
      }
    }
    //Now we have the arraylist of number of method calls in each stack
    //now we need to check the patterns between the lines
    //if the list follows and up and then down patter -> linearly recursive
    //if the list goes up and down and up and down (etc.) -> branched recursivity
    int max = totalMethodCalls.get(0);
    for (int i = 1; i < totalMethodCalls.size(); i++) {
      //this portion tests whether the list goes up and down once or multiple times
      if (totalMethodCalls.get(i) < max) {
        //then the decline starts if its linear

        for (int x = i; x < totalMethodCalls.size(); x++) {
          if (totalMethodCalls.get(x) < max) {
            max = totalMethodCalls.get(x);
          }
          else {
            //there was another spike in the number of method calls
            return true;
          }
        }
      }
    }
    return false;
  }
}
