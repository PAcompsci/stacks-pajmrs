import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class StackTestingClass {

  Stack<String> stack1 = new Stack<String>();

  @Test
  public void testEmpty() {
    stack1.push("hello");
    stack1.push("sam");
    stack1.push("ryan");
    stack1.pop();
    stack1.pop();
    stack1.pop();

    assertTrue(stack1.empty());
  }

  @Test
  public void testPeek() {
    stack1.push("hello");
    assertThat(stack1.peek(), equalTo("hello"));

    stack1.push("sam");
    assertThat(stack1.peek(), equalTo("sam"));

    stack1.pop();
    assertThat(stack1.peek(), equalTo("hello"));

  }

  @Test
  public void testPop() {
    stack1.push("hello");
    stack1.push("sam");
    stack1.push("ryan");
    stack1.pop();

    assertThat(stack1.peek(), equalTo("sam"));

    stack1.pop();

    assertThat(stack1.peek(), equalTo("hello"));

    stack1.pop();

    assertThat(stack1.peek(), equalTo(null));

  }

  @Test
  public void testPush() {
    stack1.push("hello");

    assertThat(stack1.peek(), equalTo("hello"));

    stack1.push("hi");

    assertThat(stack1.peek(), equalTo("hi"));
  }

  @Test
  public void testSearch() {
    stack1.push("hello");
    stack1.push("sam");
    stack1.push("ryan");
    int index = stack1.search("sam");
    assertThat(index, equalTo(1));
  }

  @Test
  public void testToString() {
    stack1.push("hello");
    stack1.push("ryan");
    String st = stack1.toString();
    assertThat(st, equalTo("[ryan, hello]"));
  }

  @Test
  public void testInvert() {
    stack1 = new Stack<String>();
    stack1.push("1");
    stack1.push("2");
    stack1.push("3");

    Stack<String> stack2 = stack1.invert();
    assertThat(stack2.pop(), equalTo("1"));
    assertThat(stack2.pop(), equalTo("2"));
    assertThat(stack2.pop(), equalTo("3"));
  }

  // @Test DONT NEED TO TEST THE GETTER
  // public void testAllElements() {
  //   stack1.push("hello");
  //   assertThat(stack1.allElements(), equalTo());
  // }

}
