import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
	
	private static List<Product> products = new ArrayList<>();

	Scanner scanner = new Scanner(System.in);
	
    public Admin(int userId, String username, String password, String email) {
        super(userId, username, password, email);
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
    
    public String getuserName() {
    	return this.getUserName();
    }
    
    public void createProduct() {
    	System.out.println("Enter product ID:");
    	int productId = scanner.nextInt();
    	scanner.nextLine();
    	while (existantProductId(productId)) {
    		System.out.println("A product with the specified ID already exists, please enter another: ");
    		productId = scanner.nextInt();
    		scanner.nextLine();
    	}
    	
    	System.out.println("Enter product name:");
        String productName = scanner.nextLine();

        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter product type (1 for Computer, 2 for Phone):");
        int productType = scanner.nextInt();
        scanner.nextLine();
        
        if (productType == 1) {
            System.out.println("Enter computer brand:");
            String brand = scanner.nextLine();
            System.out.println("Enter computer processor:");
            String processor = scanner.nextLine();
            System.out.println("Enter computer ram:");
            int ram = scanner.nextInt()
;           Product newProduct = new Computer(productId, productName, price, quantity, brand, processor, ram);
            products.add(newProduct);
        } else if (productType == 2) {
        	System.out.println("Enter phone brand:");
            String brand = scanner.nextLine();
            System.out.println("Enter phone model:");
            String model = scanner.nextLine();
            System.out.println("Enter screen size:");
            double screenSize  = scanner.nextDouble();
            Product newProduct = new Phone(productId, productName, price, quantity, brand, model, screenSize);
            products.add(newProduct);
        } else {
            System.out.println("Invalid product type.");
        }

        System.out.println("Product added successfully!");
    }
    
    public void showAllProducts() {
    	for (Product prod : products){
    		prod.afficher();
    	}
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
    
    public void updateProduct() {
    	System.out.println("Enter the ID of the product to update.");
    	int prodID = scanner.nextInt();
    	scanner.nextLine();
        Product existingProduct = getProductById(prodID);

        if (existantProductId(prodID)) {
            if (existingProduct instanceof Computer) {
                Computer existingComputer = (Computer) existingProduct;
                System.out.println("Enter attribute to update for the computer (1. Product Name, 2. Price, 3. Quantity, 4. Brand, 5. Processor, 6. RAM):");
                int attributeChoice = scanner.nextInt();
                scanner.nextLine();

                switch (attributeChoice) {
                    case 1:
                        System.out.println("Enter updated product name:");
                        String updatedName = scanner.nextLine();
                        existingComputer.setProductName(updatedName);
                        break;
                    case 2:
                        System.out.println("Enter updated price:");
                        double updatedPrice = scanner.nextDouble();
                        existingComputer.setPrice(updatedPrice);
                        break;
                    case 3:
                        System.out.println("Enter updated quantity:");
                        int updatedQuantity = scanner.nextInt();
                        existingComputer.setQuantity(updatedQuantity);
                        break;
                    case 4:
                        System.out.println("Enter updated brand:");
                        String updatedBrand = scanner.nextLine();
                        existingComputer.setBrand(updatedBrand);
                        break;
                    case 5:
                        System.out.println("Enter updated processor:");
                        String updatedProcessor = scanner.nextLine();
                        existingComputer.setProcessor(updatedProcessor);
                        break;
                    case 6:
                        System.out.println("Enter updated RAM:");
                        int updatedRam = scanner.nextInt();
                        existingComputer.setRam(updatedRam);
                        break;
                    default:
                        System.out.println("Invalid attribute choice.");
                        return;
                }

                System.out.println("Computer Product updated successfully!");
                existingComputer.afficher();
            } else if (existingProduct instanceof Phone) {
            	Phone existingPhone = (Phone) existingProduct;
                System.out.println("Enter attribute to update for the phone (1. Product Name, 2. Price, 3. Quantity, 4. Brand, 5. Model, 6. Screen Size):");
                int attributeChoice = scanner.nextInt();
                scanner.nextLine();

                switch (attributeChoice) {
                    case 1:
                        System.out.println("Enter updated product name:");
                        String updatedName = scanner.nextLine();
                        existingPhone.setProductName(updatedName);
                        break;
                    case 2:
                        System.out.println("Enter updated price:");
                        double updatedPrice = scanner.nextDouble();
                        existingPhone.setPrice(updatedPrice);
                        break;
                    case 3:
                        System.out.println("Enter updated quantity:");
                        int updatedQuantity = scanner.nextInt();
                        existingPhone.setQuantity(updatedQuantity);
                        break;
                    case 4:
                        System.out.println("Enter updated brand:");
                        String updatedBrand = scanner.nextLine();
                        existingPhone.setBrand(updatedBrand);
                        break;
                    case 5:
                        System.out.println("Enter updated model:");
                        String updatedModel = scanner.nextLine();
                        existingPhone.setModel(updatedModel);
                        break;
                    case 6:
                        System.out.println("Enter updated screen size:");
                        double updatedScreenSize = scanner.nextDouble();
                        existingPhone.setScreenSize(updatedScreenSize);
                        break;
                    default:
                        System.out.println("Invalid attribute choice.");
                        return;
                }

                System.out.println("Phone Product updated successfully!");
                existingPhone.afficher();
            }
        } else {
            System.out.println("Product not found. Cannot update.");
        }
    }
    
    void deleteProduct() {
    	System.out.println("Enter the ID of the product to delete.");
    	int prodID = scanner.nextInt();
    	scanner.nextLine();
    	if (existantProductId(prodID)) {
    		products.removeIf(product -> product.getProductId() == prodID);
    	}
    	else {
    		System.out.println("Product with specified ID inexistant.");
    	}
    }
    
    public static List<Product> availableProducts() {
    	return products;
    }
    
    // search by keyword
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
    
    
}