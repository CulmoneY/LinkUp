package usecases.account_creation;

public class AccountCreationInputData {
    private String username;
    private String password;
    private String repeatedPassword;
    private String language;

    public AccountCreationInputData(String username, String password, String repeatedPassword, String language){
        this.username = username;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.language = language;
    }
}
