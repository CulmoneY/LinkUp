package usecases.login;

public interface LoginOutputBoundary {

    void setPassView(LoginOutputData outputData);

    void setFailView(String error);

}
