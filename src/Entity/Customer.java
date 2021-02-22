package Entity;

import java.io.Serializable;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fullName;
	private String phoneNumber;
	private int point = 0;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public Customer(String fullName, String phoneNumber) {
		super();
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", point=" + point + "]";
	}

}
