package usecases.export_calendar;

import java.io.IOException;

public interface ExportCalendarInputBoundary {

    void execute(ExportCalendarInputData inputData) throws IOException;

}
