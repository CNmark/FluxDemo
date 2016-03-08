package com.forrest.testflux.action;


import com.forrest.testflux.action.base.*;
import com.forrest.testflux.dispatcher.Dispatcher;


/**
 * Created by forrest on 16/3/1.
 */
public class MainActionsCreator extends ActionsCreatorFactory {


    public MainActionsCreator(Dispatcher dispatcher){
        super(dispatcher);
    }



    public void setText(String text){

        actionsCreator.sendMessage(new MainAction.MessageActionEntity().setText(text).buildWithType(MainAction.ACTION_NEW_MESSAGE));

    }

}
