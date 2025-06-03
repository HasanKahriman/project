


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hasan
 * @param <Player>
 */
public class LinkedList <Player extends Comparable<Player>>{
    private class Node<Player>
    {
        private Player data;
        private Node<Player> link;

        public Node( )
        {
             data = null;
             link = null;
        }
        public Node(Player newData){       
            data = newData;
            link = null;
        }
        public Node(Player newData, Node<Player> linkValue)
        {
            data = newData;
            link = linkValue;
        }
     }//End of Node<T> inner class

    private Node<Player> head;
    private Node<Player> tail;
    public LinkedList(){
        head=null;
        tail=null;
    }
    //Yeni datayı sıralayarak ekler
    public void insertInOrder(Player insertItem)
   {
       Node<Player> newNode=new Node<Player>(insertItem);
       
       if(isEmpty()){
           head = tail = newNode;
           return;
       }
       else if (insertItem.compareTo(head.data)<=0){
           
           head.link=head;
           head=newNode;
           return;
       }
      
       
       Node<Player> current=head;
       Node<Player> previous=null;
       while (current!=null&&insertItem.compareTo(current.data)>0){
           previous=current;
           current=current.link;
                      
       }
       newNode.link=current;
       previous.link=newNode;
       if (current==null){
           tail=newNode;
       }
       
   }
    public boolean deleteHeadNode(){
        if (head!=null){
            head=head.link;
            return true;
        }
        else{
            return false;
        }
    }
    public int size (){
        int count=0;
        Node<Player> position=head;
        while(position!=null){
            count++;
            position=position.link;
        }
        return count;
    }
    public boolean contains(Player item){
        return(find(item)!=null);
    }
    private Node<Player> find(Player target){
        Node<Player>  position=head;
        Player itemAtPosition;
        while(position!=null){
            itemAtPosition=position.data;
            if (itemAtPosition.equals(target)){
                return position;
            }
            position=position.link;
        }
        return null;
        
    }
    public Player findData(Player target){
        return find(target).data;
    }
    public void outputList(){
        Node<Player> position=head;
        while(position!=null){
            System.out.println(position.data);
            position=position.link;
        }
        
    }
    public boolean isEmpty(){
        return (head==null);
    }
    public void clear(){
        head=null;
    }
    public boolean equals(Object otherObject){
        if (otherObject==null){
            return false;
        }
        else if (otherObject.getClass()!=getClass()){
            return false;
        }
        else{
            LinkedList<Player> otherList=(LinkedList<Player>)otherObject;
            if(otherList.size()!=size()){
                return false;
            }
            Node<Player> position=head;
            Node<Player> otherPosition=otherList.head;
            while(position!=null){
                if(position.data!=otherPosition.data){
                    return false;
                }
                position=position.link;
                otherPosition=otherPosition.link;
            }
            return true;
            
        }
    }
}
