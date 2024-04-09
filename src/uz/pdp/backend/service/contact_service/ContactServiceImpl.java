package uz.pdp.backend.service.contact_service;

import uz.pdp.backend.model.contact.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    private static ContactService contactService;

    private List<Contacts> contacts;

    public ContactServiceImpl() {
        this.contacts = new ArrayList<>();
    }

    public static ContactService getInstance() {
        if (contactService == null) {
            contactService = new ContactServiceImpl();
        }
        return contactService;
    }

    @Override
    public boolean add(Contacts o) {
        return contacts.add(o);
    }

    @Override
    public boolean delete(Contacts contact) {
        return contacts.remove(contact);
    }

    @Override
    public Contacts get(String id) {
        for (Contacts contact : contacts) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contacts> getList() {
        return contacts;
    }

    @Override
    public boolean isExist(String id, String contactId) {
        for (Contacts contact : contacts) {
            if (contact.getUserId().equals(id) && contact.getId().equals(contactId)) {
                return true;
            }
        }
        return false;
    }
}
