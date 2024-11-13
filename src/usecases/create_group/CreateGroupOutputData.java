package usecases.create_group;

import java.util.List;

public class CreateGroupOutputData {
    private String groupName;
    private List<String> groupMembers;
    private boolean success;

    public CreateGroupOutputData(String groupName, List<String> groupMembers, boolean success) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
        this.success = success;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<String> getGroupMembers() {
        return groupMembers;
    }

    public boolean getSuccess() {
        return success;
    }

}
