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
    private Thread t;
    private Thread inspecteeThread;

    public Inspector(Thread iT) {
        inspecteeThread = iT;
    }

    public void run() {
        for(int i = 4; i > 0; i--) {
            try {
                /* We will eventually talk about maps.  For now, just use
                 * these lines together!
                 */
                Map<Thread,StackTraceElement[]> stacks = Thread.getAllStackTraces();
                System.out.println(Arrays.toString(stacks.get(inspecteeThread)));

                // Pause the thread for 50 milliseconds
                Thread.sleep(50);
            } catch (InterruptedException e) {
                /* Sleep in Java needs to be caught, because it throws
                 * exception when it completes.
                 */
            }
        }
    }

    public void start () {
        /* All examples I found online have this `if (t == null)` line.
         * I don't really see the necessity of it, but I didn't want to
         * just delete it and have that cause y'all all kinds of bugs.
         */
        if (t == null) {
            t = new Thread(this, "Inspector object's thread");
            t.start();
        }
    }

   public static void main(String args[]) {
      Inspectee R2 = new Inspectee();

      Inspector R1 = new Inspector(R2.getT());
      R1.start();

      R2.start();
    }
}
