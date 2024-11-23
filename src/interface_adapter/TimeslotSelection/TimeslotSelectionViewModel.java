package interface_adapter.TimeslotSelection;

import interface_adapter.ViewModel;

public class TimeslotSelectionViewModel extends ViewModel<TimeslotSelectionState> {
        public TimeslotSelectionViewModel() {
            super("timeslotSelectionView");
            setState(new TimeslotSelectionState());
        }
}
