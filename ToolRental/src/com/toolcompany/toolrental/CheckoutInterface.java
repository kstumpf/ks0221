package com.toolcompany.toolrental;

public class CheckoutInterface {

    /**
     * Can use this as a way to easily run the performCheckout function, by replacing the values below.
     * Ideally this would have a GUI or would prompt the user for input, which it would then
     * enter into the performCheckout function.
     *
     * @param args
     */
    public static void main(String[] args) {
        Checkout checkout = new Checkout();

        try {
            RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 3, "0%", "2/15/21");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
