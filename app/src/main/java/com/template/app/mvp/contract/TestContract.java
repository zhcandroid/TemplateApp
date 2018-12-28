package com.template.app.mvp.contract;

import com.common.baselibrary.mvp.view.IView;
import com.template.app.bean.TestBean;

import java.util.List;

/**
 * testActivity控制器
 * 使用契约类Contract是为了减少java文件数量 方便查看管理
 *
 */
public interface TestContract {

    interface TestPresenter {

        /**
         * 从服务器获取数据
         */
        void getDataFromServer();

    }


    interface TestView extends IView {

        /**
         * 获取数据成功 显示列表
         */
        void showList(List<TestBean> list);

    }




}
