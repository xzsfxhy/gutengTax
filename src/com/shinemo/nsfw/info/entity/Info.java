package com.shinemo.nsfw.info.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2017/8/1.
 */
public class Info implements Serializable{
    private String infoId;
    private String type;
    private String source;
    private String title;
    private String content;
    private String memo;
    private String creator;
    private Timestamp createTime;
    private String state;

    public static String INFO_STATE_PUBLIC="1";
    public static String INFO_STATE_STOP="0";

    public static String INFO_TYPE_TZGG="tzgg";
    public static String INFO_TYPE_ZCSD="zcsd";
    public static String INFO_TYPE_NSZD="nszd";
    public static Map<String,String> INFO_TYPE_MAP;
    static{
        INFO_TYPE_MAP=new HashMap<>();
        INFO_TYPE_MAP.put(INFO_TYPE_TZGG,"通知公告");
        INFO_TYPE_MAP.put(INFO_TYPE_NSZD,"纳税指导");
        INFO_TYPE_MAP.put(INFO_TYPE_ZCSD,"政策速递");
    }

    public Info() {
    }

    public Info(String infoId, String type, String source, String title, String content, String memo, String creator, Timestamp createTime, String state) {
        this.infoId = infoId;
        this.type = type;
        this.source = source;
        this.title = title;
        this.content = content;
        this.memo = memo;
        this.creator = creator;
        this.createTime = createTime;
        this.state = state;
    }

    public String getInfoId() {
        return infoId;
    }

    public Info setInfoId(String infoId) {
        this.infoId = infoId;
        return this;
    }

    public String getType() {
        return type;
    }

    public Info setType(String type) {
        this.type = type;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Info setSource(String source) {
        this.source = source;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Info setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Info setContent(String content) {
        this.content = content;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public Info setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Info setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getState() {
        return state;
    }

    public Info setState(String state) {
        this.state = state;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public Info setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}
