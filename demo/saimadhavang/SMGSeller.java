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

public class SMGSeller extends Seller// SMGSeller is the child of Seller class
{
    public SMGSeller(String ID)// Constructor for SMGSeller
    {
        super(ID);// Calling the parent constructor
        sellerID = ID;
        generateProducts();
    }

    // Initialise the member platform for the respective seller object
    public void addPlatform(Platform platform) {
        this.platform = platform;
    }

    // This function will return an arraylist of products which are of the desired
    // category
    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        ArrayList<Product> categoryList = new ArrayList<>();
        for (Product product : productsList) {
            if (product.getCategory() == whichOne) {
                categoryList.add(product);
            }
        }
        return categoryList;
    }

    // This fuction will check for the availability of the product desired by the
    // user
    // It returns true if the requested product is availaible in the desired
    // quantity
    // Otherwise it returns flase
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

    // This function will generateproducts, with random quantity,price and name
    private void generateProducts() {
        random.setSeed(LocalTime.now().getNano());
        int numOfProducts = random.nextInt(12);
        while (numOfProducts < 5) {
            numOfProducts = random.nextInt(12);
        }
        Set<String> nameSet = new HashSet<String>();
        System.out.println("Num of products: " + numOfProducts);
        for (int i = 0; i < numOfProducts; i++) {
            String productID = sellerID + "-" + Integer.toString(1000 + i);
            if (i % 2 == 1) {
                Category category = Category.Mobile;
                String name = nameGenerator(category);
                while (nameSet.contains(name)) {
                    name = nameGenerator(category);
                }
                nameSet.add(name);
                float price = (random.nextInt() * 1000) % 80000;
                if (price < 0)
                    price = -price;
                int quantity = random.nextInt(100);
                Mobile mobile = new Mobile(category, name, productID, price, quantity);// Creating an object of the
                                                                                       // mobile class
                productsList.add(mobile);// Adding the mobile to arraylist of products
                productMap.put(productID, mobile);// Adding the productID to the hashmap
            } else {
                Category category = Category.Book;
                String name = nameGenerator(category);
                while (nameSet.contains(name)) {
                    name = nameGenerator(category);
                }
                nameSet.add(name);
                float price = (random.nextInt() * 10) % 2000;
                if (price < 0)
                    price = -price;
                int quantity = random.nextInt(100);
                Book book = new Book(category, name, productID, price, quantity);// Creating an object of the book class
                productsList.add(book);// Adding the object in the arraylist of products
                productMap.put(productID, book);// Adding the respective productID to the hashmap
            }
        }
    }

    // This function will generate a name for the respective product randomly
    private String nameGenerator(Globals.Category category) {
        if (category == Category.Mobile) {
            String[] names_0 = { "Sansumg", "Appel", "Macrohard", "Mokia", "OneMinus", "Fotorola" };
            String[] names_3 = { "Pro", "Max", "Prime", "Ultra", "Lite", "Plus" };
            String name_0 = names_0[random.nextInt(6)];// Name_0 is a random name from the array of strings names_0
            String name_1 = Character.toString((char) (65 + random.nextInt(26)));// It is a random alphabet
            String name_2 = Integer.toString(random.nextInt(23) + 1);// WIll generate a random integer
            String name_3 = names_3[random.nextInt(6)];// Name_3 is a random name from the array of strings names_3
            String name = name_0 + "_" + name_1 + name_2 + "_" + name_3;// The final name of the product
            return name;
        } else if (category == Category.Book) {
            String[] names_0 = { "A_Guide_to_", "Handbook_of_", "Basics_of_", "Fundamentals_of_", "Essence_of_",
                    "The_History_of_", "Problems_in_", "A_layman's_look_at_", "Introduction_to_",
                    "A_beginner's_guide_to_" };
            String[] names_1 = { "High_School_Calculus_", "Economics_", "Digital_Design_", "Programming_",
                    "Linear_Algebra_", "Probability_and_Statistics_", "Quantum_Mechanics_", "Modern_Computing_",
                    "Discrete_Mathematics_", "Carnatic_Music_" };
            String[] names_2 = { "Michael_", "Ravi_", "Robert_", "Sarvesh_", "Vladmir_", "Amitabh_", "Ted_", "Sachin_",
                    "Grace_", "Rahul_" };
            String[] names_3 = { "Subramaniam", "Brown", "Chattterji", "Parker", "Chokkalingam", "White", "Shah",
                    "Smith", "Gupta", "Rogers" };
            // A random bookname will be generated by selecting a word randomly from each of
            // the four arrays
            String name = names_0[random.nextInt(10)] + names_1[random.nextInt(10)] + "by_"
                    + names_2[random.nextInt(10)] + names_3[random.nextInt(10)];
            return name;
        } else {
            return "null";
        }

    }

    public static void main(String[] args) {
        SMGSeller smg = new SMGSeller("123");
        for (Product p : smg.productsList) {
            System.out.println(p.getName());
        }
    }

    private Random random = new Random();
    private Platform platform;
    private ArrayList<Product> productsList = new ArrayList<>();
    private HashMap<String, Product> productMap = new HashMap<>();
    private String sellerID;
}
