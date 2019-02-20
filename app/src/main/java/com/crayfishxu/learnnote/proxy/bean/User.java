package com.crayfishxu.learnnote.proxy.bean;

/**
 * @author xujie
 * @Date 2019/2/20
 */

public class User {
    public String id;
    public String name;
    public String sex;
    public String address;

    @Override
    public String toString() {
        return "User" + id;
    }
}
