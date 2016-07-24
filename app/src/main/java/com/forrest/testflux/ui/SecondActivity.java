package com.forrest.testflux.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.forrest.testflux.R;
import com.forrest.testflux.flux.action.CommonAction;
import com.forrest.testflux.flux.action.CommonActionCreator;
import com.forrest.testflux.dispatcher.Dispatcher;
import com.forrest.testflux.flux.store.SecondStore;
import com.forrest.testflux.flux.store.Store;
import com.forrest.testflux.ui.base.BaseFluxActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SecondActivity extends BaseFluxActivity {

    @BindView(R.id.btn_refresh)
    Button btn_refresh;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private CommonActionCreator commonActionCreator;
    SecondStore secondStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initDependencies();
    }

    private void initDependencies() {
        commonActionCreator=new CommonActionCreator(dispatcher);
        secondStore=new SecondStore();
        dispatcher.register(this, secondStore);
    }

    @OnClick(R.id.btn_refresh) void onRefresh() {
        btn_refresh.setText("refreshing...");
        progressBar.setVisibility(View.VISIBLE);
        commonActionCreator.refreshData();
    }

    @OnClick(R.id.btn_delete) void onDelete() {
        commonActionCreator.deleteData();
    }


    @Override
    public void onViewUpdate(Object event) {
        if(event instanceof Store.StoreChangeEvent){
            SecondStore.SecondStoreEvent secondStoreEvent= (SecondStore.SecondStoreEvent) event;
            if(secondStoreEvent.getOperationType().equals(CommonAction.TYPE_REFRESH)){
                btn_refresh.setText("refresh complete");
                progressBar.setVisibility(View.GONE);
            }else if(secondStoreEvent.getOperationType().equals(CommonAction.TYPE_DELETE)){
                Toast.makeText(this,"Delete..",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public Store initStore() {
        return secondStore;
    }
}
