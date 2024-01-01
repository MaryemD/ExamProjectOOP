import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Users users = new Users(); // Create an instance of Users
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the system!");

        // Create admin and customer accounts
        Admin admin = new Admin(1, "admin", "adminpass", "admin@example.com");
        users.addAdmin(admin);

        Customer customer = new Customer(2, "customer", "customerpass", "customer@example.com");
        users.addCustomer(customer);

        boolean adminLoggedIn = login(users, scanner, admin);

        if (adminLoggedIn) {
            adminActions(scanner, admin);

            boolean loggedIn = false;
            //User currentUser = null;

            while (!loggedIn) {
                System.out.println("Do you want to (1) Log In or (2) Create an Account?");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    boolean customerLoggedIn = login(users, scanner, customer);

                    if (customerLoggedIn) {
                        customerActions(scanner, customer);
                        break;
                    }
                } else if (choice == 2) {  	
                	createAccount(users, scanner);
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }}
        }
    public static void createAccount(Users users, Scanner scanner) {
        System.out.println("Enter a username:");
        String newUsername = scanner.nextLine();

        if (users.usernameExists(newUsername)) {
            System.out.println("Username already exists. Please choose another username.");
            return;
        }

        System.out.println("Enter a password:");
        String newPassword = scanner.nextLine();

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        int newUserId = users.generateNewUserId();
        Customer newCustomer = new Customer(newUserId, newUsername, newPassword, email);
        users.addCustomer(newCustomer);

        System.out.println("Account created successfully! You can now log in.");
    }

    public static boolean login(Users users, Scanner scanner, User user) {
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("Enter your username:");
            String username = scanner.nextLine();

            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            User foundUser = users.getUserByUsername(username);
            
            //System.out.println(users.getUserByUsername(username));

            //System.out.println(foundUser.equals(user));
            
            if (foundUser != null && foundUser.LogIn(username, password)) {
                loggedIn = true;
                System.out.println("Logged in as " + username + ".");
            } else {
                System.out.println("Invalid username or password. Try again.");
            }
        }
        return true;
    }

    public static void adminActions(Scanner scanner, Admin admin) {
        boolean adminLoggedIn = true;
        while (adminLoggedIn) {
            // Admin actions
            System.out.println("Admin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Show All Products");
            System.out.println("5. Logout");

            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1:
                    admin.createProduct();
                    break;
                case 2:
                    admin.updateProduct();
                    break;
                case 3:
                    admin.deleteProduct();
                    break;
                case 4:
                    admin.showAllProducts();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    admin.LogOut();
                    adminLoggedIn = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    public static void customerActions(Scanner scanner, Customer customer) {
        customer.linkCard();
    	boolean customerLoggedIn = true;
        while (customerLoggedIn) {
            // Customer actions
            System.out.println("Customer Menu:");
            System.out.println("1. Add to Cart");
            System.out.println("2. Remove from Cart");
            System.out.println("3. Update Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Search Products");
            System.out.println("6. Filter Products by Brand");
            System.out.println("7. Filter Products by Price Range");
            System.out.println("8. Filter Computers");
            System.out.println("9. Filter Phones");
            System.out.println("10. Complete Order");
            System.out.println("11. Review Product");
            System.out.println("12. Logout");

            int customerChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (customerChoice) {
                case 1:
                    customer.addToCart();
                    break;
                case 2:
                    customer.removeFromCart();
                    break;
                case 3:
                    customer.updateProductInCart();
                    break;
                case 4:
                    customer.cart.displayCart();
                    break;
                case 5:
                    System.out.println("Enter a keyword to search products:");
                    String keyword = scanner.nextLine();
                    List<Product> searchResults = customer.searchProducts(keyword);
                    displaySearchResults(searchResults);
                    break;
                case 6:
                    System.out.println("Enter a brand to filter products:");
                    String brandFilter = scanner.nextLine();
                    List<Product> brandFilteredProducts = customer.filterByBrand(brandFilter);
                    displayFilterResults(brandFilteredProducts);
                    break;
                case 7:
                    System.out.println("Enter minimum price:");
                    double minPrice = scanner.nextDouble();
                    System.out.println("Enter maximum price:");
                    double maxPrice = scanner.nextDouble();
                    List<Product> priceFilteredProducts = customer.filterByPriceRange(minPrice, maxPrice);
                    displayFilterResults(priceFilteredProducts);
                    break;
                case 8:
                    List<Product> computerProducts = customer.filterComputers();
                    displayFilterResults(computerProducts);
                    break;
                case 9:
                    List<Product> phoneProducts = customer.filterPhones();
                    displayFilterResults(phoneProducts);
                    break;    
                case 10:
                    ShoppingCart cart = customer.cart;
                    if (!cart.getAllCartItems().isEmpty()) {
                        Order order = cart.convertToOrder(customer);
                        customer.completeOrderWithCard(order);
                    } else {
                        System.out.println("Your cart is empty. Add items before completing the order.");
                    }
                    break;
                case 11:
                    addReviewForProduct(scanner, customer);
                    break;
                case 12:
                    System.out.println("Logging out...");
                    customer.LogOut();
                    customerLoggedIn = false;
                    //System.exit(1);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void displaySearchResults(List<Product> searchResults) {
        System.out.println("Search Results:");
        if (searchResults.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : searchResults) {
                product.afficher();
            }
        }
    }

    private static void displayFilterResults(List<Product> filteredProducts) {
        System.out.println("Filtered Products:");
        if (filteredProducts.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : filteredProducts) {
                product.afficher();
            }
        }
    }
    
    public static void addReviewForProduct(Scanner scanner, Customer customer) {
        System.out.println("Enter the ID of the product you want to review:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter your review comment:");
        String comment = scanner.nextLine();

        System.out.println("Enter your rating (out of 5):");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = customer.getProductById(productId);
        if (product != null) {
            customer.addReview(product, comment, rating);
            System.out.println("Review added successfully for the product: " + product.getProductName());
        } else {
            System.out.println("Product not found. Review couldn't be added.");
        }
    }

}



