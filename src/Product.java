import java.util.ArrayList;
import java.util.List;

public abstract class Product {
	private int productId;
	private String productName;
	private double price;
	private int quantity;
	private String brand;
	private List<Review> reviews;
	
	public Product(int productId, String productName, double price, int quantity, String brand) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.reviews = new ArrayList<>();
    }
	
	public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
	
	public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void afficher() {
    	System.out.println("Product ID: " + this.productId);
    	System.out.println("Product Name: " + this.productName);
    	System.out.println("Product Price: " + this.price);
    	System.out.println("Quantity in Stock: " + this.quantity);
        System.out.println("Brand: " + this.brand);

    }
    
    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }
    
}
