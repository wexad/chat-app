package uz.pdp.backend.service.contact_service;

import uz.pdp.backend.model.contact.Contacts;
import uz.pdp.backend.service.base_service.BaseService;

import java.util.List;

public interface ContactService extends BaseService<Contacts> {
    boolean isExist(String id, String contactId);
    List<Contacts> getContacts(String userId);
    Contacts getContact(String userId, String contactId);
}
