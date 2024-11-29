package interface_adapter.TimeslotSelection;

import usecases.account_creation.AccountCreationOutputData;
import usecases.timeslot_selection.TimeslotSelectionOutputBoundary;
import usecases.timeslot_selection.TimeslotSelectionOutputData;

public class TimeslotSelectionPreseter implements TimeslotSelectionOutputBoundary {
    private final TimeslotSelectionViewModel timeslotSelectionViewModel;

    //    private final LoginViewModel loginViewModel;
    public TimeslotSelectionPreseter(TimeslotSelectionViewModel timeslotSelectionViewModel) {
        this.timeslotSelectionViewModel = timeslotSelectionViewModel;

    }

    @Override
    public void setPassView(TimeslotSelectionOutputData response) {
        TimeslotSelectionState timeslotSelectionState = timeslotSelectionViewModel.getState();
        timeslotSelectionState.setEvent(response.getEvent());
        timeslotSelectionViewModel.firePropertyChanged("timeslotsuccess");
    }
}
