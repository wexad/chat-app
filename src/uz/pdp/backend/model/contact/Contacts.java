package uz.pdp.backend.model.contact;

import uz.pdp.backend.model.baseModel.BaseModel;

public class Contacts extends BaseModel {
    private final String userId;
    private String id;
    private String name;

    public Contacts(String userId, String id, String name) {
        this.userId = userId;
        this.id = id;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
