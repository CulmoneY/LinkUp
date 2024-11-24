package usecases.create_group;

import entity.User;

import java.time.LocalDate;
import java.util.List;

public class CreateGroupInputData {
    private String groupName;
    private List<String> groupMembers;
    private User user;

    public CreateGroupInputData(String groupName, List<String> groupMembers, User current_user) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
        this.user = current_user;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public User getCurrent_user() {
        return user;
    }
}

