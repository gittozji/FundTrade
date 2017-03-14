package me.zji.service;

import me.zji.entity.StaticShare;

/**
 * 静态份额服务
 * Created by imyu on 2017/3/14.
 */
public interface StaticShareService {
    void createOrUpdate(StaticShare staticShare);
    StaticShare queryByCodeAndAcco(StaticShare staticShare);
}
