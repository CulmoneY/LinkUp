package interface_adapter.DeletePersonalEvent;

import interface_adapter.ViewModel;

public class DeletePersonalEventViewModel extends ViewModel<DeletePersonalEventState> {
    public DeletePersonalEventViewModel(){
        super("deleteEventView");
        setState(new DeletePersonalEventState());
    }
}
