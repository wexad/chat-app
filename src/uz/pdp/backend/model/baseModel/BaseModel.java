package uz.pdp.backend.model.baseModel;

import java.io.Serializable;
import java.util.UUID;

public abstract class BaseModel implements Serializable {
    private final String id;

    public BaseModel() {
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }
}
