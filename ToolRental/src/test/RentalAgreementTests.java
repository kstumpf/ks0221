package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.toolcompany.toolrental.Checkout;
import com.toolcompany.toolrental.RentalAgreement;
import com.toolcompany.toolrental.util.StringFormatting;

public class RentalAgreementTests {
    @Test(expected = IllegalArgumentException.class)
    public void RentalAgreement_Test1() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 5, "101%", "9/3/15");
    }

    @Test
    public void RentalAgreement_Test2() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("LADW", 3, "10%", "7/2/20");
        assertEquals("LADW", rentalAgreement.getToolCode());
        assertEquals("Ladder", rentalAgreement.getToolType());
        assertEquals("Werner", rentalAgreement.getToolBrand());

        assertEquals(3, rentalAgreement.getRentalDayCount());
        assertEquals("07/02/20", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("07/05/20", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$1.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // 7/3/20 is observed 4th (no holiday charge), 7/4/20 and 7/5/20 are Sat and Sun weekend days (weekend charge)
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals("$3.98", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("10%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.40", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$3.58", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test3() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("CHNS", 5, "25%", "7/2/15");
        assertEquals("CHNS", rentalAgreement.getToolCode());
        assertEquals("Chainsaw", rentalAgreement.getToolType());
        assertEquals("Stihl", rentalAgreement.getToolBrand());

        assertEquals(5, rentalAgreement.getRentalDayCount());
        assertEquals("07/02/15", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("07/07/15", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$1.49", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // 7/3/15 is observed 4th (holiday charge), 7/4/15 and 7/5/15 are Sat and Sun weekend days (no weekend charge), 7/6 and 7/7 are Mon and Tue.
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals("$4.47", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("25%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$1.12", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$3.35", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test4() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKD", 6, "0%", "9/3/15");
        assertEquals("JAKD", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("DeWalt", rentalAgreement.getToolBrand());

        assertEquals(6, rentalAgreement.getRentalDayCount());
        assertEquals("09/03/15", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("09/09/15", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // 9/4 fri, 9/5 & 9/6 weekend (no holiday charge), 9/7 is Labor Day (no weekend charge), 9/8 and 9/9 weekdays
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals("$8.97", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("0%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$8.97", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test5() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 9, "0%", "7/2/15");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(9, rentalAgreement.getRentalDayCount());
        assertEquals("07/02/15", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("07/11/15", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // 7/3 Fri is observed holiday, 7/4 and 7/5 weekend, 7/6 - 7/10 weekday, 7/11 Sat
        assertEquals(5, rentalAgreement.getChargeDays());
        assertEquals("$14.95", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("0%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$14.95", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test6() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 4, "50%", "7/2/20");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(4, rentalAgreement.getRentalDayCount());
        assertEquals("07/02/20", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("07/06/20", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // 7/3 holiday, 7/4 and 7/5 weekend, 7/6 mon
        assertEquals(1, rentalAgreement.getChargeDays());
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("50%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$1.50", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$1.49", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void RentalAgreement_Test_ZeroDays() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 0, "0%", "2/15/21");
    }

    @Test
    public void RentalAgreement_Test_IndependenceDayOnSunday() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 5, "50%", "7/1/21");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(5, rentalAgreement.getRentalDayCount());
        assertEquals("07/01/21", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("07/06/21", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // 7/2 is Fri, 7/3 and 7/4 weekend, 7/5 is holiday, 7/6 is tue.
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals("$5.98", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("50%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test_LowerCase() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("jakr", 5, "50%", "7/1/21");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(5, rentalAgreement.getRentalDayCount());
        assertEquals("07/01/21", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("07/06/21", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // 7/2 is Fri, 7/3 and 7/4 weekend, 7/5 is holiday, 7/6 is tue.
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals("$5.98", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("50%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test_NoPercentSymbol() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout(" jakr   ", 5, "50", "7/1/21");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(5, rentalAgreement.getRentalDayCount());
        assertEquals("07/01/21", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("07/06/21", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // 7/2 is Fri, 7/3 and 7/4 weekend, 7/5 is holiday, 7/6 is tue.
        assertEquals(2, rentalAgreement.getChargeDays());
        assertEquals("$5.98", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("50%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void RentalAgreement_Test_BadDateFormat_mddyyyy() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 2, "0%", "2/15/2021");
    }

    @Test(expected = IllegalArgumentException.class)
    public void RentalAgreement_Test_BadDateFormat_ddmyy() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 2, "0%", "15/2/21");
    }

    @Test
    public void RentalAgreement_Test_NoChargeDaysOnWeekend_HasDiscountPercent() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 2, "50", "2/5/21");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(2, rentalAgreement.getRentalDayCount());
        assertEquals("02/05/21", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("02/07/21", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // Checked out on Sat and Sun, no charge.
        assertEquals(0, rentalAgreement.getChargeDays());
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("50%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test_OverNewYear() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 5, "50", "12/30/20");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(5, rentalAgreement.getRentalDayCount());
        assertEquals("12/30/20", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("01/04/21", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));

        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals("$8.97", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("50%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$4.49", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$4.48", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test_LaborDayWeekend2021() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 3, "0", "9/3/21");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(3, rentalAgreement.getRentalDayCount());
        assertEquals("09/03/21", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("09/06/21", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // Labor Day is on Mon, 9/6
        assertEquals(0, rentalAgreement.getChargeDays());
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("0%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

    @Test
    public void RentalAgreement_Test_LaborDayWeekend2022() {
        Checkout checkout = new Checkout();
        RentalAgreement rentalAgreement = checkout.performCheckout("JAKR", 3, "0", "9/2/22");
        assertEquals("JAKR", rentalAgreement.getToolCode());
        assertEquals("Jackhammer", rentalAgreement.getToolType());
        assertEquals("Ridgid", rentalAgreement.getToolBrand());

        assertEquals(3, rentalAgreement.getRentalDayCount());
        assertEquals("09/02/22", StringFormatting.formatDateToString(rentalAgreement.getCheckoutDate(), "MM/dd/yy"));
        assertEquals("09/05/22", StringFormatting.formatDateToString(rentalAgreement.getDueDate(), "MM/dd/yy"));
        assertEquals("$2.99", StringFormatting.formatCurrencyToString(rentalAgreement.getDailyCharge()));
        // Labor Day is on Mon, 9/5
        assertEquals(0, rentalAgreement.getChargeDays());
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getPreDiscountCharge()));
        assertEquals("0%", StringFormatting.formatPercentage(rentalAgreement.getDiscountPercent()));
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getDiscountAmount()));
        assertEquals("$0.00", StringFormatting.formatCurrencyToString(rentalAgreement.getFinalCharge()));
    }

}