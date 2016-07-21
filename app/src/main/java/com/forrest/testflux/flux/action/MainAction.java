package com.forrest.testflux.flux.action;


import com.forrest.testflux.flux.action.base.Action;
import com.forrest.testflux.flux.action.base.IActionEntityBuilder;

/**
 * Created by ntop on 18/12/15.
 */
public class MainAction extends Action<MainAction.MessageActionEntity> {
    public static final String ACTION_NEW_MESSAGE = "new_message";

    public MainAction(String type, MessageActionEntity data) {
        super(type, data);
    }

    public static class MessageActionEntity implements IActionEntityBuilder {

        private String text;

        public String getText() {
            return text;
        }

        public MessageActionEntity setText(String text) {
            this.text = text;
            return this;
        }

        @Override
        public Action buildWithType(String type) {
            return new MainAction(type,this);
        }
    }

}
