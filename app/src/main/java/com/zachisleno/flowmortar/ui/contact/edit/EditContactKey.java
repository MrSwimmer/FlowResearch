package com.zachisleno.flowmortar.ui.contact.edit;

import android.support.annotation.NonNull;

import com.zachisleno.flowmortar.ui.contact.ContactsUiKey;

import flow.TreeKey;

public final class EditContactKey extends ContactKey implements TreeKey {

    public EditContactKey(String contactId) {
        super(contactId);
    }

    @NonNull
    @Override public Object getParentKey() {
        return new ContactsUiKey();
    }
}