/*
    Mini-Project done by:
    Samarjeet Wankhade(IMT2021013)
    Sai Madhavan G(IMT2021101)
    Shashank Lal(IMT2021538)
*/
package demo;
//Importing the globals and product packages
import ecomm.Globals;
import ecomm.Product;

public class Mobile extends Product //Here Mobile class inherits Product class
{

    public Mobile(Globals.Category category, String name, String productID, float price, int quantity)//Parametrised Constructor for Mobile
    {
        //Initialisiation 
        this.category = category;
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public Mobile()//Default constructor
    {
        this.name = "_empty_name";
        this.productID = "_null_productID";
        this.price = 0.0f;
        this.quantity = 0;
    }
    //Getter methods(Methods to get Category, Name, productID, Price and Quantity respectively)
    public Globals.Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getProductID() {
        return productID;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    //This function wil reduce the quantity for the product requested by the user
    public void reduceQuantity(int reduction)
    {
        quantity = quantity - reduction;
    }
    private Globals.Category category;//Data members of the mobile class
    private String name;
    private String productID;
    private float price;
    private int quantity;

}