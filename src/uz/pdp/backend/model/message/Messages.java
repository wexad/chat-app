package uz.pdp.backend.model.message;

import uz.pdp.backend.enums.MessageType;
import uz.pdp.backend.model.baseModel.BaseModel;

import java.sql.Time;
import java.time.LocalTime;

public class Messages extends BaseModel implements Comparable {
    private String text;
    private final MessageType type;
    private final String userId;
    private final String toId;
    private final LocalTime time;
    private boolean isRead;

    public Messages(String text, MessageType type, String userId, String toId, LocalTime time, boolean isRead) {
        this.text = text;
        this.type = type;
        this.userId = userId;
        this.toId = toId;
        this.time = time;
        this.isRead = isRead;
    }

    public String getText() {
        return text;
    }

    public MessageType getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public String getToId() {
        return toId;
    }

    public LocalTime getTime() {
        return time;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(Object o) {
        return this.time.compareTo(((Messages) o).time);
    }

    @Override
    public String toString() {
        return text + " ( " + "\u001B[35m" + time + " ) " + "\u001B[0m";
    }
}
