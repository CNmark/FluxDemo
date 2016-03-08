package com.forrest.testflux.view;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.forrest.testflux.R;
import com.forrest.testflux.action.MainAction;
import com.forrest.testflux.action.MainActionsCreator;
import com.forrest.testflux.dispatcher.Dispatcher;
import com.forrest.testflux.store.MainStore;
import com.forrest.testflux.store.SecondStore;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText vMessageEditor;
    private Button vMessageButton;
    private TextView vMessageView;

    private Dispatcher dispatcher;
    private MainStore store;
    MainActionsCreator mainActionsCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDependencies();
        setupView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispatcher.unregister(this, store);
    }

    private void initDependencies() {
        dispatcher = Dispatcher.get();
        mainActionsCreator=new MainActionsCreator(dispatcher);
        store = new MainStore();
        dispatcher.register(this, store);

    }

    private void setupView() {
        vMessageEditor = (EditText) findViewById(R.id.message_editor);
        vMessageView = (TextView) findViewById(R.id.message_view);
        vMessageButton = (Button) findViewById(R.id.message_button);
        vMessageButton.setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.message_button) {
            if (vMessageEditor.getText() != null) {
                mainActionsCreator.setText(vMessageEditor.getText().toString());
                vMessageEditor.setText(null);
            }
        }else if(id==R.id.btn_next){
            Intent intent=new Intent(this,SecondActivity.class);
            startActivity(intent);
        }
    }


    private void render(MainStore store) {
        vMessageView.setText(store.getMessage());
    }

    @Subscribe
    public void onEventMainThread(Object event) {

        if (event instanceof MainStore.MainStoreEvent) {
            render(store);
        }else if(event instanceof SecondStore.SecondStoreEvent){
            Toast.makeText(this, "主界面收到消息了", Toast.LENGTH_LONG).show();
        }

    }
}
