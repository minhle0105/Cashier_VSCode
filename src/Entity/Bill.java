package Entity;

import java.util.ArrayList;

public class Bill {
	public ArrayList<Product> checkOutProduct = new ArrayList<>();
	private double totalAmount = 0.0;
	
	public Bill() {
	}

	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String toString() {
		String result = "";
		for (Product p : checkOutProduct) {
			result += p.getName() + "\n";
		}
		result += Double.toString(this.totalAmount);
		return result;
	}
}

