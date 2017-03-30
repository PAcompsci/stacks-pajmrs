/**
 * A mininmal working example of a thread, set up to work with an
 * Inspector object.
 *
 * Author: Nicholas Zufelt
 * Date: March 28, 2017
 * Course: CSC630: Data Structures and Algorithms
 */
package mwe;
class Inspectee implements Runnable {
    private Thread t;

    Inspectee() {
        t = new Thread(this, "Inspectee object's thread");
    }

    public Thread getT() {return t;}

    public void run() {
        waitAWhile(50);
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
