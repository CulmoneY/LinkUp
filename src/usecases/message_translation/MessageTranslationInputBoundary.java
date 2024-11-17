package usecases.message_translation;

import com.deepl.api.DeepLException;

public interface MessageTranslationInputBoundary {
    void execute(MessageTranslationInputData MessageTranslationInputData) throws DeepLException, InterruptedException;
}
