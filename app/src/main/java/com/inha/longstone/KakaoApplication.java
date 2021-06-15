package com.inha.longstone;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

import com.kakao.sdk.common.util.Utility;

public class KakaoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        KakaoSdk.init(this,"d0ef900de41a3baed0de3e59eefec47f");
    }
}
