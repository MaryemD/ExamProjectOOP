public class PaymentProcessor {
    public static boolean processPayment(double amount, String cardNumber, String expiryDate, String cvv) {
        boolean isValidCard = validateCard(cardNumber, expiryDate, cvv);
        if (!isValidCard) {
            System.out.println("Invalid card details. Payment failed.");
            return false;
        }

        boolean hasEnoughBalance = checkBalance(amount, cardNumber);
        if (!hasEnoughBalance) {
            System.out.println("Insufficient balance. Payment failed.");
            return false;
        }

        boolean isPaymentSuccessful = performPayment(amount);
        if (isPaymentSuccessful) {
            System.out.println("Payment successful!");
            return true;
        } else {
            System.out.println("Payment failed. Please try again later.");
            return false;
        }
    }

    private static boolean validateCard(String cardNumber, String expiryDate, String cvv) {
        return true; // For simulation purposes, assume card details are valid
    }

    private static boolean checkBalance(double amount, String cardNumber) {
        double availableBalance = getAvailableBalance(cardNumber); 
        return availableBalance >= amount; 
    }

    private static boolean performPayment(double amount) {
        return true;
    }

    private static double getAvailableBalance(String cardNumber) {
        return 3500.00; 
    }
}
