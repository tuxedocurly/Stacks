import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

  /**
   * This is an Exception class.
   * Throw this exception if a method that is supposed to return
   * a stack element can not return anything because the Stack is empty.
   *
   * You can throw an instance of this exception by writing this:
   *       throw new StackUnderflow();
   * which creates a StackUnderflow object with the "new" keyword,
   * and throws that StackUnderflow object with the "throw" keyword.
   */
  public static class StackUnderflowException extends RuntimeException {
    // no body is required
  }

  /**
   * This is the Stack interface.
   * Do not modify it.
   */
  // no change required
  interface Stack<T> {
    /**
     * After calling push, t is the top element of this Stack.
     */
    void push(T t);

    /**
     * peek returns the top element of this Stack, leaving the Stack unchanged,
     * or if the Stack is empty throws a StackUnderflowException.
     */
    T peek();

    /**
     * pop remove the top element of this Stack, and returns that element,
     * or if the Stack is empty throws a StackUnderflowException.
     */
    T pop();

    /**
     *
     * @return true iff (if and only if) this Stack is empty, else false.
     */
    boolean isEmpty();

    /**
     * return the size of this Stack.
     */
    int size();
  }

  /**
   * This interface just fixes the generic type T as String
   * Do not modify it.
   */
  interface StringStack extends Stack<String> {
    // no body is necessary, as this interface inherits all members of the Stack super-interface

    @Override
    void push(String s);

    @Override
    String peek();

    @Override
    String pop();

    @Override
    int size();
  }

  /**
   * Modify this StackImpl class, or write your own.
   */
  public static class StackImpl<T> implements Stack<T> {
    private ArrayList<T> arr;
    private int topIndex = -1;


    /**
     * Constructor: initilalizes a new StackImpl instance.
     */
    public StackImpl() {
      // TODO
      this.arr = new ArrayList<T>();
    }
    
    @Override
    public void push(T t) {
      topIndex += 1;
      arr.add(t);
    }

    @Override
    public T peek() {
      // base case to ensure the stack isn't empty
      if (topIndex == -1) {
        throw new StackUnderflowException();
      } else {
          return arr.get(topIndex);
      }
    }

    @Override
    public T pop() {
      if (topIndex == -1) {
        throw new StackUnderflowException();
      } else {
          // simplifies the removal and returning of the item on the top of the stack
          int tempTopIndex = topIndex;
          topIndex--;
          // the .remove() method returns the removed item
          return arr.remove(tempTopIndex);
      }
    }

    @Override
    public int size() {
      return arr.size();
    }
    @Override
    public boolean isEmpty() {
      return topIndex == -1;
    }
  }

  
  public static void main(String[] args) {

    try (Scanner in = new Scanner(System.in)) {
      // TODO
      StackImpl<String> stack = new StackImpl<>();
      // read strings from System.in until there are no more strings to read
      while (in.hasNext()) {
        try {
          // trimming input to remove unintended characters
          String s = in.nextLine().trim();

          if (s.equalsIgnoreCase("peek")) {
            System.out.println(stack.peek());
          } else if (s.equalsIgnoreCase("pop")) {
            System.out.println(stack.pop());
          } else if (s.equalsIgnoreCase("size")) {
            System.out.println(stack.size());
          } else {
            stack.push(s);
            System.out.println(stack.size());
          }
        } catch (StackUnderflowException e) {
          System.out.println("SU");
          continue;
        }
      }
    }
  }
}
