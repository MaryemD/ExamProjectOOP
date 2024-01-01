import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private List<CartItem> cartItems;
	private static int orderIdCounter = 1000;
	
	public ShoppingCart() {
		this.cartItems = new ArrayList<>();
	}
	
	public void addItem(CartItem cartItem) {
		cartItems.add(cartItem);
		System.out.println(cartItem.getProduct().getProductName() + " added to cart.");
	}
	
	public void updateItem(CartItem it, int newQt) {
		it.updateCartItem(newQt);
	}
	
	public void deleteItem(int productId) {
		cartItems.removeIf(item -> item.getProduct().getProductId() == productId);
        System.out.println("Product with ID " + productId + " removed from cart.");
	}
	
	public void displayCart() {
		if (cartItems.isEmpty()) {
			System.out.println("Your cart is empty.");
		}else {
			System.out.println("Items in your cart:");
			for (CartItem it : cartItems) {
				System.out.println("-" + it.getProduct().getProductName() + "(Quantity: " + it.getQuantity() + ").");
			}
		}
	}
	
	public List<CartItem> getAllCartItems() {
        return this.cartItems;
    }
	
	
	private int generateOrderId() {
        return orderIdCounter++; // Increment the counter for each new order
    }
	
	public Order convertToOrder(Customer customer) {
        Order order = new Order(generateOrderId(), customer);
        for (CartItem item : cartItems) {
            order.addItem(item);
        }
        clearCart();
        return order;
    }

	private void clearCart() {
        cartItems.clear();
    }

}
