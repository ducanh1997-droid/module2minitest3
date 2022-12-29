package service.impl;

import model.Candy;
import model.Category;
import model.Drink;
import model.Product;
import service.ICrudManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager implements ICrudManager<Product> {

    private final ArrayList<Product> products;

    private CategoryManager categoryManager;

    public ProductManager(CategoryManager categoryManager) {
        products = new ArrayList<>();
        this.categoryManager = categoryManager;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    @Override
    public Product create(Scanner scanner) {
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter product quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        int choice;
        System.out.println("Enter choice category of product:");

        do {
            System.out.println("1. Candy");
            System.out.println("2. Drink");

            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Enter weight of candy:");
                    int weight = Integer.parseInt(scanner.nextLine());
                    categoryManager.displayAll(categoryManager.getCategory());
                    Category category = choiceCategory(scanner);
                    return new Candy(name, price, quantity, category,weight);

                case 2:
                    System.out.println("Enter volume of drink:");
                    int volume = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter bottleType of drink:");
                    String bottleType = scanner.nextLine();
                    categoryManager.displayAll(categoryManager.getCategory());
                    Category category1 = choiceCategory(scanner);
                    return new Drink(name, price, quantity,category1, volume, bottleType);
                default:
                    choice = 0;
                    break;
            }
        } while (choice == 0);
        categoryManager.displayAll(categoryManager.getCategory());
        Category category = choiceCategory(scanner);
        return new Product(name, price, quantity,category);
    }
    @Override
    public void save(Product product) {
        products.add(product);
        write("/Users/ducanh/Desktop/module2minitest3/data/product.txt",products);
        System.out.println("Add product successfully!");
        title();
//        if(product instanceof Candy){
//            Candy candy = (Candy) product;
//            candy.m2();
//            System.out.println("ok");
//        }
        product.display();
    }
    @Override
    public void displayAll(List<Product> products) {
        if(!products.isEmpty()){
            System.out.println("List product:");
            title();
            for(Product product : products){
                product.display();
            }
        }else{
            System.out.println("Not exist product in list!");
        }
    }

    private Category choiceCategory(Scanner scanner) {
        Category category;
        System.out.println("Enter choice classroom by id: (Enter 0 for create new)");
        int idCategory = Integer.parseInt(scanner.nextLine());
        if (idCategory == 0) {
            category = categoryManager.create(scanner);
            categoryManager.save(category);
        } else {
            category = categoryManager.getById(idCategory);
        }
        if (category != null) {
            return category;
        } else {
            return choiceCategory(scanner);
        }
    }

    public static void write(String path, ArrayList<Product> products ) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(products);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  ArrayList<Product> read(String path) {
        ArrayList<Product> students = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            if (fileInputStream.available() != 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Object object = objectInputStream.readObject();
                students = (ArrayList<Product>) object;
                objectInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void deleteById(ArrayList<Product> products,Scanner scanner){
        boolean check = true;
        int id = 0;
        do{
            try{
                System.out.println("Nhập id sản phẩm muốn xoá:");
                id = Integer.parseInt(scanner.nextLine());
                check = false;
            }catch(NumberFormatException e){
                System.out.println("Nhập id sai,nhập lại");
            }
        }while(check);
        products.remove(id-1);
    }

    public void displayProductCandy(ArrayList<Product> products){
        ArrayList<Product> productCandys = new ArrayList<>();
        for(Product product : products){
            if(product instanceof Candy){
                productCandys.add(product);
            }
        }
        displayAll(productCandys);

    }

    public void displayProductDrink(ArrayList<Product> products){
        ArrayList<Product> productDrinks = new ArrayList<>();
        for(Product product : products){
            if(product instanceof Drink){
                productDrinks.add(product);
            }
        }
        displayAll(productDrinks);

    }

    public void displayByPriceMaxMin(ArrayList<Product> products,Scanner scanner){
        int choice = 0;
        do{
            System.out.println("1.Hiển thị sản phẩm giá cao nhất");
            System.out.println("2.Hiển thị sản phẩm giá thấp nhất");
            choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    ArrayList<Product> listMaxPrice = new ArrayList<>();
                    int max = (int) products.get(0).getPrice();
                    for(Product product: products){
                        if(max<product.getPrice()){
                            max = (int) product.getPrice();
                        }
                    }
                    for(Product product: products){
                        if(max == (int) product.getPrice()){
                            listMaxPrice.add(product);
                        }
                    }
                    displayAll(listMaxPrice);
                    break;
                case 2:
                    ArrayList<Product> listMinPrice = new ArrayList<>();
                    int min = (int) products.get(0).getPrice();
                    for(Product product: products){
                        if(min>product.getPrice()){
                            min = (int) product.getPrice();
                        }
                    }
                    for(Product product: products){
                        if(min == (int) product.getPrice()){
                            listMinPrice.add(product);
                        }
                    }
                    displayAll(listMinPrice);
                    break;
            }
        }while(choice!=0);
    }

    public void displayByQuantityMaxMin(ArrayList<Product> products,Scanner scanner){
        int choice = 0;
        do{
            System.out.println("1.Hiển thị sản phẩm có số lượng nhiều nhất");
            System.out.println("2.Hiển thị sản phẩm có số lượng ít nhất");
            choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    ArrayList<Product> listMaxQuantity = new ArrayList<>();
                    int max = products.get(0).getQuantity();
                    for(Product product: products){
                        if(max<product.getQuantity()){
                            max = product.getQuantity();
                        }
                    }
                    for(Product product: products){
                        if(max == (int) product.getPrice()){
                            listMaxQuantity.add(product);
                        }
                    }
                    displayAll(listMaxQuantity);
                    break;
                case 2:
                    ArrayList<Product> listMinQuantity = new ArrayList<>();
                    int min = products.get(0).getQuantity();
                    for(Product product: products){
                        if(min>product.getQuantity()){
                            min = product.getQuantity();
                        }
                    }
                    for(Product product: products){
                        if(min == product.getQuantity()){
                            listMinQuantity.add(product);
                        }
                    }
                    displayAll(listMinQuantity);
                    break;
            }
        }while(choice!=0);
    }
    private void title() {
        System.out.printf("%-15s%-15s%-15s%-15s%s",
                "ID", "NAME", "PRICE", "QUANTITY", "CATEGORY\n");
    }
}
