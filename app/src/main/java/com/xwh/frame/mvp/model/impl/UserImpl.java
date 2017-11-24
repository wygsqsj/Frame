package com.xwh.frame.mvp.model.impl;

import com.xwh.frame.mvp.model.bean.Joke;
import com.xwh.frame.utils.net.base.BaseSubscribe;
import com.xwh.frame.utils.net.request.BeanRequest;

import java.util.HashMap;
import java.util.Map;

import rx.subjects.PublishSubject;

/**
 * 用于处理用户相关信息的Model
 * Created by xwh on 2017/11/24.
 */

public class UserImpl {
    private PublishSubject lifecycleSubject;

    public UserImpl(PublishSubject lifecycleSubject) {
        this.lifecycleSubject = lifecycleSubject;
    }

    public void load(BaseSubscribe<Joke> progressSubscribe) {
        Map<String, String> map = new HashMap<>();
        map.put("key", "75f8d152fbb1d1618a0f9a1fa5bbdb34");
        map.put("page", "2");
        map.put("sort", "desc");
        map.put("pagesize", "10");
        map.put("time", "1418816972");
       /* new BeanRequest<Joke>(lifecycleSubject, Joke.class)
                .post(map, progressSubscribe);*/
        new BeanRequest<Joke>(lifecycleSubject, Joke.class)
                .get("joke/content/list.from",
                        map, progressSubscribe);
    }


    public void checkApk(BaseSubscribe<Joke> progressSubscribe) {
        Map<String, String> map = new HashMap<>();
        map.put("key", "75f8d152fbb1d1618a0f9a1fa5bbdb34");
        map.put("page", "2");
        map.put("sort", "desc");
        map.put("pagesize", "10");
        map.put("time", "1418816972");

        new BeanRequest<Joke>(lifecycleSubject, Joke.class)
                .post(map, progressSubscribe);

    }

}
