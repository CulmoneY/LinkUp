package interface_adapter.AddGroupEvent;

import usecases.add_group_event.AddGroupEventInputBoundary;
import usecases.add_group_event.AddGroupEventInputData;

public class AddGroupEventController {
    private AddGroupEventInputBoundary inputBoundary;

    public AddGroupEventController(AddGroupEventInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void executeCreate(String eventName, String startTime, String endTime, String groupName) {
        AddGroupEventInputData inputData = new AddGroupEventInputData(eventName, startTime, endTime, groupName);
        inputBoundary.executeCreate(inputData);

    }


}
