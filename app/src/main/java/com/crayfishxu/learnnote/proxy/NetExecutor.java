package com.crayfishxu.learnnote.proxy;

import com.crayfishxu.learnnote.proxy.base.INetExecutor;
import com.crayfishxu.learnnote.proxy.base.IRequest;
import com.crayfishxu.learnnote.proxy.utils.VirtualHelper;
import com.google.gson.Gson;

/**
 * @author xujie
 * @Date 2019/2/20
 */

public class NetExecutor implements INetExecutor {

    private Gson gson = new Gson();

    @Override
    public <T> T executor(IRequest request) {
        String response = VirtualHelper.request(request.url(), request.params());
        return (T) gson.fromJson(response,request.Clazz());
    }
}
