package com.crayfishxu.learnnote.proxy.utils;

import com.crayfishxu.learnnote.proxy.bean.User;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @author xujie
 * @Date 2019/2/20
 */

public class VirtualHelper {

    private static final Gson gson = new Gson();

    public static String request(String url, Map<String,Object> params){
        if(params != null){
            if("1".equals(params.get("username"))&&
                    "2".equals(params.get("password"))){
                User user = new User();
                user.id = "1";
                user.name = "XU";
                user.sex = "男";
                user.address = "xxx";
                return gson.toJson(user);
            }
        }
        return null;
    }
}
