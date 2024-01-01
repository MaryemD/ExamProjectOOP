import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private LocalDateTime orderDateTime;
    private List<CartItem> items;
    private double totalPrice;
    private boolean isCompleted;
    private Customer customer;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.orderDateTime = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
        this.isCompleted = false;
        this.customer = customer;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public Customer getCustomer() {
        return customer;
    }


    public void completeOrder() {
        isCompleted = true;
        System.out.println("Order #" + orderId + " completed successfully.");
        for (CartItem item : items) {
            Product product = item.getProduct();
            int purchasedQuantity = item.getQuantity();
            product.setQuantity(product.getQuantity() - purchasedQuantity);
        }
    }
    
    public void addItem(CartItem item) {
        items.add(item);
        totalPrice += item.getProduct().getPrice() * item.getQuantity();
    }
}
