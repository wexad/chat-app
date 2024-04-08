package uz.pdp.backend.model.group;

import uz.pdp.backend.enums.Type;
import uz.pdp.backend.model.baseModel.BaseModel;

public class Groups extends BaseModel {
    private String name;
    private Type type;

    public Groups(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
