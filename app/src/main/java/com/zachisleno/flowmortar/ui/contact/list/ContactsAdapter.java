package com.zachisleno.flowmortar.ui.contact.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zachisleno.flowmortar.R;
import com.zachisleno.flowmortar.model.Contact;

import java.util.ArrayList;
import java.util.List;

public final class ContactsAdapter extends BaseAdapter {
    private final List<Contact> contacts = new ArrayList<>();

    public void setContacts(List<Contact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    @Override public int getCount() {
        return contacts.size();
    }

    @Override public Contact getItem(int position) {
        return contacts.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);
        View view = convertView;
        if (view == null) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.list_contacts_screen_row_view, parent, false);
        }
        ((TextView) view.findViewById(R.id.contact_name)).setText(contact.name);
        ((TextView) view.findViewById(R.id.contact_email)).setText(contact.email);
        return view;
    }
}
