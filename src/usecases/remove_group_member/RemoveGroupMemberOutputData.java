package usecases.remove_group_member;

public class RemoveGroupMemberOutputData {
    private String groupname;
    private String username;

    public RemoveGroupMemberOutputData(String groupname, String username) {
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
