package app;

import model.Date;
import model.Player;
import structures.DoublyLinkedList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DoublyLinkedList playerList = new DoublyLinkedList();

        // Verileri dosyadan oku
        try (Scanner scanner = new Scanner(new FileReader("players.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length < 4) continue;

                String name = parts[0];
                String surname = parts[1];
                String[] dateParts = parts[2].split("/");
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);
                Date birthDate = new Date(month, day, year);

                ArrayList<String> clubs = new ArrayList<>();
                for (int i = 3; i < parts.length; i++) {
                    clubs.add(parts[i].trim());
                }

                Player player = new Player(name, surname, birthDate, clubs);
                playerList.insertSorted(player);
            }
        } catch (FileNotFoundException e) {
            System.out.println("players.txt dosyası bulunamadı.");
        }

        // Menü döngüsü
        int choice;
        do {
            System.out.println("\n--- OYUNCU LISTESI MENÜSÜ ---");
            System.out.println("1. Listeyi A-Z sırayla yazdır");
            System.out.println("2. Listeyi Z-A sırayla yazdır");
            System.out.println("3. Oyuncu ara");
            System.out.println("4. Oyuncu ekle");
            System.out.println("5. Oyuncu sil");
            System.out.println("6. Liste boyutunu göster");
            System.out.println("7. Liste boş mu?");
            System.out.println("8. Listeyi temizle");
            System.out.println("9. Çıkış");
            System.out.print("Seçiminiz: ");
            choice = input.nextInt();
            input.nextLine(); // dummy newline

            switch (choice) {
                case 1:
                    playerList.outputList();
                    break;
                case 2:
                    playerList.outputListFromBack();
                    break;
                case 3:
                    System.out.print("Ad: ");
                    String searchName = input.nextLine();
                    System.out.print("Soyad: ");
                    String searchSurname = input.nextLine();
                    Player found = playerList.find(searchName, searchSurname);
                    if (found != null) {
                        System.out.println("Oyuncu bulundu:\n" + found);
                    } else {
                        System.out.println("Oyuncu bulunamadı.");
                    }
                    break;
                case 4:
                    System.out.print("Ad: ");
                    String name = input.nextLine();
                    System.out.print("Soyad: ");
                    String surname = input.nextLine();
                    System.out.print("Doğum tarihi (GG/AA/YYYY): ");
                    String[] dateParts = input.nextLine().split("/");
                    int d = Integer.parseInt(dateParts[0]);
                    int m = Integer.parseInt(dateParts[1]);
                    int y = Integer.parseInt(dateParts[2]);
                    Date date = new Date(m, d, y);
                    ArrayList<String> clubList = new ArrayList<>();
                    System.out.print("Kaç kulüp oynadı?: ");
                    int clubCount = input.nextInt();
                    input.nextLine();
                    for (int i = 0; i < clubCount; i++) {
                        System.out.print("Kulüp " + (i + 1) + ": ");
                        clubList.add(input.nextLine());
                    }
                    Player newPlayer = new Player(name, surname, date, clubList);
                    playerList.insertSorted(newPlayer);
                    System.out.println("Oyuncu eklendi.");
                    break;
                case 5:
                    System.out.print("Silinecek oyuncunun adı: ");
                    String delName = input.nextLine();
                    System.out.print("Soyadı: ");
                    String delSurname = input.nextLine();
                    boolean deleted = playerList.deletePlayer(delName, delSurname);
                    if (deleted) {
                        System.out.println("Oyuncu silindi.");
                    } else {
                        System.out.println("Oyuncu bulunamadı.");
                    }
                    break;
                case 6:
                    System.out.println("Toplam oyuncu sayısı: " + playerList.size());
                    break;
                case 7:
                    System.out.println(playerList.isEmpty() ? "Liste boş." : "Liste boş değil.");
                    break;
                case 8:
                    playerList.clear();
                    System.out.println("Liste temizlendi.");
                    break;
                case 9:
                    System.out.println("Program sonlandırılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim.");
            }

        } while (choice != 9);
    }
}
