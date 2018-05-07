package com.zachisleno.flowmortar.ui.contact.edit.mail;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zachisleno.flowmortar.FlowServices;
import com.zachisleno.flowmortar.R;
import com.zachisleno.flowmortar.model.ContactEditor;
import com.zachisleno.flowmortar.model.ContactsStorage;
import com.zachisleno.flowmortar.ui.contact.list.ListContactsScreen;

import flow.Flow;

public class EditEmailView extends LinearLayout {

    ContactEditor editor;
    private TextView nameView;
    private EditText emailView;
    private TextWatcher emailWatcher;
    private View saveButton;

    public EditEmailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        editor = Flow.getService(FlowServices.CONTACT_EDITOR, context);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        nameView = findViewById(R.id.name);
        emailView = findViewById(R.id.edit_email);
        saveButton = findViewById(R.id.save);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        nameView.setText(editor.name);
        emailView.setText(editor.email);
        emailWatcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor.email = s.toString();
            }

            @Override public void afterTextChanged(Editable s) {
            }
        };
        emailView.addTextChangedListener(emailWatcher);

        saveButton.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                editor.email = emailView.getText().toString();
                ContactsStorage storage = Flow.getService(FlowServices.CONTACTS_STORAGE, v.getContext());
                //noinspection ConstantConditions
                storage.save(editor.toContact());
                Flow.get(v).set(new ListContactsScreen());
            }
        });
    }

    @Override protected void onDetachedFromWindow() {
        saveButton.setOnClickListener(null);
        emailView.removeTextChangedListener(emailWatcher);
        super.onDetachedFromWindow();
    }
}