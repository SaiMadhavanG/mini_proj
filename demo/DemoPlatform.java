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

public class DemoPlatform extends Platform {

	public DemoPlatform() {
		try {
			readFile.createNewFile();
			fr = new FileReader(readFile);
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			writeFile.createNewFile();
			fw = new FileWriter(writeFile);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public boolean addSeller(Seller aSeller) {
		sellerList.add(aSeller);
		return false;
	}

	@Override
	public void processRequests() {
		sc = new Scanner(fr);

		String request;
		while (true) {
			try {
				request = sc.nextLine();
				if (request.equals(""))
					request = sc.nextLine();
				if (request.equals(""))
					continue;
				String[] requestArray = request.split("\\s");
				String portalID = requestArray[0];
				String requestID = requestArray[1];
				if (requestArray[2].equals("Start")) {
					fw.write(portalID + " " + requestID + " ");
					for (Globals.Category category : Globals.Category.values()) {
						fw.write(new Globals().getCategoryName(category) + " ");
					}
					fw.write('\n');
				} else if (requestArray[2].equals("List")) {
					String category = requestArray[3];
					ArrayList<Product> products = new ArrayList<>();
					if (category.equals("Mobile")) {
						for (Seller seller : sellerList) {
							products.addAll(seller.findProducts(Category.Mobile));
							for (Product product : seller.findProducts(Category.Mobile)) {
								pidSellerMap.put(product.getProductID(), seller);
							}
						}
					} else if (category.equals("Book")) {
						for (Seller seller : sellerList) {
							products.addAll(seller.findProducts(Category.Book));
							for (Product product : seller.findProducts(Category.Book)) {
								pidSellerMap.put(product.getProductID(), seller);
							}
						}
					}
					for (Product product : products) {
						fw.write(portalID + " " + requestID + " "
								+ product.getName() + " " + product.getProductID()
								+ " " + product.getPrice() + " " + product.getQuantity()
								+ "\n");
					}
				} else if (requestArray[2].equals("Buy")) {

					String itemToBuy = requestArray[3];
					Integer quantity = Integer.parseInt(requestArray[4]);
					Seller seller = pidSellerMap.get(itemToBuy);
					if (seller.buyProduct(itemToBuy, quantity)) {
						fw.write(portalID + " " + requestID + " Success\n");
					} else {
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

	private File readFile = new File(Globals.toPlatform);
	private File writeFile = new File(Globals.fromPlatform);
	private FileReader fr;
	private FileWriter fw;
	private Scanner sc;
	private ArrayList<Seller> sellerList = new ArrayList<>();
	private HashMap<String, Seller> pidSellerMap = new HashMap<>();

}
