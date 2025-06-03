/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hasan
 * @param <Player>
 */
public class Stack<Player> {
    private class Node<Player>{
        private Player item;
        private Node<Player> link;
        public Node(){
            item=null;
            link=null;
        }

        public Node(Player item, Node<Player> link) {
            this.item = item;
            this.link = link;
        }
        
    }
    private Node head;
    public Stack(){
        head=null;
    }
    public void push(Player newItem){
        head=new Node(newItem,head);
    }
    public Player pop(){
        if(head==null){
            throw new IllegalStateException();
        }
        else{
            Player returnItem=(Player) head.item;
            head=head.link;
            return returnItem;
            
        }
    }
    public boolean isEmpty(){
        return (head==null);
    }
    
}
