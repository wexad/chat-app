package uz.pdp.backend.model.post;

import uz.pdp.backend.model.baseModel.BaseModel;

import java.sql.Time;
import java.time.LocalTime;

public class Posts extends BaseModel implements Comparable {
    private String text;
    private final String userId;
    private final String channelId;
    private Integer countOfViews;
    private final LocalTime time;

    public Posts(String text, String userId, String channelId, Integer countOfViews, LocalTime time) {
        this.text = text;
        this.userId = userId;
        this.channelId = channelId;
        this.countOfViews = countOfViews;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public String getUserId() {
        return userId;
    }

    public String getChannelId() {
        return channelId;
    }

    public Integer getCountOfViews() {
        return countOfViews;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCountOfViews(Integer countOfViews) {
        this.countOfViews = countOfViews;
    }

    @Override
    public int compareTo(Object o) {
        return this.time.compareTo(((Posts) o).time);
    }

    @Override
    public String toString() {
        return text + "\u001B[35m  " + time.getHour() + " : " + time.getMinute() + "\u001B[0m";
    }
}

