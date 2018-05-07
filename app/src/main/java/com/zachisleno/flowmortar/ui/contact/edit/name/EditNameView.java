package com.zachisleno.flowmortar.ui.contact.edit.name;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zachisleno.flowmortar.FlowServices;
import com.zachisleno.flowmortar.R;
import com.zachisleno.flowmortar.model.ContactEditor;
import com.zachisleno.flowmortar.ui.contact.edit.mail.EditEmailScreen;

import flow.Flow;

public class EditNameView extends LinearLayout {

    private ContactEditor editor;
    private TextView emailView;
    private EditText nameView;
    private TextWatcher nameWatcher;

    public EditNameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        editor = Flow.getService(FlowServices.CONTACT_EDITOR, getContext());
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        emailView = findViewById(R.id.email);
        nameView = findViewById(R.id.edit_name);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        emailView.setText(editor.email);
        nameView.setText(editor.name);
        nameWatcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                editor.name = s.toString();
            }

            @Override public void afterTextChanged(Editable s) {
            }
        };
        nameView.addTextChangedListener(nameWatcher);

        nameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Flow.get(v).set(new EditEmailScreen(editor.id));
                return true;
            }
        });
    }

    @Override protected void onDetachedFromWindow() {
        nameView.setOnEditorActionListener(null);
        nameView.removeTextChangedListener(nameWatcher);
        super.onDetachedFromWindow();
    }
}