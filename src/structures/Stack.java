package structures;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hasan
 * @param <T>
 */
public class Stack<T> {

    private class Node<T>{
        private T item;
        private Node<T> link;

        public Node(){
            item=null;
            link=null;
        }

        public Node(T item, Node<T> link) {
            this.item = item;
            this.link = link;
        }
        
    }
    private Node<T> head;

    public Stack(){
        head=null;
    }
    public void push(T newItem){
        head=new Node(newItem,head);
    }

    public T pop(){
        if(head==null){
            throw new IllegalStateException();
        }
        else{
            T returnItem=(T) head.item;
            head=head.link;
            return returnItem;
            
        }
    }

    public boolean isEmpty(){
        return (head==null);
    }
    
}
