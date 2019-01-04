package com.wul.hlt_client.ui.tousu;

import com.wul.hlt_client.mvp.BasePresenter;
import com.wul.hlt_client.mvp.BaseRequestView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class TousuContract {
    interface View extends BaseRequestView {

        void sounrss();
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
