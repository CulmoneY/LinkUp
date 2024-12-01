package interface_adapter.AddGroupEvent;

import interface_adapter.ViewModel;

public class AddGroupEventViewModel extends ViewModel<AddGroupEventState> {
    public AddGroupEventViewModel() {
        super("addGroupEvent");
        setState(new AddGroupEventState());
    }
}
