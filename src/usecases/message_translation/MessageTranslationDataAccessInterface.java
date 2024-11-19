package usecases.message_translation;

import com.deepl.api.DeepLException;

public interface MessageTranslationDataAccessInterface {

    String getTranslatedMessage(String message, String targetLanguage, String groupName);

    void saveTranslation(String message, String targetLanguage, String translatedMessage, String groupName);

    String translateMessage(String message, String targetLanguage) throws DeepLException, InterruptedException;
}
