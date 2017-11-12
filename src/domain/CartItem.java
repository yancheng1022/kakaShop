package domain;

public class CartItem {
	private Product product;
	private int num;
	private double subTotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getsubTotal() {
		return subTotal;
	}
	public void setsubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	
}
