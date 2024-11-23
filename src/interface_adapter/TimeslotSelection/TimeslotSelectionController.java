package interface_adapter.TimeslotSelection;

import entity.Group;
import usecases.timeslot_selection.TimeslotSelectionInputBoundary;
import usecases.timeslot_selection.TimeslotSelectionInputData;


import java.time.LocalDateTime;

public class TimeslotSelectionController {
    private final TimeslotSelectionInputBoundary timeslotselectionInteractor;

    public TimeslotSelectionController(TimeslotSelectionInputBoundary timeslotselectionInteractor) {
        this.timeslotselectionInteractor = timeslotselectionInteractor;
    }

    public void execute(Group group, int duration, LocalDateTime time) {
        final TimeslotSelectionInputData timeslotSelectionInputDataInputData =
                new TimeslotSelectionInputData(group, duration, time);
        timeslotselectionInteractor.execute(timeslotSelectionInputDataInputData);
    }
}
