package com.zachisleno.flowmortar.ui.contact.edit;

import flow.ClassKey;

public abstract class ContactKey extends ClassKey {
    public final String contactId;

    protected ContactKey(String contactId) {
        this.contactId = contactId;
    }

    @Override public boolean equals(Object o) {
        return super.equals(o) && contactId.equals(((ContactKey) o).contactId);
    }

    @Override public int hashCode() {
        return contactId.hashCode();
    }

    @Override public String toString() {
        return getClass().getSimpleName() + "{contactId=" + contactId + "}";
    }
}
