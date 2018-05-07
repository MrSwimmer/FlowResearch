package com.zachisleno.flowmortar.ui.contact.edit.name;

import android.support.annotation.NonNull;

import com.zachisleno.flowmortar.ui.contact.edit.ContactKey;
import com.zachisleno.flowmortar.ui.contact.edit.EditContactKey;

import flow.TreeKey;

public final class EditNameScreen extends ContactKey implements TreeKey {
    public EditNameScreen(String contactId) {
        super(contactId);
    }

    @NonNull
    @Override public Object getParentKey() {
        return new EditContactKey(contactId);
    }
}
