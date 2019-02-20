package com.crayfishxu.learnnote.proxy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.crayfishxu.learnnote.R;
import com.crayfishxu.learnnote.proxy.bean.User;

/**
 * @author xujie
 * @Date 2019/2/20
 */

public class ProxyTestActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proxy_main);

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginApi loginApi = ApiGenerator.generateApi(LoginApi.class);
                User user = loginApi.login("1","2");
                Log.d("ProxyTestActivity",user.name);
            }
        });
    }
}
