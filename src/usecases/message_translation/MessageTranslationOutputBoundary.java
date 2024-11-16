package usecases.message_translation;

public interface MessageTranslationOutputBoundary {
    void presentTranslatedMessage(String translatedMessage);
    void presentTranslationError(String errorMessage);
}