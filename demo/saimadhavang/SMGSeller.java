package demo.saimadhavang;

import ecomm.Seller;
import ecomm.Globals.Category;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import java.time.*;
import java.util.Random;
import java.util.Set;

import demo.Book;
import demo.Mobile;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

public class SMGSeller extends Seller {
    public SMGSeller(String ID) {
        super(ID);
        sellerID = ID;
        generateProducts();
    }

    public void addPlatform(Platform platform) {
        this.platform = platform;
    }

    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        ArrayList<Product> categoryList = new ArrayList<>();
        for (Product product : productsList) {
            if (product.getCategory() == whichOne) {
                categoryList.add(product);
            }
        }
        return categoryList;
    }

    public boolean buyProduct(String productID, int quantity) {
        Product product = productMap.get(productID);
        if (product.getQuantity() >= quantity) {
            if (product.getCategory() == Category.Mobile) {
                Mobile mobile = (Mobile) product;
                mobile.reduceQuantity(quantity);
            } else if (product.getCategory() == Category.Book) {
                Book book = (Book) product;
                book.reduceQuantity(quantity);
            }
            return true;
        } else {
            return false;
        }
    }

    private void generateProducts() {
        random.setSeed(LocalTime.now().getNano());
        int numOfProducts = random.nextInt(12);
        while (numOfProducts > 5) {
            numOfProducts = random.nextInt(12);
        }
        Set<String> nameSet = new HashSet<String>();

        for (int i = 0; i < numOfProducts; i++) {
            LocalTime time = LocalTime.now();
            String productID = sellerID + "-" + Integer.toString(1000 + i);
            if (time.getNano() % 2 == 1) {
                Category category = Category.Mobile;
                String name = nameGenerator(category);
                while (!nameSet.contains(name)) {
                    name = nameGenerator(category);
                }
                nameSet.add(name);
                float price = (random.nextInt() * 1000) % 80000;
                int quantity = random.nextInt(100);
                Mobile mobile = new Mobile(category, name, productID, price, quantity);
                productsList.add(mobile);
                productMap.put(productID, mobile);
            } else {
                Category category = Category.Book;
                String name = nameGenerator(category);
                while (!nameSet.contains(name)) {
                    name = nameGenerator(category);
                }
                nameSet.add(name);
                float price = (random.nextInt() * 10) % 2000;
                int quantity = random.nextInt(100);
                Book book = new Book(category, name, productID, price, quantity);
                productsList.add(book);
                productMap.put(productID, book);
            }
        }
    }

    private String nameGenerator(Globals.Category category) {
        if (category == Category.Mobile) {
            String[] names_0 = { "Sansumg", "Appel", "Macrohard", "Mokia", "OneMinus", "Fotorola" };
            String[] names_3 = { "Pro", "Max", "Prime", "Ultra", "Lite", "Plus" };
            String name_0 = names_0[random.nextInt(6)];
            String name_1 = Character.toString((char) (65 + random.nextInt(26)));
            String name_2 = Integer.toString(random.nextInt(23) + 1);
            String name_3 = names_3[random.nextInt(6)];
            String name = "SMG_" + name_0 + " " + name_1 + name_2 + " " + name_3;
            return name;
        } else if (category == Category.Book) {
            String[] names_0 = { "A Guide to ", "Handbook of ", "Basics of ", "Fundamentals of ", "Essence of ",
                    "The History of ", "Problems in ", "A layman's look at ", "Introduction to ",
                    "A beginner's guide to " };
            String[] names_1 = { "High School Calculus ", "Economics ", "Digita Design ", "Programming ",
                    "Linear Algebra ", "Probability and Statistics ", "Quantum Mechanics ", "Modern Computing ",
                    "Discrete Mathematics ", "Carnatic Music " };
            String[] names_2 = { "Michael ", "Ravi ", "Robert ", "Sarvesh ", "Vladmir ", "Amitabh ", "Ted ", "Sachin ",
                    "Grace ", "Rahul " };
            String[] names_3 = { "Subramaniam", "Brown", "Chattterji", "Parker", "Chokkalingam", "White", "Shah",
                    "Smith", "Gupta", "Rogers" };
            String name = "SMG_" + names_0[random.nextInt(10)] + names_1[random.nextInt(10)] + "by "
                    + names_2[random.nextInt(10)] + names_3[random.nextInt(10)];
            return name;
        } else {
            return "null";
        }

    }

    public static void main(String[] args) {
        SMGSeller smg = new SMGSeller("123");
        for (int i = 0; i < 5; i++) {
            System.out.println(smg.nameGenerator(Category.Book));
        }
    }

    private Random random = new Random();
    private Platform platform;
    private ArrayList<Product> productsList = new ArrayList<>();
    private HashMap<String, Product> productMap = new HashMap<>();
    private String sellerID;
}
