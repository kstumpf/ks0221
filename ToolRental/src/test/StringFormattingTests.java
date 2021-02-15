package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.toolcompany.toolrental.util.StringFormatting;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StringFormattingTests {
    @Test
    public void cleanString_Test() {
        /*
        An interesting thing I noticed with this test, is that trim() will clear whitespace from the beginning
        of the String, but will stop when it reaches the invisible control character.
        This could be something to consider, for a more robust cleanString function.
        I improved cleanString and rather than using trim(), now use removeAll(" ", "").
         */
        String yuckyString = "      LADW  \u200B\uFEFF        ";
        String cleanString = StringFormatting.cleanString(yuckyString);
        assertEquals("LADW", cleanString);
    }

    @Test
    public void parsePercentage_Test() {
        String formattedPercent = "50%";
        int percentInt = StringFormatting.parsePercentage(formattedPercent);
        assertEquals(50, percentInt);
    }

    @Test
    public void formatPercentage_Test() {
        int percentInt = 52;
        String formattedPercent = StringFormatting.formatPercentage(percentInt);
        assertEquals("52%", formattedPercent);
    }

    @Test
    public void formatCurrencyToString_Test() {
        // This helps me to test both formatCurrencyToString, and to test whether formatCurrency properly rounds half-up to cents.
        BigDecimal currency = new BigDecimal(53000000000.2588);
        String formattedCurrency = StringFormatting.formatCurrencyToString(StringFormatting.formatCurrency(currency));
        assertEquals("$53,000,000,000.26", formattedCurrency);
    }

    @Test
    public void formatDateToString_Test() {
        final LocalDate date = LocalDate.of(2018, 8, 28);
        final String formattedDateStr = StringFormatting.formatDateToString(date, "M/dd/yy");
        assertEquals("8/28/18", formattedDateStr);
    }
}
