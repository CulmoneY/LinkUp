package usecases.create_group;

public interface CreateGroupOutputBoundary {
    void setPassView(CreateGroupOutputData group);

    void setFailView(String error);
}
