package demo.shashanklal;

import ecom.Seller;

public class shashanklalSeller extends ecom.Seller {

    // id is passed in by the class that instantiates sub-type of seller
    public shashanklalSeller(String id) {
        super(id);
    }

    // ID of seller.
    public String getID() {
        return myID;
    }

    // Platform it is being added to.
    // Should in turn add itself to the Platform (with addSeller)
    public void addPlatform(Platform thePlatform) {
        theplatform.addSeller(this);
    }

    // Seller to return listing of Products of specified Category
    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        ArrayList<Product> listofproduct = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            if (products[i].getCategory() == whichOne) {
                listofproduct.add(products[i]);
            }
        }
        return listofproduct;
    }

    // User wants to buy specified quantity of productID
    // Return true if transaction succeeds, false otherwise.
    // Transaction fails if incorrect productID or quantity exceeds available
    // inventory
    public boolean buyProduct(String productID, int quantity)
    {
        for(int i=0;i<products.size();i++)
        {
            if()
oducts[i].getproductID()==productID

            {
                if(products[i].getquantity()>=quantity)
                {
                    setQuantity(quantity);
                    return true;
                }
                else
                {
                    return false;
                }
            }        }

        return false;    }

    private String myID;r
    private ArrayList<Prodpuct> products=new ArrayList<Product>();
}