package inventory.model;

public class Stationery extends ProductBean{
	private String stationeryBrand;
	
	public Stationery(){
		super();
	}

	public Stationery(int prodid, String prodName, double prodPrice, double sellPrice,  int prodQuantity, int supplierid, String prodType, boolean valid, String stationeryBrand) {
		super(prodid, prodName, prodPrice,sellPrice, prodQuantity,  supplierid, prodType, valid);
		this.stationeryBrand = stationeryBrand;
	}

	public String getStationeryBrand() {
		return stationeryBrand;
	}

	public void setStationeryBrand(String stationeryBrand) {
		this.stationeryBrand = stationeryBrand;
	}
}
