package com.forrest.testflux.store;


import com.forrest.testflux.action.MainAction;
import com.forrest.testflux.action.base.Action;
import com.forrest.testflux.model.Message;

/**
 * MessageStore类主要用来维护MainActivity的UI状态
 * Created by ntop on 18/12/15.
 */
public class MainStore extends Store {
    private static MainStore singleton;
    private Message mMessage = new Message();

    public MainStore() {
        super();
    }

    public String getMessage() {
        return mMessage.getMessage();
    }

    @Override
    public void onAction(Action action) {
        String operationType=action.getType();
        switch (operationType) {
            case MainAction.ACTION_NEW_MESSAGE:
                MainAction.MessageActionEntity messageActionEntity=(MainAction.MessageActionEntity) action.getData();
                mMessage.setMessage(messageActionEntity.getText());
                break;
            default:
        }
        emitStoreChange(operationType);
    }



    @Override
    public StoreChangeEvent changeEvent(String operationType) {
        return new MainStoreEvent(operationType);
    }

    public class MainStoreEvent extends StoreChangeEvent{

        public MainStoreEvent(String operationType){
            super(operationType);
        }
    }
}
