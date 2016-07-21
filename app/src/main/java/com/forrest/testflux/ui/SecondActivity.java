package com.forrest.testflux.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.forrest.testflux.R;
import com.forrest.testflux.flux.action.SecondActionCreator;
import com.forrest.testflux.dispatcher.Dispatcher;
import com.forrest.testflux.flux.store.SecondStore;

import org.greenrobot.eventbus.Subscribe;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private Dispatcher dispatcher;

    SecondActionCreator secondActionCreator;

    SecondStore secondStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initDependencies();
        setupView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispatcher.unregister(this, secondStore);
    }

    private void initDependencies() {
        dispatcher = Dispatcher.get();
        secondActionCreator=new SecondActionCreator(dispatcher);
        secondStore=new SecondStore();
        dispatcher.register(this, secondStore);
    }

    private void setupView() {
        findViewById(R.id.btn_click).setOnClickListener(this);
    }

    @Subscribe
    public void onEventMainThread(Object event) {

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_click) {
            secondActionCreator.update();
        }
    }
}
