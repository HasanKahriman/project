package app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import model.Date;
import model.Player;
import structures.DoublyLinkedList;
import structures.Stack;

public class Demo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DoublyLinkedList playerList = new DoublyLinkedList();
        Stack playerStack = new Stack(100);

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
            System.out.println("players.txt file was not found.");
        }
        String operations="--- PLAYER LIST MENU ---\n"
                          + "1. Print the list in A-Z order\n"
                          + "2. Print the list in Z-A order\n"
                          + "3. Search for a player\n"
                          + "4. Add a player\n"
                          + "5. Remove a player\n"
                          + "6. Show list size\n"
                          + "7. Is the list empty?\n"
                          + "8. Clear the list\n"
                          + "9. Exit\n"
                          + "10. Back up the list\n"
                          + "11. Restore the list from back up\n"
                          + "12. Save last backup to a file";
        int choice;

        boolean exit=false;
        while (!exit){
            System.out.println(operations);
            System.out.print("Please enter your choice :");

            choice = input.nextInt();
            input.nextLine();
            switch (choice){
                case 1:
                    playerList.outputList();
                    break;
                case 2:
                    playerList.outputListFromBack();
                    break;
                case 3:
                    System.out.print("Name: ");
                    String searchName = input.nextLine();
                    System.out.print("Surname: ");
                    String searchSurname = input.nextLine();
                    Player found = playerList.find(searchName, searchSurname);
                    if (found != null) {
                        System.out.println("The player was found:\n" + found);
                    } else {
                        System.out.println("The player was not found.");
                    }
                    break;
                case 4:
                    System.out.print("Name: ");
                    String name = input.nextLine();
                    System.out.print("Surname: ");
                    String surname = input.nextLine();
                    System.out.print("Date of birth (DD/MM/YYYY): ");
                    String[] dateParts = input.nextLine().split("/");
                    int d = Integer.parseInt(dateParts[0]);
                    int m = Integer.parseInt(dateParts[1]);
                    int y = Integer.parseInt(dateParts[2]);
                    Date date = new Date(m, d, y);
                    ArrayList<String> clubList = new ArrayList<>();
                    System.out.print("How many clubs did he play for ?: ");
                    int clubCount = input.nextInt();
                    input.nextLine();
                    for (int i = 0; i < clubCount; i++) {
                        System.out.print("Club " + (i + 1) + ": ");
                        clubList.add(input.nextLine());
                    }
                    Player newPlayer = new Player(name, surname, date, clubList);
                    playerList.insertSorted(newPlayer);
                    System.out.println("Player added.");
                    break;
                case 5:
                    System.out.print("The name of the player to be deleted: ");
                    String delName = input.nextLine();
                    System.out.print("The surname of the player to be deleted: ");
                    String delSurname = input.nextLine();
                    boolean deleted = playerList.deletePlayer(delName, delSurname);
                    if (deleted) {
                        System.out.println("The player has been deleted.");
                    } else {
                        System.out.println("The player was not found.");
                    }
                    break;
                case 6:
                    System.out.println("Total number of the players : " + playerList.size());
                    break;
                case 7:
                    if (playerList.isEmpty()){
                        System.out.println("The list is empty.");
                    }
                    else {
                        System.out.println("The list is not emtpy");
                    }
                    break;
                case 8:
                    playerList.clear();
                    System.out.println("The list has been cleared.");
                    break;
                case 9:

                    System.out.println("The program is being terminated...");
                    exit=true;
                    break;   
                case 10:
                    playerList.copyBeforeDeleteToStack(playerStack);
                    System.out.println("The replacement was taken with success!");
                    break;
                case 11:
                    if (!playerStack.isEmpty()) {
                        playerList.getFromStackAfterDeletion(playerStack);
                        System.out.println("Datas coppied from stack successfully");
                    }else {
                        System.out.println("There is no backup!");
                    }
                    break;
                case 12:
                    boolean statusOfSave = playerList.saveFromBackupToFile(playerStack);
                    if (statusOfSave) {
                        System.out.println("Backup was successfully saved to the local storage.");
                    }else {
                        System.out.println("The save operation failed.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }   
}
