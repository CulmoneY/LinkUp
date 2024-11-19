package interface_adapter.AddFriend;
import interface_adapter.AccountCreation.AccountCreationViewModel;
import interface_adapter.ViewModel;

public class AddFriendViewModel extends ViewModel<AddFriendState> {
    public AddFriendViewModel() {
        super("addFriendView");
        setState(new AddFriendState());
    }
}
