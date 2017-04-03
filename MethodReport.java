/**
 * Each MethodReport represents a method from a class, and contains a LinkedList
 * of StackTraces from method call.
 *
 * Author: Jocelyn Shen, Michelle Chao, Sam Xifaras, Ryan Goggins
 * Date: April 2, 2017
 */

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

   public String getMethodName() { return this.methodName; }

   public void setTimeline(LinkedList<Stack<String>> timeline) { this.timeline = timeline;}

   /**
   * Return true if the method is recursive (branched or otherwise).
   */
   public boolean isRecursive() {
     String previous = "";
     for (int r = 0; r < timeline.size(); r++) {
       if (timeline.get(r).peek().equals(this.methodName) &&
               timeline.get(r).peek().equals(previous)) {
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
     // if it goes up by one
     // branch recursion would be multiple calls ina stack but not increasing/decreasing by one
     // only returns true
     Integer[] totalMethodCalls = new Integer[timeline.size()];
     // because array is initialized to empty
     for (int i = 0; i < totalMethodCalls.length; i++) {
       totalMethodCalls[i] = 0;
     }
     for (int r = 0; r < timeline.size(); r++) {
       // check if monotonic
       // NEED THE METHOD: public LinkedList<T> allElements() {
       LinkedList<String> singleStack = timeline.get(r).allElements();
       int counter = 0;
       for (int i = 0; i < singleStack.size(); i++) {
         if (singleStack.get(i).equals(methodName)) {
           totalMethodCalls[r] = totalMethodCalls[r] + 1;
         }
       }
     }

     for (int i = 0; i < timeline.size(); i++) {
       if (timeline.get(i).invert().peek().equals(this.methodName)) {
         System.out.println(totalMethodCalls[i]);
       }
     }
     // Now we have the arraylist of number of method calls in each stack
     // now we need to check the patterns between the lines
     // if the list follows and up and then down patter -> linearly recursive
     // if the list goes up and down and up and down (etc.) -> branched recursivity
     int max = totalMethodCalls[0];
     for (int i = 1; i < totalMethodCalls.length; i++) {
       // this portion tests whether the list goes up and down once or multiple times
       if (totalMethodCalls[i] < max) {
         // then the decline starts if its linear

         for (int x = i; x < totalMethodCalls.length; x++) {
           if (totalMethodCalls[x] < max) {
             max = totalMethodCalls[x];
           }
           else {
             // there was another spike in the number of method calls
             return true;
           }
         }
       }
     }
     return false;
   }
 }