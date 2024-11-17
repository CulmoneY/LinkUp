package usecases.message_translation;

import entity.Message;

public interface MessageTranslationOutputBoundary {
    void presentTranslatedMessage(Message translatedMessage);
    void presentTranslationError(String errorMessage);
}