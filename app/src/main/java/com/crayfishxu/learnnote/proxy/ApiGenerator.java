package com.crayfishxu.learnnote.proxy;

import android.text.TextUtils;

import com.crayfishxu.learnnote.proxy.base.INetExecutor;
import com.crayfishxu.learnnote.proxy.base.IRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xujie
 * @Date 2019/2/20
 */

public class ApiGenerator {

    private static final Map<Class,Object> sApiCache = new HashMap<>();

    private static INetExecutor iNetExecutor;

    private static class Handler<T> implements InvocationHandler{

        private Class<T> apiInterface;

        public Handler(Class<T> apiInterface){
            this.apiInterface = apiInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            IRequest request = resolveRequest(method,args,apiInterface);
            if(iNetExecutor == null)
                iNetExecutor = defaultNetExecutor();

            return iNetExecutor.executor(request);
        }
    }

    private static <T> IRequest resolveRequest(Method method,Object[] args,Class<T> apiInterface){
        StringBuilder urlBuilder = new StringBuilder();
        Map<String,Object> params = null;
        if(apiInterface.isAnnotationPresent(URL.class)){
            String baseUrl = apiInterface.getAnnotation(URL.class).value();
            if(!TextUtils.isEmpty(baseUrl)){
                urlBuilder.append(baseUrl);
            }
        }
        if(method.isAnnotationPresent(URL.class)){
            String subUrl = method.getAnnotation(URL.class).value();
            if(!TextUtils.isEmpty(subUrl)){
                urlBuilder.append(subUrl);
            }
        }
        int index = 0;
        for (Annotation[] annotations : method.getParameterAnnotations()) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Param) {
                    String key = ((Param) annotation).value();
                    if (!TextUtils.isEmpty(key)) {
                        if (params == null) {
                            params = new HashMap<>();
                        }
                        params.put(key,args[index]);
                    }
                    break;
                }
            }
            index++;
        }
        return new Request(urlBuilder.toString(),params,method.getReturnType());
    }

    private static INetExecutor defaultNetExecutor(){
        return new NetExecutor();
    }

    public static <T> T generateApi(Class<T> apiInterface){
        if(apiInterface == null || !apiInterface.isInterface()){
            throw new RuntimeException("the apiInterface isnull or isn't interface.");
        }
        synchronized (ApiGenerator.class){
            Object api = sApiCache.get(apiInterface);
            if(api == null){
                api = Proxy.newProxyInstance(apiInterface.getClassLoader(),
                        new Class[]{apiInterface},new Handler<>(apiInterface));
                sApiCache.put(apiInterface,api);
            }
            return (T) api;
        }
    }

    /**
     * 自定义执行器
     * @param netExecutor
     */
    public static void setNetExecutor(INetExecutor netExecutor){
        iNetExecutor = netExecutor;
    }
}
