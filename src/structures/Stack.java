package structures;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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

    public boolean isEmpty(){
        return (head==null);
    }
    
}
