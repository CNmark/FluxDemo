package com.forrest.testflux.store;

import android.content.Context;


import com.forrest.testflux.action.base.Action;

import org.greenrobot.eventbus.EventBus;

/**
 * Flux的Store模块
 * Created by ntop on 18/12/15.
 */
public abstract class Store {

    protected Store() {

    }

    public   void register(Context context){
        EventBus.getDefault().register(context);
    }

    public   void unRegister(Context context){
        EventBus.getDefault().unregister(context);
    }


    void emitStoreChange(String operationType) {
        EventBus.getDefault().post(changeEvent(operationType));

    }

    public abstract StoreChangeEvent changeEvent(String operationType);
    public abstract void onAction(Action action);

    public class StoreChangeEvent {
        private String operationType;

        public String getOperationType() {
            return operationType;
        }

        public StoreChangeEvent(String operationType){
            this.operationType=operationType;
        }
    }
}
