package interface_adapter.DeletePersonalEvent;

import entity.User;
import usecases.delete_personal_event.DeletePersonalEventInputBoundary;
import usecases.delete_personal_event.DeletePersonalEventInputData;

public class DeletePersonalEventController {
    private final DeletePersonalEventInputBoundary inputBoundary;

    public DeletePersonalEventController(DeletePersonalEventInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public void executeDelete(User user, String eventName, String startTime, String endTime) {
        DeletePersonalEventInputData inputData = new DeletePersonalEventInputData(user, eventName, startTime, endTime);
        inputBoundary.executeDelete(inputData);
    }
}
