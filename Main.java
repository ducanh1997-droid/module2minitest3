import model.Category;
import model.Product;
import service.impl.CategoryManager;
import service.impl.ProductManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CategoryManager categoryManager = new CategoryManager();
        ProductManager productManager = new ProductManager(categoryManager);
        menuProduct(productManager,scanner);
    }

    public static void  menuProduct(ProductManager productManager,Scanner scanner){
        int choice;
        do{
            System.out.println("Menu");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xoá sản phẩm theo id");
            System.out.println("3. Hiển thị danh sách sản phẩm");
            System.out.println("4. Hiển thị danh sách sản phẩm là Candy");
            System.out.println("5. Hiển thị danh sách sản phẩm là Drinks");
            System.out.println("6. Hiển thị danh sách sản phẩm có giá cao nhất, thấp nhất");
            System.out.println("7. Hiển thị danh sách sản phẩm có số lượng nhiều nhất,ít nhất");
            choice = Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    Product product = productManager.create(scanner);
                    productManager.save(product);
                    break;
                case 2:
                    productManager.deleteById(productManager.getProducts(),scanner);
                    break;
                case 3:
                    productManager.displayAll(productManager.getProducts());
                    break;
                case 4:
                    productManager.displayProductCandy(productManager.getProducts());
                    break;
                case 5:
                    productManager.displayProductDrink(productManager.getProducts());
                    break;
                case 6:
                    productManager.displayByPriceMaxMin(productManager.getProducts(),scanner);
                    break;
                case 7:
                    productManager.displayByQuantityMaxMin(productManager.getProducts(),scanner);
                    break;


            }
        }while(choice != 0);
    }

    private static void menuCategory(CategoryManager categoryManager, Scanner scanner) {
        int choice;
        do {
            System.out.println("MENU:");
            System.out.println("1. Add new category");
            System.out.println("2. Update category by id");
            System.out.println("3. Delete category by id");
            System.out.println("4. Display all category");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Category category = categoryManager.create(scanner);
                    categoryManager.save(category);
                    break;
                case 2:

                    break;

            }
        } while (choice != 0);
    }
}
