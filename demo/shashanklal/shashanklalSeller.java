package demo.shashanklal;

import ecomm.Platform;
import ecomm.Product;
import ecomm.Globals;
import ecomm.Globals.Category;
import java.util.*;
import java.time.*;
import java.util.Random;
import demo.Book;
import demo.Mobile;

public class shashanklalSeller extends ecomm.Seller {
    public shashanklalSeller(String id) {
        super(id);
        generateproducts();
    }

    public String getID() {
        return myID;
    }

    public void addPlatform(Platform platform) {
        this.platform = platform;
    }

    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        ArrayList<Product> listofproduct = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getCategory() == whichOne) {
                listofproduct.add(products.get(i));
            }
        }
        return listofproduct;
    }

    public boolean buyProduct(String productID, int quantity) {

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == productID) {
                if (products.get(i).getQuantity() >= quantity) {
                    if (products.get(i).getCategory() == Category.Mobile) {
                        Mobile mb = (Mobile) products.get(i);
                        mb.reduceQuantity(quantity);
                    } else {
                        Book b = (Book) products.get(i);
                        b.reduceQuantity(quantity);
                    }
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }

    private void generateproducts() {
        random.setSeed(LocalTime.now().getNano());
        int numberofproducts = random.nextInt(3, 100);
        for (int i = 0; i < numberofproducts; i++) {
            if (i % 2 == 0) {
                int quantity = random.nextInt(1, 100);
                float price = random.nextInt(2, 6) * 10000.0f;
                String name = nameofmobiles[random.nextInt(8)];
                String productID = "shashanklal_" + Integer.toString(i + 1000);
                Mobile mb = new Mobile(Category.Mobile, name, productID, price, quantity);
                products.add(mb);
            } else {
                int quantity = random.nextInt(1, 100);
                float price = random.nextInt(2, 9) * 100.0f;
                String name = nameofbooks[random.nextInt(10)];
                String productID = "shashanklal_" + Integer.toString(i + 1000);
                Book b = new Book(Category.Book, name, productID, price, quantity);
                products.add(b);
            }
        }
    }

    private String[] nameofmobiles = { "Tony", "Ramsung", "Pineapple", "Tivo", "Joppo", "PlusPlus", "Mixel", "Thor" };
    private String[] nameofbooks = { "Aroundtheworld", "MerchantOfVenice", "War and Peace", "Ikigai", "Invisible Man",
            "Time Travel", "Macbeth", "TheAlchemist", "WillyWonka", "Sapiens" };
    private String myID;
    private ArrayList<Product> products = new ArrayList<Product>();
    private Random random = new Random();
    private Platform platform;

}