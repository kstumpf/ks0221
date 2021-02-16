package com.toolcompany.toolrental;

import com.toolcompany.toolrental.util.LocalDateValidator;
import com.toolcompany.toolrental.util.StringFormatting;
import com.toolcompany.toolrental.util.RentalCalculator;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * A class that handles the Checkout Process for the User.
 */
public class Checkout {
    private static final int rentalDaysMin = 1;
    // There is no defined rentalDaysMax.
    private static final int discountPercentMin = 0;
    private static final int discountPercentMax = 100;
    private static final String checkoutDateFormat = "M/d/yy";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(checkoutDateFormat);

    /**
     * Constructor for initializing a Checkout object.
     */
    public Checkout() {
    }

    /**
     * Performs Checkout services for user renting a tool from Toolcompany.
     * Returns a rentalAgreement instance for the given tool.
     * Validates params entered. If valid params were entered, gets relevant tool info, calculates rental information,
     * and generates the rentalAgreement and prints it out to the terminal.
     *
     * @param toolCode              A four-letter, capitalized code that determines the given tool type and tool brand.
     * @param rentalDayCount        The number of days for which the customer will rent the tool. Must be >= 1.
     * @param discountPercentStr    The discount percent that will be applied to the charge. Must be whole num between 0 - 100.
     * @param checkoutDateStr       The date the tool was rented, specified at checkout, in M/d/yy format.
     * @return                      A rentalAgreement instance with the following values:
     *                                 Tool Code, Tool Type, Tool Brand, Rental Days,
     *                                 Check Out Date, Due Date, Daily Rental Charge, Pre-Discount Charge,
     *                                 Discount Percent, Discount Amount, Final Charge
     */
    public RentalAgreement performCheckout(String toolCode, int rentalDayCount, String discountPercentStr, String checkoutDateStr) {
        // Perform the validation of entered arguments.
        if (rentalDayCount < rentalDaysMin) {
            throw new IllegalArgumentException("Please enter a valid number of Rental Days. You must rent the tool for at least 1 day.");
        }

        int discountPercent = StringFormatting.parsePercentage(discountPercentStr);
        if (discountPercent < discountPercentMin || discountPercent > discountPercentMax) {
            throw new IllegalArgumentException("Please enter the Discount Percentage as a whole number, between 0 and 100.");
        }

        checkoutDateStr = StringFormatting.cleanString(checkoutDateStr);
        LocalDateValidator dateValidator = new LocalDateValidator(dateFormatter);
        boolean isValidCheckoutDate = dateValidator.isValid(checkoutDateStr);
        if (!isValidCheckoutDate) {
            throw new IllegalArgumentException("Please enter a valid Checkout Date, in the format M/d/yy.");
        }
        LocalDate checkoutDate = LocalDate.parse(checkoutDateStr, dateFormatter);

        ToolcompanyStock availableTools = new ToolcompanyStock();
        toolCode = StringFormatting.cleanString(toolCode).toUpperCase();
        boolean isValidToolCode = availableTools.isValid(toolCode);
        if (!isValidToolCode) {
            throw new IllegalArgumentException("The entered Tool Code " + toolCode + " does not exist. Please enter a valid Tool Code.");
        }

        // Generate the relevant information for crafting the Rental Agreement.
        Tool tool = availableTools.getToolFromCode(toolCode);
        RentalCalculator rentalCalculator = new RentalCalculator(tool, rentalDayCount, discountPercent, checkoutDate);
        RentalCharges rentalChargeInfo = rentalCalculator.getChargeInfo();

        // Generate the Rental Agreement, and print it.
        RentalAgreement rentalAgreement = new RentalAgreement(tool, rentalChargeInfo);
        rentalAgreement.printRentalAgreement();

        return rentalAgreement;
    }

}

