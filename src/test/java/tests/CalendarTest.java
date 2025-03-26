package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.CalendarTestData;

import io.qameta.allure.*;

import util.DriverSetup;

@Epic("Calendar App test automation")
@Feature("Events")
public class CalendarTest extends DriverSetup {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Create new calendar event")
    @Story("Successful creation of a new calendar event")
    @Test(testName = "Calendar test", dataProviderClass = CalendarTestData.class, dataProvider = "calendarData")
    public void calendarTest(String timeslot) {

        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        calendarHomePage.createEventOnTimeslot(timeslot);
        Assert.assertTrue(newEventPage.newEventPageLoaded(), "New event page is not loaded");

        newEventPage.chooseStartHour("20", "05");
    }
}
