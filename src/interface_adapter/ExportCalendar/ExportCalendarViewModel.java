package interface_adapter.ExportCalendar;

import interface_adapter.ViewModel;

public class ExportCalendarViewModel extends ViewModel<ExportCalendarState> {

    public ExportCalendarViewModel() {
        super("exportCalendarView");
        setState(new ExportCalendarState());
    }

}
