package interface_adapter.TimeslotSelection;

import entity.Group;
import entity.User;
import usecases.timeslot_selection.TimeslotSelectionInputBoundary;
import usecases.timeslot_selection.TimeslotSelectionInputData;


import java.time.LocalDateTime;

public class TimeslotSelectionController {
    private final TimeslotSelectionInputBoundary timeslotselectionInteractor;

    public TimeslotSelectionController(TimeslotSelectionInputBoundary timeslotselectionInteractor) {
        this.timeslotselectionInteractor = timeslotselectionInteractor;
    }

    public void execute(String group, User user) {
        final TimeslotSelectionInputData timeslotSelectionInputDataInputData =
                new TimeslotSelectionInputData(group, user);
        timeslotselectionInteractor.execute(timeslotSelectionInputDataInputData);
    }
}
