package usecases.remove_group_member;

import entity.Event;
import entity.User;

import java.time.format.DateTimeFormatter;

public class RemoveGroupMemberInteractor implements RemoveGroupMemberInputBoundary{
    private RemoveGroupMemberOutputBoundary outputPresenter;
    private RemoveGroupMemberDataAccessInterface dataAccess;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public RemoveGroupMemberInteractor(RemoveGroupMemberOutputBoundary outputPresenter, RemoveGroupMemberDataAccessInterface dataAccess) {
        this.outputPresenter = outputPresenter;

        this.dataAccess = dataAccess;
    }


    @Override
    public void executeRemoveGroupMember(RemoveGroupMemberInputData inputData) {
        String groupname = inputData.getGroupname();

        String username = inputData.getUsername();

        dataAccess.removeGroupMember(groupname, username);
        User user = dataAccess.getUser(username);
        for (Event event : user.getUserCalendar().getEvents()) {
            dataAccess.removeGroupEvent(groupname, event.getEventName(), event.getStartTime().format(formatter),
                    event.getEndTime().format(formatter));
        }
        outputPresenter.setPassViewData(new RemoveGroupMemberOutputData(groupname, username));

    }
}
