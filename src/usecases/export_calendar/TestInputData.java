package usecases.export_calendar;

import entity.CommonUserFactory;
import entity.CommonCalendarFactory;
import entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInputData {

    @Test
    public void testInputData() {
        CommonUserFactory userFactory = new CommonUserFactory();
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();

        User user = userFactory.create("Test User", "Test Password", "English");
        Calendar calendar = calendarFactory.create("Test Calendar");
        user.setUserCalendar(calendar);

        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        assertNotNull(inputData, "ExportCalendarInputData instance should not be null.");
        assertEquals(user, inputData.getUser(),
                "User in input data should match.");
    }
}
