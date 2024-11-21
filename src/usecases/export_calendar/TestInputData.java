package usecases.export_calendar;

import entity.CommonCalendarFactory;
import entity.CommonUserFactory;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestInputData {

    @Test
    public void testInputDataConstruction() {
        CommonCalendarFactory calendarFactory = new CommonCalendarFactory();
        CommonUserFactory userFactory = new CommonUserFactory();


        Calendar calendar = calendarFactory.create("Test Calendar");
        User user = userFactory.create("Test User", "Test Password", "English");
        user.setUserCalendar(calendar);

        ExportCalendarInputData inputData = new ExportCalendarInputData(user);

        assertNotNull(inputData, "ExportCalendarInputData instance should not be null.");
        assertEquals(user, inputData.getUser(), "User in input data should match the one provided during construction.");
    }
}
