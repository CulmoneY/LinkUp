package interface_adapter.AccountCreation;

import interface_adapter.ViewModel;

public class AccountCreationViewModel extends ViewModel<AccountCreationState> {

    public static final String TITLE_LABEL = "Create Account";
    public static final String USERNAME_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";
    public static final String REPEAT_PASSWORD_LABEL = "Repeat Password";
    public static final String LANGUAGE_LABEL = "Language";

    public static final String CREATE_ACCOUNT_BUTTON_LABEL = "Create Account";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public static final String LOGIN_BUTTON_LABEL = "Switch to Login";
    
    public AccountCreationViewModel() {
        super("accountCreationView"); // The view name
        setState(new AccountCreationState());
    }
}
