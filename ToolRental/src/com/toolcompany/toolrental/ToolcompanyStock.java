package com.toolcompany.toolrental;

import java.util.HashMap;
import java.util.HashSet;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A class encapsulating the data for the tools available for rental from ToolCompany.
 *
 * The specs asked for a Java-based solution, and I wanted to provide something stable
 * to showcase the Rental Agreement functionality.
 *
 * However, it would be most flexible and robust to store Tool information in a database,
 * and to query for the specific Code Type we need, and its related information.
 *
 * TO-DO: Research best practices, using NetBeans or JDBC to set up DB.
 *
 */
public class ToolcompanyStock {
    private HashMap<String, String[]> toolCodeData;
    private HashMap<String, ToolType> toolTypeData;
    private int toolTypeIndex = 0;
    private int toolBrandIndex = 1;

    /**
     * Constructor for initializing a ToolsInStore object.
     */
    public ToolcompanyStock() {
        this.toolCodeData = new HashMap<String, String[]>();
        toolCodeData.put("LADW", new String[]{"Ladder", "Werner"});
        toolCodeData.put("CHNS", new String[]{"Chainsaw", "Stihl"});
        toolCodeData.put("JAKR", new String[]{"Jackhammer", "Ridgid"});
        toolCodeData.put("JAKD", new String[]{"Jackhammer", "DeWalt"});
        this.toolTypeIndex = toolTypeIndex;
        this.toolBrandIndex = toolBrandIndex;

        this.toolTypeData = new HashMap<String, ToolType>();
        toolTypeData.put("Ladder", new ToolType("Ladder", new BigDecimal(1.99), true, true, false));
        toolTypeData.put("Chainsaw", new ToolType("Chainsaw", new BigDecimal(1.49), true, false, true));
        toolTypeData.put("Jackhammer", new ToolType("Jackhammer", new BigDecimal(2.99), true, false, false));
    }

    /**
     * Creates a Tool object, given the Tool Code.
     *
     * @param toolCode    A String containing the Tool Code.
     * @return tool       A Tool object containing the relevant information for the tool.
     */
    public Tool getToolFromCode(String toolCode) {
        String toolBrand = this.toolCodeData.get(toolCode)[toolBrandIndex];
        String type = this.toolCodeData.get(toolCode)[toolTypeIndex];
        ToolType toolType = this.toolTypeData.get(type);

        Tool tool = new Tool(toolCode, toolType, toolBrand);

        return tool;
    }

    /**
     * Checks whether the entered Tool Code exists.
     *
     * @param toolCode    A String containing a potential Tool Code.
     * @return            A boolean - true if the Tool Code exists, false if the Tool Code doesn't exist.
     */
    public boolean isValid(String toolCode) {
        if (this.toolCodeData.containsKey(toolCode)) {
            return true;
        } else {
            return false;
        }
    }

}
