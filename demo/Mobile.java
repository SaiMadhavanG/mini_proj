package demo;

import ecomm.Globals;
import ecomm.Product;

public class Mobile extends Product {

    public Mobile(Globals.Category category, String name, String productID, float price, int quantity) {
        this.category = category;
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    Mobile() {
        this.name = "_empty_name";
        this.productID = "_null_productID";
        this.price = 0.0f;
        this.quantity = 0;
    }

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

    public int reduceQuantity(int reduction) {
        quantity = quantity - reduction;
        return quantity;
    }

    private Globals.Category category;
    private String name;
    private String productID;
    private float price;
    private int quantity;

}
