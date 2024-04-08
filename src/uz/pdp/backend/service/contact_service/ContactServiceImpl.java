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
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Contacts get(String id) {
        return null;
    }

    @Override
    public List<Contacts> getList() {
        return null;
    }
}
