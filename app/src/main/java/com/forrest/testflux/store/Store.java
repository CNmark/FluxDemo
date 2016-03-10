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

    /**
     * 传入操作类型，然后出发主界面更新
     * @param operationType
     */
    void emitStoreChange(String operationType) {
        EventBus.getDefault().post(changeEvent(operationType));

    }

    public abstract StoreChangeEvent changeEvent(String operationType);

    /**
     * 所有逻辑的处理，在实现类中可以简单想象成对应着一个Activity（View）的增删改查的处理
     * @param action
     */
    public abstract void onAction(Action action);

    /**
     * 返回到view中的对象，在activity得到这个对象，通过operationtype来判断对应的操作来更新对应的ui
     */
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
