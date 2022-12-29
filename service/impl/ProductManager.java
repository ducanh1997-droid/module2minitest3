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

    private void title() {
        System.out.printf("%-15s%-15s%-15s%-15s%s",
                "ID", "NAME", "PRICE", "QUANTITY", "CATEGORY\n");
    }
}
