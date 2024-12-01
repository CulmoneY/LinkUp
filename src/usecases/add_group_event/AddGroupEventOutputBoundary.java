package usecases.add_group_event;


public interface AddGroupEventOutputBoundary {

    void setPassView(AddGroupOutputData outputData);

    void setFailView(String error);
}
