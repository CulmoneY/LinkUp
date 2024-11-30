package usecases.add_group_event;

public interface AddGroupEventOutputBoundary {

    void setPassView(AddGroupEventOutputData outputData);

    void setFailView(String error);
}
