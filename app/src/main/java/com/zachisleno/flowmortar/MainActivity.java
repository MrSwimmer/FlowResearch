package com.zachisleno.flowmortar;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zachisleno.flowmortar.ui.contact.edit.mail.EditEmailScreen;
import com.zachisleno.flowmortar.ui.contact.edit.name.EditNameScreen;
import com.zachisleno.flowmortar.ui.contact.list.ListContactsScreen;
import com.zachisleno.flowmortar.ui.welcom.WelcomeScreen;

import java.util.Map;

import flow.Direction;
import flow.Flow;
import flow.KeyChanger;
import flow.KeyDispatcher;
import flow.State;
import flow.TraversalCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context baseContext) {
        baseContext = Flow.configure(baseContext, this)
                .addServicesFactory(new FlowServices())
                .defaultKey(new WelcomeScreen())
                .dispatcher(KeyDispatcher.configure(this, new Changer()).build())
                .install();
        super.attachBaseContext(baseContext);
    }

    private class Changer implements KeyChanger {

        @Override
        public void changeKey(@Nullable State outgoingState, @NonNull State incomingState, @NonNull Direction direction, @NonNull Map<Object, Context> incomingContexts, @NonNull TraversalCallback callback) {
            Object key = incomingState.getKey();
            Context context = incomingContexts.get(key);

            if (outgoingState != null) {
                ViewGroup view = findViewById(android.R.id.content);
                outgoingState.save(view.getChildAt(0));
            }

            View view;
            if (key instanceof WelcomeScreen) {
                view = showKeyAsText(context, key, new ListContactsScreen());
            } else if (key instanceof ListContactsScreen) {
                view = showLayout(context, R.layout.list_contacts_screen);
            }  else if (key instanceof EditNameScreen) {
                view = showLayout(context, R.layout.edit_name_screen);
            } else if (key instanceof EditEmailScreen) {
                view = showLayout(context, R.layout.edit_email_screen);
            }
            else {
                view = showKeyAsText(context, key, null);
            }
            incomingState.restore(view);
            setContentView(view);
            callback.onTraversalCompleted();
        }

        private View showLayout(Context context, @LayoutRes int layout) {
            LayoutInflater inflater = LayoutInflater.from(context);
            return inflater.inflate(layout, null);
        }

        private View showKeyAsText(Context context, Object key, @Nullable final Object nextScreenOnClick) {
            TextView view = new TextView(context);
            view.setText(key.toString());

            if (nextScreenOnClick == null) {
                view.setOnClickListener(null);
            } else {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Flow.get(v).set(nextScreenOnClick);
                    }
                });
            }
            return view;
        }
    }
}

