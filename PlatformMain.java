import java.util.Scanner;

import demo.DemoPlatform;
import demo.saimadhavang.SMGSeller;
import demo.samarth.SamEnterprises;
import demo.shashanklal.shashanklalSeller;
import ecomm.Platform;
import ecomm.Seller;

public class PlatformMain {

	public static void main(String[] args) {

		Platform pf = new DemoPlatform(); // replace with appropriate derived class

		// create a number of sellers (of different sub-types of Seller)
		// Assign a name (sellerID) to each of them.

		// replace each of the XYZ below with the derived class name of one of the
		// team members.

		String listOfCommands = "Please use one of the following commands:-"
				+ "\n * Check - Processes all the commands on the PortalToPlatform.txt"
				+ "\n * Help - Lists out the list of commands"
				+ "\n * Quit - Exits the program\n";
		System.out.println("Welcome to your ecommerce platform!");

		Seller s1 = new SamEnterprises("SamSeller");
		s1.addPlatform(pf);
		pf.addSeller(s1);

		Seller s2 = new shashanklalSeller("ShashankSeller");
		s2.addPlatform(pf);
		pf.addSeller(s2);

		Seller s3 = new SMGSeller("SMGSeller");
		s3.addPlatform(pf);
		pf.addSeller(s3);

		System.out.println(listOfCommands);
		Scanner sc = new Scanner(System.in);

		// keep reading from System.in
		// If "Check" typed in
		// invoke

		while (true) {
			String command = sc.next();
			if (command.equals("Check") || command.equals("check")) {
				pf.processRequests();
				System.out.println("Requests processed successfully!\n");
			} else if (command.equals("Help") || command.equals("help")) {
				System.out.println(listOfCommands);
			} else if (command.equals("Quit") || command.equals("quit")) {
				break;
			} else {
				System.out.println("Please enter a valid command!\nType \"Help\" to get a list of commands\n");
			}
		}
		sc.close();
	}

}
