package usecases.add_personal_event;

public interface AddPersonalEventOutputBoundary {

    void setPassView(AddPersonalEventOutputData outputData);

    void setFailView(String error);
}
