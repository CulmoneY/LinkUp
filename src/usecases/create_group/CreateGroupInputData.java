package usecases.create_group;

import entity.User;

import java.time.LocalDate;
import java.util.List;

public class CreateGroupInputData {
    private String groupName;
    private List<String> groupMembers;

    public CreateGroupInputData(String groupName, List<String> groupMembers) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }
}
