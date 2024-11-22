package usecases.export_calendar;

import entity.Calendar;

// add to MongoDAO
public interface ExportCalendarDataAccessInterface {

    void saveCalendar(Calendar calendar);

}
