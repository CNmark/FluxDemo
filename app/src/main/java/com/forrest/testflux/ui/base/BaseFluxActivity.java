package com.forrest.testflux.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.forrest.testflux.dispatcher.Dispatcher;
import com.forrest.testflux.flux.store.Store;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by forrest on 16/3/20.
 */
public abstract class BaseFluxActivity extends AppCompatActivity implements IFluxBaseHelper{

    public Dispatcher dispatcher;
    private Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatcher=Dispatcher.get();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        store=initStore();
        if(store!=null){
            dispatcher.register(this, store);
            store.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(store!=null){
            dispatcher.unregister(this, store);
        }
    }

    @Subscribe
    public void onEventMainThread(Object event) {
        onViewUpdate(event);
    }
}
