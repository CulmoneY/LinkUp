package usecases.export_calendar;

import entity.Calendar;

import java.io.IOException;

// add to MongoDAO
public interface ExportCalendarDataAccessInterface {

    void saveData(Calendar calendar, String content) throws IOException;

}
