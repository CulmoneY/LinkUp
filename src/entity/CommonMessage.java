package entity;

public class CommonMessage implements Message {
    private String message;
    private String language;

    public CommonMessage(String message, String language) {
        this.message = message;
        this.language = language;
    }

    @Override
    public String getMessage(String translationLanguage) {
        return message;
    }

    @Override
    public void editMessage(String message) {
        this.message = message;
    }
}
