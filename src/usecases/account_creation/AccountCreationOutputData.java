package usecases.account_creation;

public class AccountCreationOutputData {
    private String username;
    private String language;
    private boolean success;

    public AccountCreationOutputData(String username, String language, boolean success) {
        this.username = username;
        this.language = language;
    }

    public String getUsername() {return this.username;}
    public String getLanguage() {return this.language;}
    public boolean getSuccess() {return this.success;}
}
