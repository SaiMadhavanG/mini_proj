package demo.saimadhavang;

import ecomm.Seller;
import ecomm.Globals.Category;
import ecomm.Product;
import java.time.*;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class SMGSeller extends Seller {
    public SMGSeller(String ID) {
        super(ID);
        generateProducts();
    }

    private void generateProducts() {
        Random random = new Random();
        random.setSeed(LocalTime.now().getNano());
        int numOfProducts = random.nextInt(12);
        while(numOfProducts>5) {
            numOfProducts = random.nextInt(12);
        }
        Set<String> nameSet = new HashSet<String>();
        String[] names = {"Pro", "Max", "Prime", "Ultra", "Lite"};
        for (int i = 0; i < numOfProducts; i++) {
            LocalTime time = LocalTime.now();
            if(time.getNano()%2==1) {
                Category category = Category.Mobile;
                String name_1 = 
            }
            else {
                Category category = Category.Book;
            }

        }
    }

    private ArrayList<Product> productsList = new ArrayList<>();
}
