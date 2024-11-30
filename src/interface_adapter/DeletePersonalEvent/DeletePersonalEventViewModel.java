package interface_adapter.DeletePersonalEvent;

import interface_adapter.ViewModel;

public class DeletePersonalEventViewModel extends ViewModel<DeletePersonalEventState> {
    public DeletePersonalEventViewModel(){
        super("deletePersonalEventView");
        setState(new DeletePersonalEventState());
    }
}
