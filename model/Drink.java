package model;

public class Drink extends Product{
    private String bottleType;

    private int volume;



    public Drink(String bottleType, int volume) {
        this.bottleType = bottleType;
        this.volume = volume;

    }
    public Drink(String name, double price, int quantity,Category category, int volume,String bottleType){
        this.id = INDEX;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.volume = volume;
        this.bottleType = bottleType;
        this.category = category;
        INDEX++;
    }

    public String getBottleType() {
        return bottleType;
    }

    public void setBottleType(String bottleType) {
        this.bottleType = bottleType;
    }
}
