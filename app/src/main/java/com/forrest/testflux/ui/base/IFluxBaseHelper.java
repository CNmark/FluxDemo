package com.forrest.testflux.ui.base;


import com.forrest.testflux.flux.store.Store;

/**
 * Created by forrest on 16/3/20.
 */
public interface IFluxBaseHelper {

    /**
     * evenbus的事件回调，也是页面ui更新管理器
     */
    void onViewUpdate(Object event);

    /**
     * 被继承的Fragment必须初始化返回Store
     * @return
     */
    Store initStore();
}
