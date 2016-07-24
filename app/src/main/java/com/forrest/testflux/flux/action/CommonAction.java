package com.forrest.testflux.flux.action;


import com.forrest.testflux.flux.action.base.Action;
import com.forrest.testflux.flux.action.base.IActionEntityBuilder;

/**
 * 用于存放较少操作页面的类
 * Created by Forrest
 */
public class CommonAction extends Action<CommonAction.CommonActionEntity> {

    /***公共使用的刷新操作**/
    public static final String TYPE_REFRESH = "TYPE_REFRESH";
    /***公共使用的删除操作**/
    public static final String TYPE_DELETE= "TYPE_DELETE";

    public CommonAction(String type, CommonActionEntity data) {
        super(type, data);
    }

    public static class CommonActionEntity implements IActionEntityBuilder {

        /**定义一个公共的传参对象，通过**/
        private ICommonInfo commonInfo;
        private String text;
        private int index;

        public ICommonInfo getCommonInfo() {
            return commonInfo;
        }

        public CommonActionEntity setCommonInfo(ICommonInfo commonInfo) {
            this.commonInfo = commonInfo;
            return this;
        }

        public String getText() {
            return text;
        }

        public CommonActionEntity setText(String text) {
            this.text = text;
            return this;
        }

        public int getIndex() {
            return index;
        }

        public CommonActionEntity setIndex(int index) {
            this.index = index;
            return this;
        }

        private long productId;
        public long getProductId() {
            return productId;
        }
        public CommonActionEntity setProductId(long productId) {
            this.productId = productId;
            return this;
        }

        private int uid;
        public int getUid() {
            return uid;
        }
        public CommonActionEntity setUid(int uid) {
            this.uid = uid;
            return this;
        }


        @Override
        public Action buildWithType(String type) {
            return new CommonAction(type,this);
        }


    }

}
