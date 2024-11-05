package entity;

import java.util.ArrayList;

public class CommonCalendarFactory implements CalendarFactory {

    @Override
    public Calendar create(String name) {
        return new CommonCalendar(name, new ArrayList<Event>());
    }
}
