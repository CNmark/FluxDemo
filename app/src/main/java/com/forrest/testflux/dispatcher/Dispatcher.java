package com.forrest.testflux.dispatcher;



import android.content.Context;

import com.forrest.testflux.action.base.Action;
import com.forrest.testflux.store.Store;

import java.util.ArrayList;
import java.util.List;


/**
 * Flux的Dispatcher模块
 * Created by ntop on 18/12/15.
 */
public class Dispatcher {
    private static Dispatcher instance;
    private final List<Store> stores = new ArrayList<>();

    public static Dispatcher get() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

    Dispatcher() {}

    public void register(Context context,final Store store) {
        if (!stores.contains(store)) {
            store.register(context);
            stores.add(store);
        }
    }

    public void unregister(Context context,final Store store) {
        store.unRegister(context);
        stores.remove(store);
    }

    public void dispatch(Action action) {
        post(action);
    }

    private void post(final Action action) {
        for (Store store : stores) {
            store.onAction(action);
        }
    }
}
