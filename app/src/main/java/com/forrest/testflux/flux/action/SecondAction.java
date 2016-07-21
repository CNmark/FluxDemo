package com.forrest.testflux.flux.action;

import com.forrest.testflux.flux.action.base.Action;
import com.forrest.testflux.flux.action.base.IActionEntityBuilder;

/**
// * Created by forrest on 16/3/1.
// */
public class SecondAction extends Action<SecondAction.TestRxbusFlux2ActionEntity> {
    public static final String TYPE_UPDATE="TYPE_UPDATE";


    public SecondAction(String type, TestRxbusFlux2ActionEntity data){
        super(type,data);
    }


    public static class TestRxbusFlux2ActionEntity implements IActionEntityBuilder {

        @Override
        public Action buildWithType(String type) {
            return new SecondAction(type,this);
        }
    }



}
