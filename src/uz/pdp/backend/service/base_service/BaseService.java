package uz.pdp.backend.service.base_service;

import uz.pdp.backend.model.baseModel.BaseModel;

import java.util.List;

public interface BaseService<E extends BaseModel>{
    boolean add(E o);

    boolean delete(E e);

    E get(String id);

    List<E> getList();
}
