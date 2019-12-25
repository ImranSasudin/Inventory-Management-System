package inventory.model;

public class Food extends ProductBean{
	private String foodType;
	private int prodid;
	
	public Food() {
		super();
	}

	public Food(int prodid, String prodName, double prodPrice, double sellPrice, int prodQuantity, int supplierid, String prodType, boolean valid, String foodType) {
		super(prodid, prodName, prodPrice,sellPrice,prodQuantity, supplierid, prodType,  valid);
		this.foodType = foodType;
		this.prodid = prodid;
	}

	public int getProdid() {
		return prodid;
	}
	public void setProdid(int prodid) {
		this.prodid = prodid;
	}
	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
}
