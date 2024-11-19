package interface_adapter.MessageTranslation;

import interface_adapter.ViewManagerModel;
import usecases.message_translation.MessageTranslationOutputBoundary;
import usecases.message_translation.MessageTranslationOutputData;
import entity.Message;

public class MessageTranslationPresenter implements MessageTranslationOutputBoundary {
    private final MessageTranslationViewModel messageTranslationViewModel;
    private final ViewManagerModel viewManagerModel;

    public MessageTranslationPresenter(MessageTranslationViewModel messageTranslationViewModel, ViewManagerModel viewManagerModel) {
        this.messageTranslationViewModel = messageTranslationViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentTranslatedMessage(MessageTranslationOutputData outputData) {
        MessageTranslationState messageTranslationState = messageTranslationViewModel.getState();
        messageTranslationState.setMessage(outputData.getMessage());
        messageTranslationViewModel.firePropertyChanged("translationSuccess");
    }

    @Override
    public void presentTranslationError(String errorMessage) {
        // TODO: Implement this method
    }

}
