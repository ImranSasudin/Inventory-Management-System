package inventory.model;

import java.util.List;

import inventory.model.SupplierBean;

public class SupplierBean {

	private int supplierid;
	private String suppName;
	private String suppAddress;
	private String suppContact;
	private String suppEmail;
	public boolean valid;
	
	public int getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}
	public String getSuppName() {
		return suppName;
	}
	public void setSuppName(String suppName) {
		this.suppName = suppName;
	}
	public String getSuppAddress() {
		return suppAddress;
	}
	public void setSuppAddress(String suppAddress) {
		this.suppAddress = suppAddress;
	}
	public String getSuppContact() {
		return suppContact;
	}
	public void setSuppContact(String suppContact) {
		this.suppContact = suppContact;
	}
	public String getSuppEmail() {
		return suppEmail;
	}
	public void setSuppEmail(String suppEmail) {
		this.suppEmail = suppEmail;
	}
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}


}
