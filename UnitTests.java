/**
 * Compiled unit test file
 *
 * Date: 4/2/17
 */

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.CoreMatchers.not;

import org.junit.Test;
import java.util.*;

public class UnitTests {

    @Test
    public void testIsRecursive() {
        // NOTE: Not guaranteed to succeed

        Inspector ins = new Inspector();
        ArrayList<MethodReport> report = ins.InspectAll("TestClass");
        for (MethodReport r : report) {
            if (r.getMethodName() == "addRecursive") {
                assertTrue(r.isRecursive());
            }
            if (r.getMethodName() == "notRecursive") {
                assertFalse(r.isRecursive());
            }
        }
    }

    @Test
    public void testIsBranchedRecursive() {
        Inspector ins = new Inspector();
        ArrayList<MethodReport> report = ins.InspectAll("TestClass");
        for (MethodReport r : report) {
            if (r.getMethodName() == "addRecursive") {
                boolean val = r.isBranchedRecursive();
                assertFalse(val);
            }
            if (r.getMethodName() == "notRecursive") {
                //assertFalse(r.isBranchedRecursive());
            }
        }
    }

}
