package model;

import java.io.Serializable;

public class Product implements Serializable {
    protected static int INDEX = 1;
    protected int id;
    protected String name;
    protected double price;
    protected int quantity;

    protected Category category;

    public Product(String name, double price, int quantity, Category category) {
        this.id = INDEX;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        INDEX++;
    }

    public Product() {

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void display() {
        System.out.printf("%-15s%-15s%-15s%-15s%s",
                id, name, price, quantity, category.getName() + "\n");
    }
}
