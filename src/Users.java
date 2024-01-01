import java.util.HashMap;
import java.util.Map;

public class Users {
    private Map<Integer, Admin> adminMap;
    private Map<Integer, Customer> customerMap;
    //private int lastUserId = 0;

    public Users() {
        this.adminMap = new HashMap<>();
        this.customerMap = new HashMap<>();
    }
    
    public User getUserByUsername(String username) {
        for (Admin admin : adminMap.values()) {
            if (admin.getUserName().equals(username)) {
                return admin;
            }
        }

        for (Customer customer : customerMap.values()) {
            if (customer.getUserName().equals(username)) {
                return customer;
            }
        }

        return null; // User with the specified username not found
    }

    
    public int generateNewUserId() {
        int lastUserId = User.getLastUserId();
        lastUserId++;
        return lastUserId;
    }
    
    public boolean usernameExists(String username) {
        for (Admin admin : adminMap.values()) {
            if (admin.getUserName().equals(username)) {
                return true; // Username already exists in admins
            }
        }
        
        for (Customer customer : customerMap.values()) {
            if (customer.getUserName().equals(username)) {
                return true; // Username already exists in customers
            }
        }
        
        return false; // Username doesn't exist

    }

    public boolean addAdmin(Admin admin) {
        if (!adminMap.containsKey(admin.getUserId())) {
            adminMap.put(admin.getUserId(), admin);
            return true;
        }
        return false; // Admin already exists
    }

    public boolean addCustomer(Customer customer) {
        if (!customerMap.containsKey(customer.getUserId())) {
            customerMap.put(customer.getUserId(), customer);
            return true;
        }
        return false; // Customer already exists
    }

    public Admin getAdminById(int userId) {
        return adminMap.get(userId);
    }

    public Customer getCustomerById(int userId) {
        return customerMap.get(userId);
    }
}
