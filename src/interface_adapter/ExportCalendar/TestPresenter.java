package interface_adapter.ExportCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecases.export_calendar.ExportCalendarOutputData;

import static org.junit.jupiter.api.Assertions.*;

class TestPresenter {

    private ExportCalendarViewModel viewModel;
    private ExportCalendarPresenter presenter;

    @BeforeEach
    void setUp() {
        viewModel = new ExportCalendarViewModel();
        presenter = new ExportCalendarPresenter(viewModel);
    }

    // encountering a null issue
    @Test
    void testExportSuccess() {
        String filePath = "/Downloads/test.ics";
        ExportCalendarOutputData outputData = new
                ExportCalendarOutputData(true, filePath, "Export successful.");

        presenter.exportSuccess(outputData);

        assertNotNull(viewModel.getState(), "The view model state should not be null.");
        assertNotNull(viewModel.getState().getFilePath(), "File path should not be null.");
        assertEquals(filePath, viewModel.getState().getFilePath(), "The file path should match the output.");
        assertEquals("Export successful.", viewModel.getState().getMessage(),
                "The message should indicate success.");
        assertTrue(viewModel.getState().isSuccess(), "The success flag should be true.");
    }

    @Test
    void testExportFailure() {
        String error = "Export failed.";

        presenter.exportFail(error);

        assertNull(viewModel.getState().getFilePath(), "The file path should be null on failure.");
        assertEquals(error, viewModel.getState().getMessage(), "The error message should match.");
        assertFalse(viewModel.getState().isSuccess(), "The success flag should be false.");
    }
}