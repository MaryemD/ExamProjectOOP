public class Card {
    private String cardNumber;
    private double balance;
    private Customer owner; // Reference to the customer who owns the card

    public Card(String cardNumber, double balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        //this.owner = owner;
    }
    
    public Card (String cardNumber) {
    	this.cardNumber = cardNumber;
    	this.balance = 1500.00;
    }
    
    public void setOwner(Customer owner) {
    	this.owner = owner;
    	//owner.linkCard(this.cardNumber);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double d) {
        this.balance = d;
    }

    public Customer getOwner() {
        return owner;
    }

    public boolean hasEnoughBalance(double amount) {
        return balance >= amount;
    }

    public boolean withdrawAmount(double amount) {
        if (hasEnoughBalance(amount)) {
            balance -= amount;
            return true; // Withdrawal successful
        }
        return false; // Insufficient balance
    }
}
