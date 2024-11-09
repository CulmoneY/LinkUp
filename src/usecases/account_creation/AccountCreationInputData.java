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

    public String getUsername() {return this.username;}

    public String getPassword() {return this.password;}

    public String getRepeatedPassword() {return this.repeatedPassword;}

    public String getLanguage() {return this.language;}

}
