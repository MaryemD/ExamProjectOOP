
public class Phone extends Product {
    
    private String model;
    private double screenSize;

    public Phone(int productId, String productName, double price, int quantity, String brand, String model, double screenSize) {
        super(productId, productName, price, quantity, brand);
        //this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
    
    public void afficher() {
        super.afficher(); 
        System.out.println("Model: " + this.model);
        System.out.println("Screen Size: " + this.screenSize + " inches");
    }
}
