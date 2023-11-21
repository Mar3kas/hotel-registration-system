package model;

public class Guest {
    private final String name;
    private final String surname;

    public Guest(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
