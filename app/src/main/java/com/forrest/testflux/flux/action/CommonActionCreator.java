package com.forrest.testflux.flux.action;


import com.forrest.testflux.flux.action.base.ActionsCreatorFactory;
import com.forrest.testflux.dispatcher.Dispatcher;

/**
 * 公共的消息发送类，用于处理不是很多的逻辑界面
 * Created by forrest on 16/4/12.
 */
public class CommonActionCreator extends ActionsCreatorFactory {

    public CommonActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }


    public void refreshData(){
        actionsCreator.sendMessage(new CommonAction.CommonActionEntity().buildWithType(CommonAction.TYPE_REFRESH));
    }

}
