package interface_adapter.AddPersonaEvent;
import usecases.add_personal_event.AddPersonalEventInputBoundary;
import usecases.add_personal_event.AddPersonalEventInputData;
import entity.User;

public class AddPersonalEventController {
    private AddPersonalEventInputBoundary inputBoundary;

    public AddPersonalEventController(AddPersonalEventInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void executeCreate(String eventName, String startTime, String endTime, User user) {
        AddPersonalEventInputData inputData = new AddPersonalEventInputData(eventName, startTime, endTime, user);
        inputBoundary.executeCreate(inputData);

    }


}
