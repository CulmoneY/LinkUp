package usecases.message_translation;

import com.deepl.api.DeepLException;

public interface MessageTranslationDataAccessInterface {

    boolean messageAlreadyTranslated(String message, String language);

    String getTranslatedMessage(String message, String targetLanguage);

    void saveTranslation(String message, String targetLanguage, String translatedMessage);

    String translateMessage(String message, String targetLanguage) throws DeepLException, InterruptedException;
}
