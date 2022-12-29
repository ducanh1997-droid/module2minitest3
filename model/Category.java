package model;

import java.io.Serializable;

public class Category implements Serializable {
    private static int INDEX = 1;
    private final int id;
    private String name;

    public Category(String name) {
        this.id = INDEX;
        this.name = name;
        INDEX++;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void display() {
        System.out.printf("%-15s%-15s", id, name+ "\n");
    }
}
