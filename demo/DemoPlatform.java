/*
    Mini-Project done by:
    Samarjeet Wankhade(IMT2021013)
    Sai Madhavan G(IMT2021101)
    Shashank Lal(IMT2021538)
*/
package demo;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.NoSuchElementException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;
import ecomm.Globals.Category;

//DemoPlatform inherits from the class Platform
public class DemoPlatform extends Platform {

	public DemoPlatform()//Constructor
	{
		try
		{
			readFile.createNewFile();
			fr = new FileReader(readFile);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		try
		{
			writeFile.createNewFile();
			fw = new FileWriter(writeFile);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	//Adding the seller to the seller List
	public boolean addSeller(Seller aSeller) {
		sellerList.add(aSeller);
		return false;
	}

	@Override
	//Overriding the processRequests function
	public void processRequests() {
		sc = new Scanner(fr);

		String request;
		while (true) {
			try {
				request = sc.nextLine();
				if (request.equals(""))
					request = sc.nextLine();
				//Skipping if empty string
				if (request.equals(""))
					continue;
				String[] requestArray = request.split("\\s");
				String portalID = requestArray[0];
				String requestID = requestArray[1];
				//Writing to the file according to the request command
				if (requestArray[2].equals("Start"))
				{
					fw.write(portalID + " " + requestID + " ");
					for (Globals.Category category : Globals.Category.values()) {
						fw.write(new Globals().getCategoryName(category) + " ");
					}
					fw.write('\n');
				}
				else if (requestArray[2].equals("List"))
				{
					String category = requestArray[3];
					//Arraylist of products
					ArrayList<Product> products = new ArrayList<>();
					//Adding all mobiles to the products arraylist
					if (category.equals("Mobile"))
					{
						for (Seller seller : sellerList) {
							products.addAll(seller.findProducts(Category.Mobile));
							for (Product product : seller.findProducts(Category.Mobile)) {
								pidSellerMap.put(product.getProductID(), seller);
							}
						}
					}
					else if (category.equals("Book")) //Adding all books to the products of arraylist
					{
						for (Seller seller : sellerList) {
							products.addAll(seller.findProducts(Category.Book));
							for (Product product : seller.findProducts(Category.Book)) {
								pidSellerMap.put(product.getProductID(), seller);
							}
						}
					}
					//Writing name, productID, price and quantity of the products
					for (Product product : products)
					{
						fw.write(portalID + " " + requestID + " "
								+ product.getName() + " " + product.getProductID()
								+ " " + product.getPrice() + " " + product.getQuantity()
								+ "\n");
					}
				}
				else if (requestArray[2].equals("Buy"))
				{
					String itemToBuy = requestArray[3];
					Integer quantity = Integer.parseInt(requestArray[4]);
					//Getting the particular seller from the map created
					Seller seller = pidSellerMap.get(itemToBuy);
					//It will write success if buy product returns true or else it will write false
					if (seller.buyProduct(itemToBuy, quantity))
					{
						fw.write(portalID + " " + requestID + " Success\n");
					}
					else
					{
						fw.write(portalID + " " + requestID + " Failure\n");
					}
				}
				fw.flush();
			} catch (NoSuchElementException e) {
				break;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	//Datamembers of Platform
	private File readFile = new File(Globals.toPlatform);
	private File writeFile = new File(Globals.fromPlatform);
	private FileReader fr;
	private FileWriter fw;
	private Scanner sc;
	private ArrayList<Seller> sellerList = new ArrayList<>();
	private HashMap<String, Seller> pidSellerMap = new HashMap<>();
}