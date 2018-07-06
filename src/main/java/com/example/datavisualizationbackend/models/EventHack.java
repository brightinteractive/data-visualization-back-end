package com.example.datavisualizationbackend.models;

import java.io.Serializable;

public class EventHack {
    private String eventType;
    private String userId;
    private String userName;
    private String group;
    private int assetId;
    private String assetTitle;
    private long date;

    public EventHack(String eventType, String userId, String userName, String group, int assetId, String assetTitle, long date) {
        this.eventType = eventType;
        this.userId = userId;
        this.userName = userName;
        this.group = group;
        this.assetId = assetId;
        this.assetTitle = assetTitle;
        this.date = date;
    }

    public int getAssetId() {
        return assetId;
    }

    public String getAssetTitle() {
        return assetTitle;
    }

    public String getGroup() {
        return group;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEventType() { return eventType; }

    public long getDate() { return date; }

}
