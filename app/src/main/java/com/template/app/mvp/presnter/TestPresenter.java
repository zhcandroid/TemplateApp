package com.template.app.mvp.presnter;


import com.common.baselibrary.mvp.presenter.BasePresenter;
import com.common.baselibrary.net.HttpUtils;
import com.common.baselibrary.net.RequestParam;
import com.common.baselibrary.net.callback.OnResultCallBack;
import com.template.app.bean.TestBean;
import com.template.app.mvp.contract.TestContract;

import java.util.List;

/**
 * 测试 presenter
 */
public class TestPresenter extends BasePresenter<TestContract.TestView> implements TestContract.TestPresenter{

    /**
     * 获取列表数据
     */
    @Override
    public void getDataFromServer() {
        if(isAttachView()){
            //net work
            RequestParam params = new RequestParam();
            params.addParameter("page", 1);
            params.addParameter("rows", 20);
            params.addParameter("annNum", 1628);
            params.addParameter("totalYOrN", true);
            HttpUtils.getInstance().postRequest("tmann/annInfoView/annSearchDG.html",
                    params, new OnResultCallBack<List<TestBean>>() {

                        @Override
                        public void onSuccess(boolean success, int code, String msg, Object tag, List<TestBean> response) {
                            if(response != null && response.size() > 0){
                                getView().showList(response);

                            }
                        }

                        @Override
                        public void onFailure(Object tag, Exception e) {

                        }

                        @Override
                        public void onCompleted() {

                        }
                    }
            );
        }



    }


}
