package com.zachisleno.flowmortar.model;

public final class ContactEditor {
    public final String id;
    public String name;
    public String email;

    public ContactEditor(Contact contact) {
        this.id = contact.id;
        this.name = contact.name;
        this.email = contact.email;
    }

    public Contact toContact() {
        return new Contact(id, name, email);
    }
}