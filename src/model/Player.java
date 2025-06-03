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

    public Player(String name, String surname, Date birthDate, ArrayList<String> clubs) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.clubs = clubs;
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
}
