package com.crayfishxu.learnnote.proxy;

import com.crayfishxu.learnnote.proxy.base.IRequest;

import java.util.Map;

/**
 * @author xujie
 * @Date 2019/2/20
 */

public class Request implements IRequest {

    String url;
    Map<String,Object> params;
    Class<?> clazz;

    public Request(String url,Map<String,Object> params,Class<?> clazz) {
        this.url = url;
        this.params = params;
        this.clazz = clazz;
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public Map<String, Object> params() {
        return params;
    }

    @Override
    public Class<?> Clazz() {
        return clazz;
    }
}
