import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
	
	private Card card;
	ShoppingCart cart = new ShoppingCart();
	Scanner sc = new Scanner(System.in);
	private List<Product> products = Admin.availableProducts();
	
	public Customer(int userId, String username, String password, String email) {
        super(userId, username, password, email);
    }
	
	public void linkCard() {
		System.out.println("Please enter your Card Number: ");
		String cardNumber = sc.nextLine();
        Card card = new Card(cardNumber);
        this.card = card;
        card.setOwner(this);
        //System.out.println("aaaaaaaa" + this.card.getBalance());
    }
	
	public Card getCard() {
		return this.card;
	}
	
	public boolean existantProductId(int newProdId) {
		boolean exist = false;
	    	for (Product prod : products) {
	    		if (prod.getProductId() == newProdId) {
	    			exist = true;
	    			return exist;
	    		}
	    	}
	    	return exist;
	}
	
	public Product getProductById(int productId) {
		for (Product prod : products) {
			if (prod.getProductId() == productId) {
				prod.afficher();
				return prod;
			}
		}
		System.out.println("Product Unavailable.");
		return null;
	}
	
	public void addToCart() {
		
		System.out.println("Available Products with Ratings:");
	    for (Product product : products) {
	        System.out.println("Product ID: " + product.getProductId());
	        System.out.println("Name: " + product.getProductName());
	        System.out.println("Rating: " + product.getAverageRating());
	        System.out.println("-----------");
	    }

		
		// allows the customer to pick the id and qt of the product to add to cart
		System.out.println("Provide the ID of the product you'd like to add to your cart: ");
		int prodId = sc.nextInt();
		sc.nextLine();
		while (!existantProductId(prodId)) {
			System.out.println("Inexistant product ID. Please provide a valid product ID: ");
			prodId = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("How many items of this product would you like to buy: ");
		int quantity = sc.nextInt();
		while ((quantity < 1)) {
			System.out.println("Quantity cannot be inferior to 1. Please provide a valid quantity: ");
			quantity = sc.nextInt();
			sc.nextLine();
		} 
		while (quantity > getProductById(prodId).getQuantity()){
			System.out.println("Quantity cannot be superior to quantity in stock. Quantity in stock:  " + getProductById(prodId).getQuantity() +". Please re-enter the quantity: ");
			quantity = sc.nextInt();
			sc.nextLine();
		}
		// creates product with provided id, creates cartItme with said product and provided quantity, and adds it to cart
		Product product = getProductById(prodId);
		CartItem it = new CartItem(product, quantity);
		cart.addItem(it);
	}
	
	public void removeFromCart() {
		System.out.println("Provide the ID of the product you'd like to remove from your cart: ");
		int prodId = sc.nextInt();
		sc.nextLine();
		Boolean test = false;
		for (CartItem it : cart.getAllCartItems()) {
			if (it.getProduct().getProductId() == prodId) {
				test = true;
			}
		}
		
		while (!test) {
			System.out.println("Product with this ID inexistant in your cart. Please provide the ID of the product from your cart you'd like to delete.");
			prodId = sc.nextInt();
			sc.nextLine();
		}
		
		cart.deleteItem(prodId);
		System.out.println("Product successfully deleted.");
	}
	
	public void updateProductInCart() {
	    System.out.println("Provide the ID of the product you'd like to update in your cart: ");
	    int prodId = sc.nextInt();
	    sc.nextLine();
	    boolean found = false;

	    for (CartItem it : cart.getAllCartItems()) {
	        if (it.getProduct().getProductId() == prodId) {
	            found = true;
	            System.out.println("Enter the new quantity for this product: ");
	            int newQuantity = sc.nextInt();
	            sc.nextLine();
	            while (newQuantity < 1) {
	                System.out.println("Quantity cannot be less than 1. Enter a valid quantity: ");
	                newQuantity = sc.nextInt();
	                sc.nextLine();
	            }
	            while (newQuantity > getProductById(prodId).getQuantity()){
	    			System.out.println("Quantity cannot be superior to quantity in stock. Quantity in stock:  " + getProductById(prodId).getQuantity() +".");
	    			newQuantity = sc.nextInt();
	                sc.nextLine();
	    		}
	            cart.updateItem(it, newQuantity);
	            System.out.println("Product quantity updated successfully.");
	            break;
	        }
	    }

	    if (!found) {
	        System.out.println("Product with this ID is not in your cart.");
	    }
	}
	
//	public void completeOrder() {
//        Order order = cart.convertToOrder(this);
//        // Optionally, store the order or perform additional processing
//        order.completeOrder();
//    }
	
	public void completeOrderWithCard(Order order) {
        if (card != null) {
            double orderTotal = order.getTotalPrice();

            if (card.hasEnoughBalance(orderTotal)) {
                boolean paymentSuccess = card.withdrawAmount(orderTotal);
                if (paymentSuccess) {
                    order.completeOrder();
                } else {
                    System.out.println("Payment failed. Insufficient balance.");
                }
            } else {
                System.out.println("Payment failed. Insufficient funds in the card.");
            }
        } else {
            System.out.println("No card linked to this customer.");
        }
    }
	
	// filter by brand
    public List<Product> filterByBrand(String brand) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getBrand().equalsIgnoreCase(brand)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
    
    // filter by price range
    public List<Product> filterByPriceRange(double minPrice, double maxPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }
    
    // filter by product type computer
    public List<Product> filterComputers() {
        List<Product> computerProducts = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof Computer) {
                computerProducts.add(product);
            }
        }
        return computerProducts;
    }
    
    public String getuserName() {
    	return this.getUserName();
    }

    // filter by product type phone
    public List<Product> filterPhones() {
        List<Product> phoneProducts = new ArrayList<>();

        for (Product product : products) {
            if (product instanceof Phone) {
                phoneProducts.add(product);
            }
        }
        return phoneProducts;
    }
    
    public List<Product> searchProducts(String keyword) {
        List<Product> searchResults = new ArrayList<>();

        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(keyword.toLowerCase())
                || product.getBrand().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(product);
            }
        }
        return searchResults;
    }
    
    public void addReview(Product product, String comment, int rating) {
        Review review = new Review(this.getuserName(), comment, rating);
        product.addReview(review);
    }

}
