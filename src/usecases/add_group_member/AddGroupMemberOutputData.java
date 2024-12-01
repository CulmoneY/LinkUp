package usecases.add_group_member;

public class AddGroupMemberOutputData {
    private String groupname;
    private String username;

    public AddGroupMemberOutputData(String groupname, String username) {
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
