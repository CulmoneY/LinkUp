package usecases.create_group;

import entity.User;

import java.time.LocalDate;
import java.util.List;

public class CreateGroupInputData {
    private String groupName;
    private List<String> groupMembers;
    private String current_user;

    public CreateGroupInputData(String groupName, List<String> groupMembers, String current_user) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
        this.current_user = current_user;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public String getCurrent_user() {
        return current_user;
    }
}

