package usecases.export_calendar;

import usecases.account_creation.AccountCreationOutputData;

public interface ExportCalendarOutputBoundary {

    void exportSuccess(ExportCalendarOutputData calendar);

    void exportFail(String error);
}
