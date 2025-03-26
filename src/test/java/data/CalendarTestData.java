package data;

import org.testng.annotations.DataProvider;


public class CalendarTestData {

    @DataProvider(name = "calendarData")
    public Object[][] calendarData() {
        return new Object[][] {
                {"03:00"},
                {"05:00"},
                {"10:00"}
        };
    }
}
