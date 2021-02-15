package com.toolcompany.toolrental.util;

import java.text.NumberFormat;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Helper Class that handles cleaning of user-entered Strings, Dates, and Currency values.
 */
public class StringFormatting {

    /**
     * Constructor for initializing a StringFormatting object.
     */
    public StringFormatting() {
    }

    /**
     * Cleans a String entered by the User.
     * Removes any whitespace.
     * Cleans Invisible Control Chars.
     * Cleans Carriage Returns (\r), Line Feeds (\n), tab (\t), and End of Line (\r\n).
     * @param userEntry    A String from the user.
     * @return             A cleaned version of the entry String.
     */
    public static String cleanString(String userEntry) {
        return userEntry.replaceAll(" ", "").replaceAll("\\p{C}", "").replaceAll("[\\r\\n\\t]", "").replaceAll("\u00a0","");
    }

    /**
     * Takes a percentage String, and returns it as an int value, given as a whole number between 0 - 100.
     *
     * @param percentage    A percentage given as a String, in the format "82%"
     * @return              An int version of the percentage, given as a whole number between 0 - 100.
     */
    public static int parsePercentage(String percentage) {
        percentage = cleanString(percentage).replaceAll("%", "");
        int percentInt;
        try {
            percentInt = Integer.parseInt(percentage);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Please enter the Discount Percentage as a whole number, between 0 and 100.");
        }
        return percentInt;
    }

    /**
     * Formats a percent, given as a whole number between 0 - 100.
     * Formatted Value Example: 82%
     *
     * @param percentage    A percentage given as an int.
     * @return              A formatted version of the int as a String.
     */
    public static String formatPercentage(int percentage) {
        return percentage + "%";
    }

    /**
     * Formats a currency value given as a BigDecimal to a String in USD rounded half up to cents.
     * Formatted Value Example: $99,999.99
     * Uses the Java class NumberFormat to format the BigDecimal appropriately.
     *
     * @param chargeValue    A currency value given as a BigDecimal. This currency value should already have been formatted with formatCurrency.
     * @return               A formatted version of the int as a String.
     */
    public static String formatCurrencyToString(BigDecimal chargeValue) {
        Locale currencyLocale = Locale.US;
        return NumberFormat.getCurrencyInstance(currencyLocale).format(chargeValue);
    }

    /**
     * Formats a BigDecimal to be rounded half up to cents.
     * Formatted Value Example: 99999.99
     * Uses the BigDecimal method setScale to accurately round half up to cents.
     *
     * @param chargeValue    A currency value given as a BigDecimal.
     * @return               A formatted version of the int as a String.
     */
    public static BigDecimal formatCurrency(BigDecimal chargeValue) {
        int newScale = 2;
        return chargeValue.setScale(newScale, RoundingMode.HALF_UP);
    }

    /**
     * Formats a date, given as a LocalDate, to mm/dd/yy
     * Formatted Value Example: 02/14/21
     *
     * @param date    A date given as a LocalDate.
     * @return        A formatted version of the date as a String.
     */
    public static String formatDateToString(LocalDate date, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return date.format(formatter);
    }

}
