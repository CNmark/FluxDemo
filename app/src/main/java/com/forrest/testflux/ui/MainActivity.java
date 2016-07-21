package com.forrest.testflux.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.forrest.testflux.R;
import com.forrest.testflux.flux.action.MainAction;
import com.forrest.testflux.flux.action.MainActionsCreator;
import com.forrest.testflux.dispatcher.Dispatcher;
import com.forrest.testflux.flux.store.MainStore;
import com.forrest.testflux.flux.store.SecondStore;
import com.forrest.testflux.flux.store.Store;
import com.forrest.testflux.ui.base.BaseFluxActivity;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseFluxActivity implements View.OnClickListener{

    private EditText vMessageEditor;
    private Button vMessageButton;
    private TextView vMessageView;

    private MainStore store;
    MainActionsCreator mainActionsCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        initDependencies();
    }



    private void initDependencies() {
        mainActionsCreator=new MainActionsCreator(dispatcher);
        store = new MainStore();

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
               // vMessageEditor.setText(null);
            }
        }else if(id==R.id.btn_next){
            Intent intent=new Intent(this,SecondActivity.class);
            startActivity(intent);
        }
    }


    private void render(MainStore store) {
        vMessageView.setText(store.getMessage());
    }


    @Override
    public void onViewUpdate(Object event) {
        if (event instanceof MainStore.MainStoreEvent) {
            if(MainAction.ACTION_NEW_MESSAGE.equals(((MainStore.MainStoreEvent) event).getOperationType())){
                render(store);
            }

        }else if(event instanceof SecondStore.SecondStoreEvent){
            Toast.makeText(this, "主界面收到消息了", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Store initStore() {
        return store;
    }
}
