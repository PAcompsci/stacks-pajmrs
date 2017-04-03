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
    public void testDataCollection() {
        Inspector ins = new Inspector();
        ArrayList<MethodReport> report = ins.InspectAll("TestClass");
        for (MethodReport r : report) {
            if (r.getMethodName() == "addRecursive") {
                assertTrue(r.isRecursive());
                //assertFalse(r.isBranchedRecursive());
            }
            if (r.getMethodName() == "notRecursive") {
                assertFalse(r.isRecursive());
            }
        }
    }

}
