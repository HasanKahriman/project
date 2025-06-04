package structures;



     
/**
 *
 * @author hasan
 * @param <Object>
 */
public class Stack<Object> {

     private Object[] stackArray;
    private int topIndex;
    private int stackSize;

    public Stack(int capacity) {
        this.stackSize = capacity;
        this.stackArray=new Object[stackSize];
        this.topIndex = -1;
    }

    public void push(Object obj) {
        if (topIndex == this.stackSize - 1 ){
            System.out.println("Stack is full");
            System.exit(0);
        }else {
            topIndex += 1;
            stackArray[topIndex] = obj;
        }
    }

    public Object pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            System.exit(0);
        }
        Object objectToBeDeleted = stackArray[topIndex];
        stackArray[topIndex] = null;
        topIndex -= 1;
        return objectToBeDeleted;
    }

    public boolean isEmpty() {
        return topIndex == -1;
    }

    public int size() {
        return topIndex+1;
    }

}