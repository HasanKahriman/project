package structures;


public class Stack {
    private Object[] stackArray;
    private int topIndex;
    private int stackSize;

    public Stack(int capacity) {
        this.stackSize = capacity;
        this.stackArray = new Object[stackSize];
        this.topIndex = -1;
    }

    public void push(Object obj) {
        if (topIndex == this.stackSize - 1 ){
            System.out.println("Stack is full");
            System.exit(0);
        }else {
            topIndex += 1;
            stackArray[topIndex] = obj;

/**
 *
 * @author hasan
 * @param <Object>
 */
public class Stack<Object> {

    private class Node<Object>{
        private Object item;
        private Node<Object> link;

        public Node(){
            item=null;
            link=null;
        }

        public Node(Object item, Node<Object> link) {
            this.item = item;
            this.link = link;

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

    private Node<Object> head;

    public Stack(){
        head=null;
    }
    public void push(Object newItem){
        head=new Node(newItem,head);
    }

    public Object pop(){
        if(head==null){
            throw new IllegalStateException();
        }
        else{
            Object returnItem=(Object) head.item;
            head=head.link;
            return returnItem;
            
        }

    }

    public int size() {
        return topIndex + 1;
    }
}