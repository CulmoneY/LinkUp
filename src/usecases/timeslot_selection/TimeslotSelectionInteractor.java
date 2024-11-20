package usecases.timeslot_selection;

import java.time.LocalDateTime;
import database.MongoDBConnection;
// import entities.Timeslot;

public class TimeslotSelectionInteractor implements TimeslotSelectionInputBoundary {
    private TimeslotSelectionAccessInterface dao;
    private TimeslotSelectionOutputBoundary presenter;

    public void selectTimeslot(TimeslotSelectionInputData inputData) {
        // Business logic to select a timeslot
    }

    public void cancelTimeslot(TimeslotSelectionInputData inputData) {
        // Business logic to cancel a timeslot
    }
}
