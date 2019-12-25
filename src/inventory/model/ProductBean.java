package inventory.model;

import java.util.List;

public class ProductBean {

	private int prodid;
	private String prodName;
	private double prodPrice;
	private int prodQuantity;
	private int supplierid;
	private String prodType;
	private double sellPrice;
	public boolean valid;
	
	public ProductBean() {
		super();
	}

	public ProductBean(int prodid, String prodName, double prodPrice, double sellPrice, int prodQuantity, int supplierid, String prodType, boolean valid) {
		super();
		this.prodid = prodid;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.sellPrice = sellPrice;
		this.prodQuantity = prodQuantity;
		this.supplierid = supplierid;
		this.prodType = prodType;
		this.valid = valid;
	}

	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public int getProdQuantity() {
		return prodQuantity;
	}
	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	public int getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	} 
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	}
