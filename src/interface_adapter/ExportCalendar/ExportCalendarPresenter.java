package interface_adapter.ExportCalendar;

import interface_adapter.AccountCreation.AccountCreationViewModel;
import usecases.export_calendar.ExportCalendarOutputBoundary;
import interface_adapter.ViewManagerModel;
import usecases.export_calendar.ExportCalendarOutputData;

public class ExportCalendarPresenter implements ExportCalendarOutputBoundary {
    private final ExportCalendarViewModel exportCalendarViewModel;
    private final ViewManagerModel viewManagerModel;

    public ExportCalendarPresenter(ExportCalendarViewModel exportCalendarViewModel, ViewManagerModel viewManagerModel) {
        this.exportCalendarViewModel = exportCalendarViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void exportSuccess(ExportCalendarOutputData calendar) {

    }

    @Override
    public void exportFail(String error) {

    }
}
