package demo;
/*
    Mini-Project done by:
    Samarjeet Wankhade(IMT2021013)
    Sai Madhavan G(IMT2021101)
    Shashank Lal(IMT2021538)
*/
//Importing the globals and product pacakges
import ecomm.Globals;
import ecomm.Product;

public class Book extends Product//Here Book is the child class of Product
{
    public Book(Globals.Category category, String name, String productID, float price, int quantity)//Parametrised constructor
    {
        //Initialisation of the data members
        this.category = category;
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }
    //This function will reduce quantity for the product requested by the user
    public void reduceQuantity(int reduction) {
        quantity = quantity - reduction;
        // return quantity;
    }

    public Book()//Default constructor
    {
        this.name = "_empty_name";
        this.productID = "_null_productID";
        this.price = 0.0f;
        this.quantity = 0;

    }
    //Getter functions to get Category,Name,productID,Price and quantity respectively
    public Globals.Category getCategory()
    {
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
//Data members of the class Book
    private Globals.Category category;
    private String name;
    private String productID;
    private float price;
    private int quantity;
}
