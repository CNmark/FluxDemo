package com.forrest.testflux.action.base;


import com.forrest.testflux.dispatcher.Dispatcher;

/**
 * Created by forrest on 16/3/1.
 */
public abstract class ActionsCreatorFactory {

   public  ActionsCreator actionsCreator;

    public ActionsCreatorFactory(Dispatcher dispatcher){
        actionsCreator=ActionsCreator.get(dispatcher);
    }
}
