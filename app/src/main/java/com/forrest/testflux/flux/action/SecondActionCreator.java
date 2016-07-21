package com.forrest.testflux.flux.action;


import com.forrest.testflux.flux.action.base.ActionsCreatorFactory;
import com.forrest.testflux.dispatcher.Dispatcher;


/**
 * Created by forrest on 16/3/1.
 */
public class SecondActionCreator extends ActionsCreatorFactory {


    public SecondActionCreator(Dispatcher dispatcher){
        super(dispatcher);
    }




    public void update(){
           actionsCreator.sendMessage(new SecondAction.TestRxbusFlux2ActionEntity().
                   buildWithType(SecondAction.TYPE_UPDATE));
    }


}
