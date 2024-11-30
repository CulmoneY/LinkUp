package interface_adapter.DeleteGroupEvent;

import entity.Group;
import usecases.delete_group_event.DeleteGroupEventInputBoundary;
import usecases.delete_group_event.DeleteGroupEventInputData;

public class DeleteGroupEventController {
    private final DeleteGroupEventInputBoundary inputBoundary;

    public DeleteGroupEventController(DeleteGroupEventInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    public void executeDelete(Group group, String eventName, String startTime, String endTime) {
        DeleteGroupEventInputData inputData = new DeleteGroupEventInputData(group, eventName, startTime, endTime);
        inputBoundary.executeDelete(inputData);
    }
}
