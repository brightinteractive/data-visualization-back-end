package com.example.datavisualizationbackend.models;

public class Event {
    private String userId;
    private String userName;
    private String group;
    private int assetId;
    private String assetTitle;

    public Event(String userId, String userName, String group, int assetId, String assetTitle) {
        this.userId = userId;
        this.userName = userName;
        this.group = group;
        this.assetId = assetId;
        this.assetTitle = assetTitle;
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
}
