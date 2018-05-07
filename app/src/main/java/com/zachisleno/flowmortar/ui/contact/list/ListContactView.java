package com.zachisleno.flowmortar.ui.contact.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.zachisleno.flowmortar.FlowServices;
import com.zachisleno.flowmortar.model.Contact;
import com.zachisleno.flowmortar.model.ContactsStorage;
import com.zachisleno.flowmortar.ui.contact.edit.name.EditNameScreen;

import flow.Flow;

public class ListContactView extends ListView {

    private final ContactsAdapter adapter = new ContactsAdapter();

    private ContactsStorage storage;

    public ListContactView(Context context, AttributeSet attrs) {
        super(context, attrs);
        storage = Flow.getService(FlowServices.CONTACTS_STORAGE, context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setAdapter(adapter);
        setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = adapter.getItem(position);
                Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                Flow.get(ListContactView.this).set(new EditNameScreen(contact.id));
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        adapter.setContacts(storage.listContacts());
    }
}