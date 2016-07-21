package com.forrest.testflux.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.forrest.testflux.dispatcher.Dispatcher;
import com.forrest.testflux.flux.store.Store;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by forrest on 16/3/20.
 */
public abstract class BaseFluxActivity extends Activity implements IFluxBaseHelper{

    public Dispatcher dispatcher;

    private Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatcher=Dispatcher.get();
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
            store.onRelease();
            dispatcher.unregister(this, store);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dispatcher.reCheckStore(BaseFluxActivity.this,store);
    }



    @Subscribe
    public void onEventMainThread(Object event) {
        onViewUpdate(event);
    }
}
