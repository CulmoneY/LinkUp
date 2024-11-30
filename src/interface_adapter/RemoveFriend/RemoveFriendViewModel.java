package interface_adapter.RemoveFriend;

import interface_adapter.ViewModel;

public class RemoveFriendViewModel extends ViewModel<RemoveFriendState> {

    public RemoveFriendViewModel(){
        super("removeFriendView");
        setState(new RemoveFriendState());
    }
}