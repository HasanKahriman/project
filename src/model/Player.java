package model;

import java.util.ArrayList;

/**
 *
 * @author hasan
 */
public class Player implements Comparable<Player> {
    private String name;
    private String surname;
    private Date birthDate;
    private ArrayList<String> clubs;

    public Player(){
        this("","",new Date(),new ArrayList<>());
    }

    public Player(String name, String surname, Date birthDate, ArrayList<String> clubs) {
        this.setName(name);
        this.setSurname(surname);
        this.setBirthDate(birthDate);
        this.setClubs(clubs);
    }

    public Player(Player playerToBeCopied){
        this.setName(playerToBeCopied.getName());
        this.setSurname(playerToBeCopied.getSurname());
        this.setBirthDate(playerToBeCopied.getBirthDate());
        this.setClubs(playerToBeCopied.getClubs());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<String> getClubs() {
        return clubs;
    }

    public void setClubs(ArrayList<String> clubs) {
        this.clubs = clubs;
    }

    @Override
    public int compareTo(Player otherPlayer) {
        int nameCompare = this.name.compareTo(otherPlayer.getName());
        if (nameCompare != 0) {
            return nameCompare;
        }else {
            return this.surname.compareTo(otherPlayer.getSurname());
        }
    }

    public boolean equals(Object otherPlayer) {
        if (otherPlayer == null){
            return false;
        }else if (!(otherPlayer instanceof Player)){
            return false;
        }else {
            return (this.getName().equals(((Player)otherPlayer).getName()) && this.getSurname().equals(((Player)otherPlayer).getSurname()) && this.getBirthDate().equals(((Player)otherPlayer).getBirthDate()) && this.getClubs().equals(((Player)otherPlayer).getClubs()));
        }
    }

    public String toString() {
        return name + " " + surname + " " + birthDate + " " + clubs;
    }
}
