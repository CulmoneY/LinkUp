package interface_adapter.MessageTranslation;

import entity.Message;
import usecases.message_translation.MessageTranslationOutputBoundary;

public class MessageTranslationPresenter implements MessageTranslationOutputBoundary {
    public MessageTranslationPresenter() {
        // TODO: Implement this constructor
    }

    @Override
    public void presentTranslatedMessage(Message translatedMessage) {
        // TODO: Implement this method
    }

    @Override
    public void presentTranslationError(String errorMessage) {
        // TODO: Implement this method
    }

}
