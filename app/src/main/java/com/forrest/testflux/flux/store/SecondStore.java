package com.forrest.testflux.flux.store;


import android.os.AsyncTask;

import com.forrest.testflux.flux.action.CommonAction;
import com.forrest.testflux.flux.action.base.Action;

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
        if(action instanceof CommonAction){
            String type=action.getType();
            switch (type){
                case CommonAction.TYPE_REFRESH:
                    new RefreshTask().execute(type);
                    break;
                case CommonAction.TYPE_DELETE:
                    emitStoreChange(type);
                    break;
            }
        }
    }

    private class RefreshTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return params[0];
        }

        @Override
        protected void onPostExecute(String text) {
            super.onPostExecute(text);
            emitStoreChange(text);
        }
    }

    public class SecondStoreEvent extends StoreChangeEvent{

        public SecondStoreEvent(String operationType){
            super(operationType);
        }
    }

}
