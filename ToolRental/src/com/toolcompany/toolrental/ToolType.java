package com.toolcompany.toolrental;

import java.math.BigDecimal;

/**
 * A class that stores information about a Tool that is specific to the Tool Type.
 * Stores the Tool Type name, the daily charge for the tool type,
 * and whether the tool type has a weekeday charge, weekend charge, or holiday charge.
 *
 */
public class ToolType {
    private String toolType;
    private BigDecimal dailyCharge;
    private boolean weekdayCharge;
    private boolean weekendCharge;
    private boolean holidayCharge;

    /**
     * Constructor for initializing a ToolType object.
     *
     * @param toolType         The Tool Type name, as a String.
     * @param dailyCharge      A BigDecimal containing the daily charge for the given Tool Type.
     * @param weekdayCharge    A boolean that states whether the given Tool Type is chargeable on weekdays.
     * @param weekendCharge    A boolean that states whether the given Tool Type is chargeable on weekends.
     * @param holidayCharge    A boolean that states whether the given Tool Type is chargeable on holidays.
     *
     */
    public ToolType(String toolType, BigDecimal dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolType = toolType;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    public String getToolTypeStr() {
        return this.toolType;
    }

    public void setToolTypeStr(String type) {
        this.toolType = toolType;
    }

    public BigDecimal getDailyCharge() {
        return this.dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public boolean getWeekdayCharge() {
        return this.weekdayCharge;
    }

    public void setWeekdayCharge(boolean weekdayCharge) {
        this.weekdayCharge = weekdayCharge;
    }

    public boolean getWeekendCharge() {
        return this.weekendCharge;
    }

    public void setWeekendCharge(boolean weekendCharge) {
        this.weekendCharge = weekendCharge;
    }

    public boolean getHolidayCharge() {
        return this.holidayCharge;
    }

    public void setHolidayCharge(boolean holidayCharge) {
        this.holidayCharge = holidayCharge;
    }

}
