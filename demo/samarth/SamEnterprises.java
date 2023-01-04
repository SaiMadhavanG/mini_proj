package demo.samarth;

import demo.Book;
import demo.Mobile;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SamEnterprises extends Seller// class SamEnterprises is the child class of Seller
{

    private Inventory inventory;
    private Platform platform;

    private class Inventory// Class Inventory
    {
        public Inventory()// Constructor
        {
            books = new ArrayList<>();
            mobiles = new ArrayList<>();
        }

        // This function will return productList of that particular product(Book or
        // Mobile)
        ArrayList<Product> getProductList(Globals.Category cat) {
            switch (cat) {
                case Book -> {

                    return books;
                }
                case Mobile -> {
                    return mobiles;
                }
            }
            return null;
        }

        // This function will add a book object to the array of books
        void addBook(Book book) {
            books.add(book);
        }

        // This function will add a mobile object to the array of mobiles
        void addMobile(Mobile mob) {
            mobiles.add(mob);
        }

        // Data Members
        ArrayList<Product> books;
        ArrayList<Product> mobiles;
    }

    public SamEnterprises(String id)// Constructor
    {
        super(id);// Calling the parent constructor
        inventory = new Inventory();// Creating an object of Inventory class
        // Calling functions addMobiles and addBooks
        addMobiles();
        addBooks();
    }

    private void addBooks() {
        // Generating random name,quantity and price for the particular book
        Random rand = new Random();
        ArrayList<String> cool_names = new ArrayList<>(
                List.of(new String[] { "Delhiwale daru le jayenge", "Sai the eminant because", "We Need A savior",
                        "Kindler 6",
                        "Redemption 7", "Sherman 4", "Sherman 14", "Lynx 17", "Kanishnokov AK47" }));
        for (Integer i = 0; i < cool_names.size(); i++) {
            Integer Quantity = rand.nextInt(20);
            Integer Price = rand.nextInt(200, 1000);
            // !check if this i works
            // Creating an object of the Book class and adding it to the inventory
            Book book = new Book(Globals.Category.Book, cool_names.get(i), "SamEntrprises-Book." + i.toString(), Price,
                    Quantity);
            inventory.addBook(book);
        }

    }

    private void addMobiles() {
        // Generating random name,quantity and price for the particular book
        Random rand = new Random();
        ArrayList<String> cool_names = new ArrayList<>(
                List.of(new String[] { "Syphon 7", "Syphon 9", "Kindler 5", "Kindler 6",
                        "Redemption 7", "Sherman 4", "Sherman 14", "Lynx 17", "Kanishnokov AK47" }));
        for (Integer i = 0; i < cool_names.size(); i++) {
            Integer Quantity = rand.nextInt(20);
            Integer Price = rand.nextInt(20000, 50000);

            // !Check if this i works latter.
            // Creating an object of the Mobile class and adding it to the inventory
            Mobile mob = new Mobile(Globals.Category.Mobile, cool_names.get(i), "SamEntrprises-Mobile." + i, Price,
                    Quantity);
            inventory.addMobile(mob);
        }
    }

    // Adding the seller to the respective platform
    @Override
    public void addPlatform(Platform thePlatform) {
        this.platform = thePlatform;
    }

    @Override
    // This function will return arraylist of products of the desired category
    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        return inventory.getProductList(whichOne);
    }

    @Override
    // This fuction will check for the availability of the product desired by the
    // user
    // It returns true if the requested product is availaible in the desired
    // quantity
    // Otherwise it returns flase
    public boolean buyProduct(String productID, int quantity) {
        String[] components = productID.split(".");
        if (components[1].equals("Mobile")) {
            Integer hashmob = Integer.parseInt(components[2]);
            Mobile temp = (Mobile) inventory.mobiles.get(hashmob);
            if (temp.getQuantity() > quantity) {
                temp.reduceQuantity(quantity);
                return true;

            }
        } else if (components[1].equals("Book")) {
            Integer hashmob = Integer.parseInt(components[2]);
            Book temp = (Book) inventory.books.get(hashmob);
            if (temp.getQuantity() > quantity) {
                temp.reduceQuantity(quantity);
                return true;
            }
        }
        return false;
    }
}
