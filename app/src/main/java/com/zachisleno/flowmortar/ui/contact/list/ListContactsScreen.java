package com.zachisleno.flowmortar.ui.contact.list;

import android.support.annotation.NonNull;

import com.zachisleno.flowmortar.ui.contact.ContactsUiKey;

import flow.ClassKey;
import flow.TreeKey;

public final class ListContactsScreen extends ClassKey implements TreeKey {
    @NonNull
    @Override
    public Object getParentKey() {
        return new ContactsUiKey();
    }
}
