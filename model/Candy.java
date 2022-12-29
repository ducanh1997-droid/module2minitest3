package model;

public class Candy extends Product{
    private int weight;



    public Candy(int weight, Category category) {
        this.weight = weight;
        this.category = category;
    }

    public Candy(String name, double price, int quantity,Category category,int weight){
         this.id = INDEX;
         this.name = name;
         this.price = price;
         this.quantity = quantity;
         this.weight = weight;
         this.category = category;
         INDEX++;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void display() {
        System.out.printf("%-15s%-15s%-15s%-15s%s",
                id, name, price, quantity, category.getName() + "\n");
    }
}
