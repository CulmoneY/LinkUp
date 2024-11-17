package usecases.message_translation;

import entity.Message;

public interface MessageTranslationOutputBoundary {
    void presentTranslatedMessage(MessageTranslationOutputData translatedMessage);
    void presentTranslationError(String errorMessage);
}