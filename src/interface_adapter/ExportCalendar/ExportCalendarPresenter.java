package interface_adapter.ExportCalendar;

import usecases.export_calendar.ExportCalendarOutputBoundary;
import usecases.export_calendar.ExportCalendarOutputData;

// add to AppBuilder
public class ExportCalendarPresenter implements ExportCalendarOutputBoundary {
    private final ExportCalendarViewModel exportCalendarViewModel;

    public ExportCalendarPresenter(ExportCalendarViewModel exportCalendarViewModel) {
        this.exportCalendarViewModel = exportCalendarViewModel;
    }

    @Override
    public void exportSuccess(ExportCalendarOutputData calendar) {
        ExportCalendarState exportCalendarState = exportCalendarViewModel.getState();
        exportCalendarState.setSuccess(exportCalendarState.isSuccess());
        exportCalendarState.setMessage(exportCalendarState.getMessage());
        exportCalendarState.setFilePath(exportCalendarState.getFilePath());
        exportCalendarViewModel.firePropertyChanged("exportCalendarSuccess");
    }

    @Override
    public void exportFail(String error) {
        ExportCalendarState exportCalendarState = exportCalendarViewModel.getState();
        exportCalendarState.setMessage(error);
        exportCalendarViewModel.firePropertyChanged("exportCalendarFail");
    }
}
