package uz.pdp.backend.model.user;

import uz.pdp.backend.model.baseModel.BaseModel;

import java.sql.Time;

public class Users extends BaseModel {
    private String name;
    private Integer number;
    private String nickname;
    private String password;
    private Time lastActivity;

    public Users(String name, Integer number, String nickname, String password, Time lastActivity) {
        this.name = name;
        this.number = number;
        this.nickname = nickname;
        this.password = password;
        this.lastActivity = lastActivity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Time getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Time lastActivity) {
        this.lastActivity = lastActivity;
    }
}
