package interface_adapter.AddGroupMember;

public class AddGroupMemberState {
    String groupname;
    String username;

    public void setGroupName(String groupname) {
        this.groupname = groupname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getGroupname() {
        return groupname;
    }
}
