package com.forrest.testflux.flux.action.base;


public abstract class Action<T extends IActionEntityBuilder> {
    private final String type;
    private final T data;



    public Action(String type, T data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }

}
