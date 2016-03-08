package com.forrest.testflux.store;


import com.forrest.testflux.action.SecondAction;
import com.forrest.testflux.action.base.Action;

/**
 * Created by forrest on 16/2/29.
 */
public class SecondStore extends Store  {



    @Override
    public StoreChangeEvent changeEvent(String operationType) {
        return new SecondStoreEvent(operationType);
    }

    @Override
    public void onAction(Action action) {
        String type=action.getType();
        switch (type){
            case SecondAction.TYPE_UPDATE:
                break;
        }

        emitStoreChange(type);

    }


    public class SecondStoreEvent extends StoreChangeEvent{

        public SecondStoreEvent(String operationType){
            super(operationType);
        }
    }

}