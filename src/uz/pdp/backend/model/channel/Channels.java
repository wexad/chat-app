package uz.pdp.backend.model.channel;

import uz.pdp.backend.enums.Type;
import uz.pdp.backend.model.baseModel.BaseModel;

public class Channels extends BaseModel {
    private String name;
    private String description;
    private final Type type;

    public Channels(String name, String description, Type type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
