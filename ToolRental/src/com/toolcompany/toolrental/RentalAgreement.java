package com.toolcompany.toolrental;

import com.toolcompany.toolrental.util.StringFormatting;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A RentalAgreement class containing the Rental Agreement data for the
 * rental of a tool from Toolcompany.
 */
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDayCount;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;
    private String rentalDateFormat = "MM/dd/yy";

    /**
     * Constructor for initializing a RentalAgreement object.
     *
     * @param tool                A Tool object containing all information about the tool.
     * @param rentalChargeInfo    A RentalCharge object containing all charge information.
     */
    public RentalAgreement(Tool tool, RentalCharges rentalChargeInfo) {
        this.toolCode = tool.getToolCode();
        this.toolType = tool.getToolType().getToolTypeStr();
        this.toolBrand = tool.getToolBrand();

        this.rentalDayCount = rentalChargeInfo.getRentalDayCount();
        this.checkoutDate = rentalChargeInfo.getCheckoutDate();
        this.dueDate = rentalChargeInfo.getDueDate();

        this.dailyRentalCharge = rentalChargeInfo.getDailyCharge();
        this.discountPercent = rentalChargeInfo.getDiscountPercent();
        this.chargeDays = rentalChargeInfo.getChargeDays();
        this.preDiscountCharge = rentalChargeInfo.getPreDiscountCharge();
        this.discountAmount = rentalChargeInfo.getDiscountAmount();
        this.finalCharge = rentalChargeInfo.getFinalCharge();
    }

    /**
     * Prints out a formatted version of the Rental Agreement for the User,
     * containing all relevant information.
     *
     * Dates must be formatted as              - mm/dd/yy
     * Currency should be formatted in USD, as - $8,8888.88
     * Percentages should be formatted as      - 82%
     *
     */
    public void printRentalAgreement() {
        String rentalAgreementStr = "Tool code: " + this.toolCode +
                "\nTool type: " + this.toolType +
                "\nTool brand: " + this.toolBrand +
                "\nRental days: " + this.rentalDayCount +
                "\nCheck out date: " + StringFormatting.formatDateToString(this.checkoutDate, rentalDateFormat) +
                "\nDue date: " + StringFormatting.formatDateToString(this.dueDate, rentalDateFormat) +
                "\nDaily rental charge: " + StringFormatting.formatCurrencyToString(this.dailyRentalCharge) +
                "\nCharge days: " + this.chargeDays +
                "\nPre-discount charge: " + StringFormatting.formatCurrencyToString(this.preDiscountCharge) +
                "\nDiscount percent: " + StringFormatting.formatPercentage(this.discountPercent) +
                "\nDiscount amount: " + StringFormatting.formatCurrencyToString(this.discountAmount) +
                "\nFinal charge: " + StringFormatting.formatCurrencyToString(this.finalCharge);

        System.out.println(rentalAgreementStr);
    }

    public String getToolCode() {
        return this.toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getToolType() {
        return this.toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return this.toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public BigDecimal getDailyCharge() {
        return this.dailyRentalCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyRentalCharge = dailyCharge;
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

    public String getRentalDateFormat() {
        return this.rentalDateFormat;
    }

    public void setRentalDateFormat(String dateformat) {
        this.rentalDateFormat = dateformat;
    }

}
