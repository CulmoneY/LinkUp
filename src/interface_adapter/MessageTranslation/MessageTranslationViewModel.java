package interface_adapter.MessageTranslation;

import interface_adapter.Login.LoginState;
import interface_adapter.ViewModel;

public class MessageTranslationViewModel extends ViewModel<MessageTranslationState> {

    public MessageTranslationViewModel() {
        super("messageTranslationView");
        setState(new MessageTranslationState());
    }
}
