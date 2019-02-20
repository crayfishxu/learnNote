package com.crayfishxu.learnnote.proxy.base;

import java.util.Map;

/**
 * @author xujie
 * @Date 2019/2/20
 */

public interface IRequest {
    String url();

    Map<String,Object> params();

    Class<?> Clazz();
}
