package com.weaveown.know;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangwei
 * @date 2021/5/12
 */
public interface IDispatcher {
    <R> R dispatch(String type, Request data);
}

interface IResultHandler {
    <R> R send(Request data);
}

class HttpHandler implements IResultHandler {
    @Override
    public <R> R send(Request data) {
        return null;
    }
}

interface ILocalHandler extends IResultHandler {

}


interface IAuthInterceptor {
    Request intercept(Request request);
}

interface LocalAuthInterceptor extends IAuthInterceptor {
}

interface WebHookAuthInterceptor extends IAuthInterceptor {

}

class DocumentInterceptor implements IAuthInterceptor {
    @Override
    public Request intercept(Request request) {
        return null;
    }
}


class DefaultDispatcher implements IDispatcher {
    Map<String, IAuthInterceptor> interceptors = new HashMap<>();
    Map<String, IResultHandler> resultHandlers = new HashMap<>();


    public DefaultDispatcher(LocalAuthInterceptor localAuthInterceptor, WebHookAuthInterceptor webhookAuthInterceptor, ILocalHandler resultHandler) {
        interceptors.put("LOCAL", localAuthInterceptor);
        interceptors.put("WEBHOOK", webhookAuthInterceptor);
        resultHandlers.put("LOCAL", resultHandler);
    }

    {
        interceptors.put("DOCUMENT", new DocumentInterceptor());
        resultHandlers.put("DOCUMENT", new HttpHandler());
        resultHandlers.put("WEBHOOK", new HttpHandler());
    }

    @Override
    public <R> R dispatch(String type, Request data) {
        final Request intercept = interceptors.get(type).intercept(data);
        return resultHandlers.get(type).send(data);
    }
}


class Request {
    Map<String, String> heads;
    Map<String, String> params;
    Object data;
}