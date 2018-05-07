package com.zachisleno.flowmortar;

import android.support.annotation.NonNull;

import com.zachisleno.flowmortar.model.Contact;
import com.zachisleno.flowmortar.model.ContactEditor;
import com.zachisleno.flowmortar.model.ContactsStorage;
import com.zachisleno.flowmortar.ui.contact.ContactsUiKey;
import com.zachisleno.flowmortar.ui.contact.edit.EditContactKey;

import flow.Services;
import flow.ServicesFactory;

public class FlowServices extends ServicesFactory {
    public static final String CONTACTS_STORAGE = "CONTACTS_STORAGE";
    public static final String CONTACT_EDITOR = "CONTACT_EDITOR";

    @Override
    public void bindServices(@NonNull Services.Binder services) {
        Object key = services.getKey();
        if (key.equals(new ContactsUiKey())) {
            // Setting up the ContactsUiKey means providing storage for Contacts.
            services.bind(CONTACTS_STORAGE, new ContactsStorage());
        } else if (key instanceof EditContactKey) {
            // Setting up the EditContactKey key means providing an editor for the contact.
            // This editor can be shared among any keys that have the EditContactKey as parent/ancestor!
            final String contactId = ((EditContactKey) key).contactId;
            ContactsStorage storage = services.getService(CONTACTS_STORAGE);
            Contact contact = storage.getContact(contactId);
            services.bind(CONTACT_EDITOR, new ContactEditor(contact));
        }
    }

    @Override public void tearDownServices(Services services) {
        // Nothing to do in this example, but if you need this hook to release resources, it's here!
        super.tearDownServices(services);
    }
}
