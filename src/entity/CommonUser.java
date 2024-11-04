package entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CommonUser implements User{

    private String name;
    private String password;
    private List<User> friends;
    private Calendar userCalendar;
    private String language;

    public CommonUser (String name, String password, String language) {
        this.name = name;
        this.password = password;
        this.language = language;
        this.friends = new ArrayList<User>();
        this.userCalendar = null;
        //TODO : CHANGE THIS SO IT USES THE CALENDAR INITIALIZER
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public List<User> getFriends() {
        return friends;
    }

    @Override
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public Calendar getUserCalendar() {
        return userCalendar;
    }

    @Override
    public void setUserCalendar(Calendar userCalendar) {
        this.userCalendar = userCalendar;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }
}
