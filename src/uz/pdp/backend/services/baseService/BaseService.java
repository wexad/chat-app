package uz.pdp.backend.services.baseService;

import uz.pdp.backend.model.baseModel.BaseModel;

import java.util.List;

public interface BaseService<E extends BaseModel> {
    boolean add(E o);

    void update(E o);

    boolean delete(String id);

    E get(String id);

    List<E> getList();
}
