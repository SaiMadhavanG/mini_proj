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

public class shashanklalSeller extends ecomm.Seller//Here shashankSeller is the child class of Seller in ecomm folder
{
    public shashanklalSeller(String id)//Constructor
    {
        super(id);//calling the constructor of the parent class
        generateproducts();
    }
    //This function will return the seller ID
    public String getID() {
        return myID;
    }
    //Initialise the member platform for the respective seller object and addseller to the platform
    public void addPlatform(Platform platform)
    {
        this.platform = platform;
    }
//This function will return an arraylist of products which are of the desired category
    public ArrayList<Product> findProducts(Globals.Category whichOne)
    {
        ArrayList<Product> listofproduct = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++)
        {
            if (products.get(i).getCategory() == whichOne) {
                listofproduct.add(products.get(i));
            }
        }
        return listofproduct;
    }
    //This fuction will check for the availability of the product desired by the user
    //It returns true if the requested product is availaible in the desired quantity
    //Otherwise it returns flase
    public boolean buyProduct(String productID, int quantity) {

        for (int i = 0; i < products.size(); i++)
        {
            if (products.get(i).getProductID() == productID)
            {
                if (products.get(i).getQuantity() >= quantity)
                {
                    if (products.get(i).getCategory() == Category.Mobile)
                    {
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
//This function will generateproducts, with random quantity,price and name
    private void generateproducts() {
        random.setSeed(LocalTime.now().getNano());
        int numberofproducts = random.nextInt(3, 100);
        for (int i = 0; i < numberofproducts; i++) {
            if (i % 2 == 0) {
                 //generating random quantity,price and name for the book
                int quantity = random.nextInt(1, 100);
                float price = random.nextInt(2, 6) * 10000.0f;
                String name = nameofmobiles[random.nextInt(8)];
                String productID = "shashanklal_" + Integer.toString(i + 1000);
                Mobile mb = new Mobile(Category.Mobile, name, productID, price, quantity);//Creating an object of the Mobile class
                products.add(mb);//Adding that mobile to our list of products
            } else {
                //generating random quantity,price and name for the book
                int quantity = random.nextInt(1, 100);
                float price = random.nextInt(2, 9) * 100.0f;
                String name = nameofbooks[random.nextInt(10)];
                String productID = "shashanklal_" + Integer.toString(i + 1000);
                Book b = new Book(Category.Book, name, productID, price, quantity);//Creating an object of the book class 
                products.add(b);//Adding that book to our list of products
            }
        }
    }
    //Data members of this class
    //List of all the mobile names from which name of that particular mobile will be given randomly
    private String[] nameofmobiles = { "Tony", "Ramsung", "Pineapple", "Tivo", "Joppo", "PlusPlus", "Mixel", "Thor" };
    //List of all the book names from which name of that particular book will be given randomly
    private String[] nameofbooks = { "Aroundtheworld", "MerchantOfVenice", "War and Peace", "Ikigai", "Invisible Man",
            "Time Travel", "Macbeth", "TheAlchemist", "WillyWonka", "Sapiens" };
    private String myID;//Seller Id
    private ArrayList<Product> products = new ArrayList<Product>();// Will contain list of products
    private Random random = new Random();//Object of random class
    private Platform platform;
}