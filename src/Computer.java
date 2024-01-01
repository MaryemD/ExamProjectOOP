
public class Computer extends Product {
	//private String brand;
	private String processor;
	private int ram;
	public Computer(int productId, String productName, double price, int quantity, String brand, String processor, int ram) {
		super(productId, productName, price, quantity, brand);
		//this.brand = brand;
		this.processor = processor;
		this.ram = ram;
	}
	
	
    
    public String getProcessor() {
        return this.processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }
    
    public int getRam() {
    	return this.ram;
    }
    
    public void setRam(int ram) {
    	this.ram = ram;
    }
    
    public void afficher() {
        super.afficher(); // Call the afficher() method of the parent class (Product)
        System.out.println("Processor: " + this.processor);
        System.out.println("RAM: " + this.ram + "GB");
    }
}
