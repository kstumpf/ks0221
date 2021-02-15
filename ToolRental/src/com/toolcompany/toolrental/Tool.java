package com.toolcompany.toolrental;

/**
 * A tool class, storing the relevant information for the rented Tool.
 * Contains the four-letter Tool Code, the Tool Type, and the Tool Brand.
 */
public class Tool {
    private String toolCode;
    private String toolBrand;
    private ToolType toolType;

    /**
     * Constructor for initializing a Tool object.
     *
     * @param toolCode     The four-letter Tool Code for the given tool.
     * @param toolType     The type of tool (e.g. Jackhammer, Chainsaw, etc.)
     * @param toolBrand    The tool brand (e.g. Werner, Stihl, etc.)
     */
    public Tool(String toolCode, ToolType toolType, String toolBrand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
    }

    public String getToolCode() {
        return this.toolCode;
    }

    public void setToolCode(String code) {
        this.toolCode = code;
    }

    public ToolType getToolType() {
        return this.toolType;
    }

    public void setToolType(ToolType type) {
        this.toolType = type;
    }

    public String getToolBrand() {
        return this.toolBrand;
    }

    public void setToolBrand(String brand) {
        this.toolBrand = brand;
    }

}
