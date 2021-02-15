package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.toolcompany.toolrental.ToolcompanyStock;
import com.toolcompany.toolrental.Tool;
import com.toolcompany.toolrental.ToolType;

import java.math.BigDecimal;

public class ToolcompanyStockTests {
    @Test
    public void isValid_ToolCode_Yes() {
        ToolcompanyStock availableTools = new ToolcompanyStock();
        String toolCode = "CHNS";
        boolean isValidToolCode = availableTools.isValid(toolCode);
        assertEquals(true, isValidToolCode);
    }

    @Test
    public void isValid_ToolCode_NO() {
        ToolcompanyStock availableTools = new ToolcompanyStock();
        String toolCode = "CHNW";
        boolean isValidToolCode = availableTools.isValid(toolCode);
        assertEquals(false, isValidToolCode);
    }

    @Test
    public void getToolFromCode_LADW() {
        ToolcompanyStock availableTools = new ToolcompanyStock();
        String toolCode = "LADW";
        Tool tool = availableTools.getToolFromCode(toolCode);
        Tool expectedTool = new Tool("LADW", new ToolType("Ladder", new BigDecimal(1.99), true, true, false), "Werner");

        assertEquals(expectedTool.getToolCode(), tool.getToolCode());
        assertEquals(expectedTool.getToolType().getToolTypeStr(), tool.getToolType().getToolTypeStr());
        assertEquals(expectedTool.getToolBrand(), tool.getToolBrand());
        assertEquals(expectedTool.getToolType().getDailyCharge(), tool.getToolType().getDailyCharge());
        assertEquals(expectedTool.getToolType().getHolidayCharge(), tool.getToolType().getHolidayCharge());
        assertEquals(expectedTool.getToolType().getWeekdayCharge(), tool.getToolType().getWeekdayCharge());
        assertEquals(expectedTool.getToolType().getWeekendCharge(), tool.getToolType().getWeekendCharge());
    }

    @Test
    public void isValid_ToolCode_CHNS() {
        ToolcompanyStock availableTools = new ToolcompanyStock();
        String toolCode = "CHNS";
        Tool tool = availableTools.getToolFromCode(toolCode);
        Tool expectedTool = new Tool("CHNS", new ToolType("Chainsaw", new BigDecimal(1.49), true, false, true), "Stihl");

        assertEquals(expectedTool.getToolCode(), tool.getToolCode());
        assertEquals(expectedTool.getToolType().getToolTypeStr(), tool.getToolType().getToolTypeStr());
        assertEquals(expectedTool.getToolBrand(), tool.getToolBrand());
        assertEquals(expectedTool.getToolType().getDailyCharge(), tool.getToolType().getDailyCharge());
        assertEquals(expectedTool.getToolType().getHolidayCharge(), tool.getToolType().getHolidayCharge());
        assertEquals(expectedTool.getToolType().getWeekdayCharge(), tool.getToolType().getWeekdayCharge());
        assertEquals(expectedTool.getToolType().getWeekendCharge(), tool.getToolType().getWeekendCharge());
    }

    @Test
    public void getToolFromCode_JAKD() {
        ToolcompanyStock availableTools = new ToolcompanyStock();
        String toolCode = "JAKD";
        Tool tool = availableTools.getToolFromCode(toolCode);
        Tool expectedTool = new Tool("JAKD", new ToolType("Jackhammer", new BigDecimal(2.99), true, false, false), "DeWalt");

        assertEquals(expectedTool.getToolCode(), tool.getToolCode());
        assertEquals(expectedTool.getToolType().getToolTypeStr(), tool.getToolType().getToolTypeStr());
        assertEquals(expectedTool.getToolBrand(), tool.getToolBrand());
        assertEquals(expectedTool.getToolType().getDailyCharge(), tool.getToolType().getDailyCharge());
        assertEquals(expectedTool.getToolType().getHolidayCharge(), tool.getToolType().getHolidayCharge());
        assertEquals(expectedTool.getToolType().getWeekdayCharge(), tool.getToolType().getWeekdayCharge());
        assertEquals(expectedTool.getToolType().getWeekendCharge(), tool.getToolType().getWeekendCharge());
    }

    @Test
    public void isValid_ToolCode_JAKR() {
        ToolcompanyStock availableTools = new ToolcompanyStock();
        String toolCode = "JAKR";
        Tool tool = availableTools.getToolFromCode(toolCode);
        Tool expectedTool = new Tool("JAKR", new ToolType("Jackhammer", new BigDecimal(2.99), true, false, false), "Ridgid");

        assertEquals(expectedTool.getToolCode(), tool.getToolCode());
        assertEquals(expectedTool.getToolType().getToolTypeStr(), tool.getToolType().getToolTypeStr());
        assertEquals(expectedTool.getToolBrand(), tool.getToolBrand());
        assertEquals(expectedTool.getToolType().getDailyCharge(), tool.getToolType().getDailyCharge());
        assertEquals(expectedTool.getToolType().getHolidayCharge(), tool.getToolType().getHolidayCharge());
        assertEquals(expectedTool.getToolType().getWeekdayCharge(), tool.getToolType().getWeekdayCharge());
        assertEquals(expectedTool.getToolType().getWeekendCharge(), tool.getToolType().getWeekendCharge());
    }
}
