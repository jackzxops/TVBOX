package com.fongmi.android.tv.api.loader;

import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PyLoader {

    private final ConcurrentHashMap<String, Spider> spiders;
    private String recent;

    public PyLoader() {
        spiders = new ConcurrentHashMap<>();
        // 丞相注：chaquo 模块已禁用，Python 爬虫功能不可用
        // 所有 Python 爬虫请求将返回空 Spider
    }

    public void clear() {
        spiders.values().forEach(Spider::destroy);
        spiders.clear();
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }

    public Spider getSpider(String key, String api, String ext) {
        // 丞相注：由于 chaquo 模块已禁用，Python 爬虫功能不可用
        // 返回空的 Spider 实现
        return new SpiderNull();
    }

    public Object[] proxy(Map<String, String> params) throws Exception {
        if (recent == null) return null;
        Spider spider = spiders.get(recent);
        return spider != null ? spider.proxy(params) : null;
    }
}
