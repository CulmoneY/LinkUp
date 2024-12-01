package usecases.add_group_member;
import entity.Group;


public class AddGroupMemberInputData {
    private String groupname;
    private String username;

    public AddGroupMemberInputData(String groupname, String username) {
        this.groupname = groupname;
        this.username = username;
    }

    public String getGroupname() {
        return groupname;
    }

    public String getUsername() {
        return username;
    }
}
