package uz.pdp.backend.model.user;

import uz.pdp.backend.model.baseModel.BaseModel;

import java.sql.Time;
import java.time.LocalTime;

public class Users extends BaseModel {
    private String name;
    private String number;
    private String nickname;
    private String password;
    private LocalTime lastActivity;

    public Users(String name, String number, String nickname, String password, LocalTime lastActivity) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public LocalTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    @Override
    public String toString() {
        return nickname;
    }
}
