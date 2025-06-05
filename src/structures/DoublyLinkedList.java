package structures;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import model.Player;

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

        //position olarak yani listeyi dönerken kullandığımız pointeri head yani ilk düğüme set eder
        public DoublyLinkedIterator(){
            this.position = head;
        }

        //iterator tekrar liste başına gelmesi gerektiğinde tekrar pointeri head'e set eder
        public void restart(){
            this.position = head;
        }

        //o an bulunduğu düğümden sonraki düğümdeki datayı yani Player nesnesini return eder
        public Player next(){
            if(!hasNext())
                throw new NoSuchElementException();
            Player player = position.element;
            position = position.next;
            return player;
        }

        //iterate edebilecek eleman var mı diye kontrol
        public boolean hasNext()
        {
            return (position != null);
        }

        //iterate eldecek eleman var ise sonraki düğümün datasını return eder
        public Player peek()
        {
            if (!hasNext())
                throw new IllegalStateException();
            return position.element;
        }
        
        
        public void insertHere(Player newElement)
        {   //Eğer liste boş değil ama iteratorun positionu set edilmemiş ise headin arkasına ekler yeni düğümü
            if (position == null && head != null)
            {
                TwoWayNode temp = head;
                while (temp.next != null)
                {
                    temp = temp.next;
                }
                temp.next = new TwoWayNode(newElement, temp, null);
                tail =temp.next;
            } // iterator boş ise ve liste de boş ise en başa ekler Player listesini bir düğüm
            else if (head == null || position.previous == null)
                DoublyLinkedList.this.addToStart(newElement);
            else
            {
                //iterator boş değilse mevcut konumdan önce ekler
                TwoWayNode temp = new TwoWayNode(newElement, position.previous, position);
                position.previous.next = temp;
                position.previous = temp;
            }
        }

        //iteratorun konumuna göre eğer previousu boş olan bir düğüm var ise onun head olması demektir
        //head nodeyi siler
        //eğer iterator set edilmemişse hata döndürür
        //eğer nexti null olan bir node var ise o tail nodedir o yüzden taili siler ve tailin previousunu
        //yeni tail olarak assign eder
        public void delete()
        {
            if (position == null)
                throw new IllegalStateException();
            else if (position.previous == null)
            {
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

    //class dışından iterator nesnesi oluşturmadan iterator nesnesini çağırmak istersek diye
    public DoublyLinkedIterator iterator(){
        return new DoublyLinkedIterator();
    }

    //ana constructorumuz headi null olarak assign eder
    public DoublyLinkedList(){
        head = null;
    }

    //listeye baştan eleman ekler yani head nodeyi assign eder
    public void addToStart(Player newElement)
    {
        TwoWayNode newHead = new TwoWayNode(newElement, null, head);
        if (head != null)
        {
            head.previous = newHead;
        }else {
            tail = newHead;
        }
        head = newHead;
    }

    //head nodeyi siler
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

    //head nodeden başlayarak tek tek nodenin nextine positionu set eder
    //eğer position.next null değil ise countu yani listenin eleman sayısını artırır her bir eleman için
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

    //head nodeden başlayarak her bir nodenin datasını(player objesini) yazdırır
    //Player sınıfındaki toString sayesinde kullanıcı doğrudan player nesnesinin datasını görüntüler
    public void outputList( )
    {
        TwoWayNode position = head;
        while (position != null)
        {
            System.out.println(position.element);
            position = position.next;
        }
    }

    //liste boş mu diye bakar
    public boolean isEmpty( )
    {
        return (head == null);
    }

    //listenin bağlarını kopartır Java sınıfındaki garbageCollector elemanları temizler
    //yani listeyi sıfırlar
    public void clear( )
    {
        head = null;
        tail = null;
    }

    public void insertSorted( Player newElement){
        //TwoWayNode nodeToBeInserted = new TwoWayNode(newElement, head, tail);

        if (head == null){
            addToStart(newElement);
            return;
        }

        // Eklenecek oyuncu daha one eklenecekse
        if (newElement.compareTo(head.element) < 0){
            addToStart(newElement);
            return;
        }

        //pointeri head node'den başlayarak datasını yani player objesinin adına ve soyadına göre
        //karşılaştırma yaparak ilerler ve pointeri uygun yere kadar getirir
        TwoWayNode pointer = head;
        while (pointer != null && newElement.compareTo(pointer.element) >= 0){
            pointer = pointer.next;
        }

        //eğer eğer pointer null ise yani henüz alfabetik olarak kendisinden sonra gelen bir 
        //isime sahip player objesini içeren node bulunamadı ise bizim playerimizi 
        //listeye yeni kuyruk tail node olarak ekler
        if (pointer == null){
            TwoWayNode newTail = new TwoWayNode(newElement, tail, null);
            tail.next = newTail;
            tail = newTail;
            return;
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
            TwoWayNode newMiddle = new TwoWayNode(newElement, beforePointer, pointer);
            beforePointer.next = newMiddle;
            pointer.previous = newMiddle;
        }
    }

    //Parametre olarak alınan name, surname değerlerine sahip oyuncuyu bulur ve döndürür
    //oyuncu yoksa null döndürür
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

    //bu method ile listemizdeki elemanları parametre olarak alınan stack yapısına
    //kopyalıyoruz yani bir nevi yedeğini alıyoruz
    public void copyBeforeDeleteToStack(Stack stack) {
        DoublyLinkedIterator iterator = iterator();
        while (iterator.hasNext()) {
            stack.push(iterator.next());
        }
    }

    //daha önceden elemanları kopyaladığımız stackteki elemanları tekrardan 
    //listeye eklemek için yani yedekten geri yükleme mekanizasması amacı ile yazıldı bu method
    public void getFromStackAfterDeletion(Stack stack) {
        while (!stack.isEmpty()) {
            Player p = (Player) stack.pop();
            insertSorted(p);
        }
    }

    //Stacke yedeklenen verileri yedek.txt dosyasına write eder
    public boolean saveFromBackupToFile(Stack stack) {
        if (!stack.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("yedek.txt"))) {
                Stack tempStack = new Stack(stack.size());

                // Stacki geçici bir stacke aktarır
                while (!stack.isEmpty()) {
                    Player p = (Player) stack.pop();
                    tempStack.push(p);
                }

                // geçici stackten tekrar okuyarak dosyaya yazar
                while (!tempStack.isEmpty()) {
                    Player p = (Player) tempStack.pop();
                    stack.push(p); // Orijinal stack'e geri koy
                    writer.write(p.toString()); // Oyuncuyu dosya formatında yaz
                    writer.newLine();
                }

                //işlem başarılı ise true döner kontroller için
                return true;
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("Stack is empty. Nothing to write.");
        }
        //başarılı olmadıysa false olarak return eder
        return false;
    }

}
