package com.forrest.testflux.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.forrest.testflux.R;
import com.forrest.testflux.flux.action.MainAction;
import com.forrest.testflux.flux.action.MainActionsCreator;
import com.forrest.testflux.flux.store.MainStore;
import com.forrest.testflux.flux.store.Store;
import com.forrest.testflux.ui.base.BaseFluxActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseFluxActivity {

    @BindView(R.id.et_text) EditText editText;
    @BindView(R.id.tv_text) TextView textView;

    private MainStore store;
    MainActionsCreator mainActionsCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDependencies();
    }

    private void initDependencies() {
        mainActionsCreator=new MainActionsCreator(dispatcher);
        store = new MainStore();
    }

    @OnClick(R.id.btn_send) void onSend() {
        if (editText.getText() != null) {
            mainActionsCreator.setText(editText.getText().toString());
        }
    }

    @OnClick(R.id.btn_next) void onNext() {
        Intent intent=new Intent(this,SecondActivity.class);
        startActivity(intent);
    }


    private void render(MainStore store) {
        textView.setText(store.getMessage());
    }

    @Override
    public void onViewUpdate(Object event) {
        if (event instanceof MainStore.MainStoreEvent) {
            if(MainAction.ACTION_NEW_MESSAGE.equals(((MainStore.MainStoreEvent) event).getOperationType())){
                render(store);
            }
        }
    }

    @Override
    public Store initStore() {
        return store;
    }
}
