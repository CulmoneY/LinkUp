package usecases.remove_group_member;

import entity.User;

public interface RemoveGroupMemberDataAccessInterface {
    void removeGroupMember(String groupname, String username);

    public void removeGroupEvent(String groupname, String eventname, String startTime, String endTime);

    public User getUser(String username);
}
