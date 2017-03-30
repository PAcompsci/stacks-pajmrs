/**
 * Created by Samuel on 3/30/2017.
 */
public class TestClass {

    public int addRecursive(int n) {
        if (n == 0) {
            return 0;
        } else {
            return n + addRecursive(n - 1);
        }
    }

    public int notRecursive(int n) {
        return addRecursive(n);
    }

}
