package app;

import model.Date;
import model.Player;
import structures.LinkedList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hasan
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try(Scanner scanner =new Scanner(new FileReader("players.txt"))){
            LinkedList playersInformation =new LinkedList();
            while(scanner.hasNextLine()){
                String players_information=scanner.nextLine();
                
                String[] playersArray=players_information.split(",");
                String name=String.valueOf(playersArray[0]);
                String surname=String.valueOf(playersArray[1]);
                String birthDateString=String.valueOf(playersArray[2]);

                String[] birthDateArray=birthDateString.split("/");
                int day=Integer.parseInt(birthDateArray[0]);
                int month=Integer.parseInt(birthDateArray[1]);
                int year=Integer.parseInt(birthDateArray[2]);
                Date birthDate=new Date(month, day, year);
                ArrayList<String> clubs = new ArrayList<>();
                for (int i=3;i<(playersArray.length-1);i++){
                    clubs.add(playersArray[i]);       
                }
                Player player=new Player(name, surname, birthDate, clubs);
                
      
            }
            playersInformation.outputList();
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        } 
        
    }
    
}
