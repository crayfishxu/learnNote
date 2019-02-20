package com.crayfishxu.learnnote.proxy;

import com.crayfishxu.learnnote.proxy.bean.User;

/**
 * @author xujie
 * @Date 2019/2/20
 */
@URL("http://www.crayfishxu.top")
public interface LoginApi {
    User login(@Param("username") String name,
               @Param("password") String password);
}
