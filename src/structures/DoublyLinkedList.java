package structures;

import model.Player;

import java.util.NoSuchElementException;

public class DoublyLinkedList{

    private class TwoWayNode
    {
        private Player element;
        private TwoWayNode previous;
        private TwoWayNode next;

        public TwoWayNode( )
        {
            element = null;
            next = null;
            previous = null;
        }

        public TwoWayNode(Player newElement, TwoWayNode previousNode, TwoWayNode nextNode)
        {
            element = newElement;
            next = nextNode;
            previous = previousNode;
        }
    }

    public class DoublyLinkedIterator{
        private TwoWayNode position = null;

        public DoublyLinkedIterator(){
            this.position = head;
        }

        public void restart(){
            this.position = head;
        }

        public Player next(){
            if(!hasNext())
                throw new NoSuchElementException();
            Player player = position.element;
            position = position.next;
            return player;
        }

        public boolean hasNext()
        {
            return (position != null);
        }

        public Player peek()
        {
            if (!hasNext())
                throw new IllegalStateException();
            return position.element;
        }

        public void insertHere(Player newElement)
        {
            if (position == null && head != null)
            {
                TwoWayNode temp = head;
                while (temp.next != null)
                {
                    temp = temp.next;
                }
                temp.next = new TwoWayNode(newElement, temp, null);
                tail =temp.next;
            }
            else if (head == null || position.previous == null)
                DoublyLinkedList.this.addToStart(newElement);
            else
            {
                // Insert before the current position
                TwoWayNode temp = new TwoWayNode(newElement, position.previous, position);
                position.previous.next = temp;
                position.previous = temp;
            }
        }

        public void delete()
        {
            if (position == null)
                throw new IllegalStateException();
            else if (position.previous == null)
            {
                // Deleting first node
                head = head.next;
                position = head;
                if (head == null){
                    tail = null;
                }else {
                    head.previous = null;
                }
            }
            else if (position.next == null)
            {
                // Deleting last node
                position.previous.next = null;
                tail = position.previous;
                position = null;
            }
            else
            {
                position.previous.next = position.next;
                position.next.previous = position.previous;
                position = position.next;
            }
        }
    }

    private TwoWayNode head;
    private TwoWayNode tail;

    public DoublyLinkedIterator iterator(){
        return new DoublyLinkedIterator();
    }

    public DoublyLinkedList(){
        head = null;
    }

    public void addToStart(Player newElement)
    {
        TwoWayNode newHead = new TwoWayNode(newElement, null, head);
        if (head != null)
        {
            head.previous = newHead;
        }
        head = tail = newHead;
    }

    public boolean deleteHeadNode( )
    {
        if (head != null)
        {
            head = head.next;
            if (head == null){
                tail = null;
            }
            return true;
        }
        else
            head.previous = null;
        return false;
    }

    public int size( )
    {
        int count = 0;
        TwoWayNode position = head;
        while (position != null)
        {
            count++;
            position = position.next;
        }
        return count;
    }

    public void outputList( )
    {
        TwoWayNode position = head;
        while (position != null)
        {
            System.out.println(position.element);
            position = position.next;
        }
    }

    public boolean isEmpty( )
    {
        return (head == null);
    }

    public void clear( )
    {
        head = null;
        tail = null;
    }

    public void insertSorted( Player newElement){
        TwoWayNode nodeToBeInserted = new TwoWayNode(newElement, head, tail);

        if (head == null){
            addToStart(newElement);
            return;
        }

        // Eklenecek oyuncu daha one eklenecekse
        if (newElement.compareTo(head.element) < 0){
            addToStart(newElement);
            return;
        }

        TwoWayNode pointer = head;
        while (pointer.next != null && newElement.compareTo(pointer.element) >= 0){
            pointer = pointer.next;
        }

        // Eklenecek oyuncu sona eklenecekse taile baglar
        if (newElement.compareTo(pointer.element) >= 0){
            TwoWayNode newTail = new TwoWayNode(newElement, tail, null);
            tail.next =  newTail;
            tail = newTail;
            return;
        }

        //Ortaya eklenecekse player
        else {
            TwoWayNode beforePointer = pointer.previous;
            TwoWayNode newMiddle = new TwoWayNode(newElement, beforePointer, beforePointer.previous);
            beforePointer.next = newMiddle;
            pointer.previous = newMiddle;
        }
    }

    public Player find(String name, String surname){
        TwoWayNode position = head;
        while (position != null){
            Player player = position.element;
            if (player.getName().equalsIgnoreCase(name) && player.getSurname().equalsIgnoreCase(surname)){
                return player;
            }
            position = position.next;
        }
        return null;
    }

    public boolean deletePlayer(String name, String surname){
        //donguyu gezdigimizde karsilastirma yapiacak her bir eleman
        TwoWayNode position = head;

        // eger eleman null degil ise
        while (position != null){

            // Player objesi yani datasi alinir
            Player player = position.element;

            // datanin isim soy isim ile aradigimiz isim soy isimdeki player mi diye karsilastirma yapilir
            if (player.getName().equalsIgnoreCase(name) && player.getSurname().equalsIgnoreCase(surname)){

                // eger aradigimiz datayi liste basinda bulduysak :
                if (position == head){
                    head = position.next;
                    if (head == null){
                        tail = null;
                    }else {
                        head.previous = null;
                    }

                // eger aradigimiz elemani liste sonunda bulduysak :
                } else if (position == tail) {
                    tail = position.previous;
                    tail.next = null;
                }

                // eger aradigimiz eleman listenin basinda ya da sonunda degilse :
                else {
                    position.previous.next = position.next;
                    position.next.previous = position.previous;
                }

                // eger eleman bulunup silindiyse true dondurur silme islemi kontrolu icin
                return true;
            }

            //positionu her kontrolden sonra bir sonraki elemanin next itemine assign eder.
            position = position.next;
        }

        // iceride kontrol hicbir zaman true donmezse yani
        // aradigimiz elemani bulamazsak silme islemininin sonucu false doner
        return false;
    }

    //DoublyLinkedListin en avatajli oldugu kisim
    //Listeyi tersten yazdirma kismi
    public void outputListFromBack(){

        //sondan baslayacagimiz icin tail elemanindan basliyoruz
        TwoWayNode position = tail;

        while (position != null){
            System.out.println(position.element);

            //sondan geldigimiz icin tailin bir sonraki elemani aslinda previous oluyor
            position = position.previous;
        }
    }

}
