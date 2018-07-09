package com.example.datavisualizationbackend.simulator.models;

import java.util.Date;

public class Event {
    private String eventType;
    private String userId;
    private String userName;
    private String group;
    private int assetId;
    private String assetTitle;
    private Date date;

    public Event(String eventType, String userId, String userName, String group, int assetId, String assetTitle, Date date) {
        this.eventType = eventType;
        this.userId = userId;
        this.userName = userName;
        this.group = group;
        this.assetId = assetId;
        this.assetTitle = assetTitle;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType='" + eventType + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", group='" + group + '\'' +
                ", assetId=" + assetId +
                ", assetTitle='" + assetTitle + '\'' +
                ", date=" + date +
                '}';
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

    public String getEventType() {
        return eventType;
    }

    public Date getDate() {
        return date;
    }

}
