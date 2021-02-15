package com.toolcompany.toolrental;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A helper class that stores the relevant information about Rental Charges.
 * Used as an easy way to pass the information to a rentalAgreement object.
 */
public class RentalCharges {
    private BigDecimal dailyCharge;
    private int discountPercent;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;
    private int rentalDayCount;
    private LocalDate checkoutDate;
    private LocalDate dueDate;

    /**
     * Constructor for initializing a RentalCharges object.
     *
     * @param dailyCharge          The daily charge for tool rental, dependent on Tool Type.
     * @param discountPercent      The discount percent, as provided by the cashier.
     * @param chargeDays           The number of chargeable days out of the days the tool is rented.
     *                                 Discounts days that are not charged for the given tool type,
     *                                 such as certain holidays or weekends.
     * @param preDiscountCharge    The charge amount before the discount has been applied.
     * @param discountAmount       The discount amount.
     * @param finalCharge          The final charge for the tool rental.
     * @param rentalDayCount       An int set during checkout, the number of days the tool will be rented.
     * @param checkoutDate         A LocalDate set during checkout, the date the tool was checked out.
     * @param dueDate              A LocalDate holding the due date for the tool to be returned.
     */
    public RentalCharges(BigDecimal dailyCharge, int discountPercent, int chargeDays,
                         BigDecimal preDiscountCharge, BigDecimal discountAmount, BigDecimal finalCharge,
                         int rentalDayCount, LocalDate checkoutDate, LocalDate dueDate) {
        this.dailyCharge = dailyCharge;
        this.discountPercent = discountPercent;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;

        this.rentalDayCount = rentalDayCount;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    public BigDecimal getDailyCharge() {
        return this.dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public int getDiscountPercent() {
        return this.discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getChargeDays() {
        return this.chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return this.preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return this.finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public int getRentalDayCount() {
        return this.rentalDayCount;
    }

    public void setRentalDayCount(int rentalDayCount) {
        this.rentalDayCount = rentalDayCount;
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

}
