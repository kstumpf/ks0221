package com.toolcompany.toolrental.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Helper Class that handles validation for Strings containing a date,
 * prior to creating a LocalDate instance using the String.
 *
 * I researched online for best ways to validate a date entered as a String, and utilized the below article.
 * https://www.baeldung.com/java-string-valid-date
 */
public class LocalDateValidator {
    private DateTimeFormatter dateFormatter;

    /**
     * Constructor for initializing a LocalDateValidator object.
     *
     * @param dateFormatter    A DateTimeFormatter object containing the desired date formatting.
     *                            The LocalDateValidator will validate based on the entered date format.
     */
    public LocalDateValidator(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    /**
     * Returns a boolean value, where true means that the date String was formatted correctly,
     * and false means that an improperly formatted date String was entered.
     *
     * @param dateStr    A string containing a given date.
     * @return           A boolean value, stating whether the date String is formatted as desired.
     */
    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

}