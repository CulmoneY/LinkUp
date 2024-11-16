package usecases.message_translation;

public interface MessageTranslationDataAccessInterface {

    boolean messageAlreadyTranslated(String message, String language);

}
