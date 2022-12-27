package demo.samarth;

import ecomm.Seller;
import ecomm.Product;
import demo.Mobile;
import ecomm.Globals.Category;

public class SamEnterprises extends Seller(){

    SamEnterprises

    public SamEnterprises(String id) {
		myID = id;

        Random random= new Random();
        Integer number_of_mobiles=random.nextInt(25,50);
        HashMap myHash


        for(int i=0;i<number_of_mobiles;i++){
            Category mycat =Category.Mobile;
            Product newProduct=Mobile(mycat,);
        }
        
	}

    // ID of seller.
    public String getID() {
        return myID;
    }

    // Platform it is being added to.
    // Should in turn add itself to the Platform (with addSeller)
    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(myID);

    }

    // Seller to return listing of Products of specified Category
    public ArrayList<Product> findProducts(Globals.Category whichOne) {

    }

    // User wants to buy specified quantity of productID
    // Return true if transaction succeeds, false otherwise.
    // Transaction fails if incorrect productID or quantity exceeds available
    // inventory
    public boolean buyProduct(String productID, int quantity);

    private ArrayList<Product> inventory;

}