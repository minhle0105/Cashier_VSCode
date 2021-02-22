package Behavior;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Entity.Bill;
import Entity.Product;

public class BillManagement {
	Scanner sc = new Scanner(System.in);
	ProductManagement pm = new ProductManagement();
	
	public Bill createBill() throws IOException {
		Bill bill = new Bill();
		pm.productStorage = (ArrayList<Product>) pm.ReadFile();
		ArrayList<Product> currentProductStorage = pm.productStorage;
		boolean condition = true;
		double currentBillAmount = 0.0;
		while (condition) {
			System.out.println("Enter product ID to check out");
			String productIDToCheckOut = sc.nextLine();
			if (productIDToCheckOut.equals("q")) {
				break;
			}
			System.out.println("Enter quantity");
			int quantity = Integer.parseInt(sc.nextLine());
			for (Product p : currentProductStorage) {
				if (p.getId().equals(productIDToCheckOut)) {
					bill.checkOutProduct.add(p);
					p.setQuantity(p.getQuantity()-quantity);
					currentBillAmount += (quantity * (p.getPrice()));
					bill.setTotalAmount(currentBillAmount);
				}
			}
		}
		pm.UpdateFile();
		return bill;
	}
}
