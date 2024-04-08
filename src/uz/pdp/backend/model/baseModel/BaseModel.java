package uz.pdp.backend.model.baseModel;

import java.util.UUID;

public class BaseModel {
    private final String id;

    public BaseModel() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }
}
