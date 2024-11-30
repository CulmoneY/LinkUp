package interface_adapter.DeleteGroupEvent;

import interface_adapter.ViewModel;

public class DeleteGroupEventViewModel extends ViewModel<DeleteGroupEventState> {
    public DeleteGroupEventViewModel(){
        super("deleteGroupEventView");
        setState(new DeleteGroupEventState());
    }
}
