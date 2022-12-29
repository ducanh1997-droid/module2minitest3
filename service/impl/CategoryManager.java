package service.impl;

import model.Category;
import service.ICrudManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryManager implements ICrudManager<Category> {
    private final ArrayList<Category> categorys;


    public CategoryManager() {
        categorys = new ArrayList<>();
    }

    public ArrayList<Category> getCategory() {
        return categorys;
    }

    @Override
    public Category create(Scanner scanner) {
        System.out.println("Enter category name: ");
        String name = scanner.nextLine();
        return new Category(name);
    }

    @Override
    public void displayAll(List<Category> categorys) {
        if (!categorys.isEmpty()) {
            System.out.println("List category: ");
            title();
            for (Category category : categorys) {
                category.display();
            }
        } else {
            System.out.println("List category haven't element!");
        }
    }
    private void title() {
        System.out.printf("%-15s%-15s", "ID", "NAME\n");
    }

    @Override
    public void save(Category category) {
        categorys.add(category);
        System.out.println("Add category successfully!");
        title();
        category.display();
    }
    public Category getById(int categoryId) {
        for (Category category : categorys) {
            if (category.getId() == categoryId) {
                return category;
            }
        }
        return null;
    }
}
