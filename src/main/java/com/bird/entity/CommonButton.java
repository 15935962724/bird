package com.bird.entity;

/**
 * 普通按钮（子按钮）
 * wz,2020.1.13
 */
public class CommonButton extends Button {

    private String type;

    private String key;

    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}