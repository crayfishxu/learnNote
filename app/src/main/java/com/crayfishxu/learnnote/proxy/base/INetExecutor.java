package com.crayfishxu.learnnote.proxy.base;

/**
 * @author xujie
 * @Date 2019/2/20
 */

public interface INetExecutor {
    <T> T executor(IRequest request);
}
