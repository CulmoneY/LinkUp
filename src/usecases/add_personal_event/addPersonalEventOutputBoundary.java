package usecases.add_personal_event;

public interface addPersonalEventOutputBoundary {

    void setPassView(addPersonalEventOutputData outputData);

    void setFailView(String error);
}
