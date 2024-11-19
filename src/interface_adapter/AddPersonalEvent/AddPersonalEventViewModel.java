package interface_adapter.AddPersonalEvent;

import interface_adapter.ViewModel;

public class AddPersonalEventViewModel extends ViewModel<AddPersonalEventState> {

    public AddPersonalEventViewModel() {
        super("addPersonalEventView");
        setState(new AddPersonalEventState());
    }
}
