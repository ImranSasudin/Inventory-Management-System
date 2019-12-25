package inventory.model;

public class OrderProduct {
	
	private int prodid;
	private int orderid;
	private String status;
	private int orderProdQuantity;
	public boolean valid;
	private String orderDate;
	private int teacherid,teacherid2;
	
	public OrderProduct() {
		super();
	}

	public OrderProduct(int prodid, int orderid, String status, int orderProdQuantity, boolean valid) {
		super();
		this.prodid = prodid;
		this.orderid = orderid;
		this.status = status;
		this.orderProdQuantity = orderProdQuantity;
		this.valid = valid;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public int getTeacherid2() {
		return teacherid2;
	}

	public void setTeacherid2(int teacherid2) {
		this.teacherid2 = teacherid2;
	}
	
	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrderProdQuantity() {
		return orderProdQuantity;
	}

	public void setOrderProdQuantity(int orderProdQuantity) {
		this.orderProdQuantity = orderProdQuantity;
	}

	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
