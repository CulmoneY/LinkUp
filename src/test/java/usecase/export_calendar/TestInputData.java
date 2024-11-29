package usecase.export_calendar;

import entity.CommonCalendarFactory;
import entity.CommonUserFactory;
import entity.*;
import org.junit.jupiter.api.Test;
import usecases.export_calendar.ExportCalendarInputData;

import static org.junit.jupiter.api.Assertions.*;

public class TestInputData {

    @Test
    public void testInputData() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonUserFactory userFactory = new CommonUserFactory();


        User user = userFactory.create("Test User", "Test Password", "English");
        Calendar calendar = calendarFactory.create("Test Calendar");
        user.setUserCalendar(calendar);

        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        assertNotNull(inputData, "ExportCalendarInputData instance should not be null.");
        assertEquals(user, inputData.getUser(),
                "User in input data should match.");
    }
}
