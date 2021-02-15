package com.toolcompany.toolrental.util;

import com.toolcompany.toolrental.RentalCharges;
import com.toolcompany.toolrental.Tool;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Arrays;

/**
 * Contains methods for calculating Rental Agreement Date and Charge information.
 */
public class RentalCalculator {
    private Tool tool;
    private int rentalDayCount;
    private int discountPercent;
    private LocalDate checkoutDate;
    private static final HashSet<DayOfWeek> WEEKEND_DAYS = new HashSet<DayOfWeek>(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));

    /**
     * Constructor for initializing a RentalCalculator object.
     */
    public RentalCalculator(Tool tool, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        this.tool = tool;
        this.rentalDayCount = rentalDayCount;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }

    /**
     * Returns the number of Chargeable Days as an int for the entered Tool Type,
     * based on whether the given Tool Type charges for weekdays, weekends, or holidays.
     *
     * NOTE: July 4th will never fall on a weekend, as it is instead observed on the closest weekday.
     * NOTE: Checkout Date and Due Date may fall a on weekday, weekend, or holiday with no issues.
     *          The store supports renting out a tool or returning a tool on any day of the year.
     *          Its employees probably need a vacation!
     *
     * @return    The number of chargeable days during the rental period,
     *                excluding the day the tool was rented, through and
     *                including the due date.
     */
    private int getChargeableDays() {
        int chargeDayCount = 0;
        // The charge day count begins the day after the checkout date, and includes the final rental day.
        for (int i = 1; i < this.rentalDayCount + 1; i++) {
            LocalDate rentalDay = this.checkoutDate.plusDays(i);
            DayOfWeek dayOfWeek = rentalDay.getDayOfWeek();

            // Check if the date is a holiday. Check this first, as a given weekday may also be a holiday.
            if (isDateAHoliday(rentalDay)) {
                if (tool.getToolType().getHolidayCharge()) {
                    chargeDayCount++;
                }
            // Check if the date is a weekday (i.e. not a weekend day)
            } else if (!WEEKEND_DAYS.contains(dayOfWeek)) {
                if (tool.getToolType().getWeekdayCharge()) {
                    chargeDayCount++;
                }
            // Check if the date is a weekend day
            } else if (WEEKEND_DAYS.contains(dayOfWeek)) {
                if (tool.getToolType().getWeekendCharge()) {
                    chargeDayCount++;
                }
            }
        }

        return chargeDayCount;
    }

    /**
     * Returns the Due Date for the rented tool,
     * calculated by adding the Checkout Date and the Rental Day Count.
     *
     * @return    The due date as a LocalDate.
     */
    private LocalDate getDueDate() {
        return this.checkoutDate.plusDays(this.rentalDayCount);
    }

    /**
     * Checks if the current date is a holiday.
     * Valid holidays include Independence Day and Labor Day (the first Mon in September).
     * If the holiday falls on a weekend, it will be observed on the closest weekday.
     *
     * Used https://docs.oracle.com/javase/tutorial/datetime/iso/adjusters.html
     * to learn how to get first Monday of the month.
     *
     * @param date    A LocalDate holding the current date.
     * @return        A boolean value - true if the date is a holiday, and false if it is not a holiday.
     */
    private static boolean isDateAHoliday(LocalDate date) {
        boolean isHoliday = false;
        LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        LocalDate independenceDayObserved = independenceDay;
        // If it is a weekday, let it be.
        if (!WEEKEND_DAYS.contains(independenceDay.getDayOfWeek())) {

        // If it is a Saturday, set it to the Friday before.
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDayObserved = independenceDay.minusDays(1);
        // If it is a Sunday, set it to the Monday after.
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDayObserved = independenceDay.plusDays(1);
        }

        // Check if today is the day Independence Day is observed.
        if (date.compareTo(independenceDayObserved) == 0) {
            isHoliday = true;
            return isHoliday;
        }

        // Check if today is Labor Day.
        LocalDate laborDay = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        if (date.compareTo(laborDay) == 0) {
            isHoliday = true;
        }

        return isHoliday;
    }

    /**
     * Returns the charge for the tool for the given number of chargeable rental days,
     * before the discount has been applied.
     *
     * @param chargeDays    The number of chargeable rental days.
     * @return              The pre-discount rental charge as a BigDecimal, rounded half up to cents.
     */
    private BigDecimal getPreDiscountCharge(int chargeDays) {
        BigDecimal chargeDaysBigDecimal = new BigDecimal(chargeDays);
        BigDecimal preDiscountCharge = this.tool.getToolType().getDailyCharge().multiply(chargeDaysBigDecimal);
        return StringFormatting.formatCurrency(preDiscountCharge);
    }

    /**
     * Returns the total discount, calculated from discount percentage and pre-discount charge.
     *
     * @param discountPercent      The discount percentage, as an int.
     * @param preDiscountAmount    The pre-discounted amount, as a BigDecimal.
     * @return                     The Discount Amount, as a BigDecimal, rounded half up to cents.
     */
    private static BigDecimal getDiscountAmount(int discountPercent, BigDecimal preDiscountAmount) {
        BigDecimal percentBigDecimal = new BigDecimal(discountPercent).divide(new BigDecimal(100));
        BigDecimal discountAmount = preDiscountAmount.multiply(percentBigDecimal);
        return StringFormatting.formatCurrency(discountAmount);
    }

    /**
     * Returns the final charge for the tool rental, calculated as pre-discount charge minus discount amount.
     *
     * @param preDiscountCharge    The pre-discount charge, as a BigDecimal.
     * @param discountAmount       The discount amount, as a BigDecimal.
     * @return                     The Final Charge, as a BigDecimal, rounded half up to cents.
     */
    private static BigDecimal getFinalCharge(BigDecimal preDiscountCharge, BigDecimal discountAmount) {
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);
        return StringFormatting.formatCurrency(finalCharge);
    }

    /**
     * Returns a helper RentalCharges object, encapsulating all relevant charge information
     * for the Rental Agreement.
     *
     * @return    A helper RentalCharges object, encapsulating all relevant charge information.
     */
    public RentalCharges getChargeInfo() {
        LocalDate dueDate = getDueDate();
        int chargeDays = getChargeableDays();
        BigDecimal preDiscountCharge = getPreDiscountCharge(chargeDays);
        BigDecimal discountAmount = getDiscountAmount(this.discountPercent, preDiscountCharge);
        BigDecimal finalCharge = getFinalCharge(preDiscountCharge, discountAmount);

        RentalCharges rentalChargeInfo = new RentalCharges(this.tool.getToolType().getDailyCharge(), this.discountPercent, chargeDays,
                                              preDiscountCharge, discountAmount, finalCharge, this.rentalDayCount, this.checkoutDate, dueDate);
        return rentalChargeInfo;
    }

}