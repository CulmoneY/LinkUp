package interface_adapter.DeleteGroupEvent;

import usecases.delete_group_event.DeleteGroupEventInputBoundary;
import usecases.delete_group_event.DeleteGroupEventInputData;

public class DeleteGroupEventController {
    private DeleteGroupEventInputBoundary inputBoundary;

    public DeleteGroupEventController(DeleteGroupEventInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void execute(String groupName, String eventName, String startTime, String endTime) {
        DeleteGroupEventInputData inputData = new DeleteGroupEventInputData(groupName, eventName, startTime, endTime);
        inputBoundary.execute(inputData);
    }
}
