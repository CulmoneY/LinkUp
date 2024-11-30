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
        System.out.println("TimeslotSelectionPresenter: setPassView");
        TimeslotSelectionState timeslotSelectionState = timeslotSelectionViewModel.getState();
        System.out.println(response.getEvent());
        timeslotSelectionState.setEvent(response.getEvent());
        timeslotSelectionViewModel.firePropertyChanged("timeslotSuccess");
    }
}
