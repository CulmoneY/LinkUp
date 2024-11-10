package entity;

import java.util.List;

public interface GroupFactory {

    CommonGroup create(String name, List<User> users);
}
