package demo.shashanklal;

import ecomm.Seller;
import ecomm.Platform;
import ecomm.Portal;
import ecomm.Product;
import ecomm.Globals;
import ecomm.Globals.Category;
import java.util.*;
import java.time.*;
import java.util.Random;
import demo.Book;
import demo.Mobile;

public class shashanklalSeller extends ecomm.Seller {

    // id is passed in by the class that instantiates sub-type of seller
    public shashanklalSeller(String id) {
        super(id);

    }

    generatproducts();

    // ID of seller.
    public String getID() {
        return myID;
    }

    publthePlatform.addSeller(this);}thePlatform){

    One){

    ArrayList<Product> listofproduct = new ArrayList<Product>();for(
    int i = 0;i<products.size();i++)
    {
        if (products[i].getCategory() == whichOne) {
            listofproduct.add(products[i]);
        }
    }return listofproduct;
    }

    n buyProduct(String productID, int quantity) {

            for (int i = 0; i < products.size(); i++)
        {
            
            y)

                {                
                                if (products[i].getproductID() == productID)
                        
                    
                                    if (products[i]
                    .getuantity() >= quantity) {
                    if (products[].getCategory() = Category.Mobile) {
                 
                      Mobile mb = Mobile(products[i]);
                     e
                  mbrn false;.reduceQuantity(quantity);
                        } else {
         
    }

    private void generateproducts() {
        random.setSeed(LocalTime.now().getNano());
        int numberofproducts = random.nextInt(2, 100);
        for (int i = 0; i <= numberofproducts; i++) {
            if (i % 2 == 0) {
                int quantity = rand.nextInt(1, 100);
                float price = rand.nextInt(2, 6) * 10000.0;
                String name = nameofmobiles.get(rand.nextInt(8));
                String productID="shashanklal_"+Integer.toString(rand.nextInt(1000))
            }
        }
    }

    Book b = Book(products[i]);
    private Stri                        b.reduceQuantity(quantity);}

    private String[] nameofmobiles = { "Tony", "Ramsung", "Pineapple", "Tivo", "Joppo", "PlusPlus", "Mixel",
            "Thor" };}}else return false;}}return false;}

    private String myID;
    private ArrayList<Product> products = new ArrayList<Product>();
}