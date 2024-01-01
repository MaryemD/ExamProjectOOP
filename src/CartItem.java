
public class CartItem {
	private Product prod;
	private int qt;
	
	public CartItem(Product prod, int qt){
		this.prod = prod;
		this.qt = qt;
	}
	
	public void updateCartItem(int qt) {
		this.qt = qt;
	}
	
	public Product getProduct() {
		return this.prod;
	}
	
	public int getQuantity() {
		return this.qt;
	}
	
}
