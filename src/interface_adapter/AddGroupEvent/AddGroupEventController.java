package interface_adapter.AddGroupEvent;

import interface_adapter.AddGroupMember.AddGroupMemberController;
import usecases.add_group_event.AddGroupEventInputBoundary;
import usecases.add_group_event.AddGroupEventInputData;
import usecases.add_group_event.AddGroupEventInteractor;
import usecases.add_group_event.AddGroupEventOutputBoundary;

public class AddGroupEventController {
    private final AddGroupEventInputBoundary addGroupEventInteractor;

    public AddGroupEventController(AddGroupEventInputBoundary addGroupEventInteractor) {
        this.addGroupEventInteractor = addGroupEventInteractor;
    }

    public void execute(String groupname, String eventname, String startTime, String endTime) {
        addGroupEventInteractor.executeCreate(new AddGroupEventInputData(groupname, eventname, startTime, endTime));
    }

}
