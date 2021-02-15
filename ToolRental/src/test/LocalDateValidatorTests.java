package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.time.format.DateTimeFormatter;
import com.toolcompany.toolrental.util.LocalDateValidator;

public class LocalDateValidatorTests {
    @Test
    public void isValidStringDate_Yes_Mdyy() {
        String checkoutDateFormat = "M/d/yy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(checkoutDateFormat);
        LocalDateValidator dateValidator = new LocalDateValidator(dateFormatter);
        boolean isValidCheckoutDate = dateValidator.isValid("8/21/21");
        assertEquals(true, isValidCheckoutDate);
    }

    @Test
    public void isValidStringDate_No_Mddyyyy() {
        String checkoutDateFormat = "M/d/yy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(checkoutDateFormat);
        LocalDateValidator dateValidator = new LocalDateValidator(dateFormatter);
        boolean isValidCheckoutDate = dateValidator.isValid("8/21/2021");
        assertEquals(false, isValidCheckoutDate);
    }
}
