package inventory.model;

public class Orders {
	
	private int orderid;
	private String orderDate;
	public boolean valid;	
	private int teacherid;
	
	public Orders() {
		super();
	}
	public Orders(int orderid, String orderDate) {
		super();
		this.orderid = orderid;
		this.orderDate = orderDate;
	}

	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
