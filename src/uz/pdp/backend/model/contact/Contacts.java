package uz.pdp.backend.model.contact;

import uz.pdp.backend.model.baseModel.BaseModel;

public class Contacts extends BaseModel implements Comparable {

    private final String userId;
    private String contactId;
    private String name;

    public Contacts(String userId, String contactId, String name) {
        this.userId = userId;
        this.contactId = contactId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String getId() {
        return contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Contacts) o).name);
    }
}
