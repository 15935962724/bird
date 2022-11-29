package com.bird.entity;

import java.util.Date;

public class Schedule {
    private Long id;

    private Long meetingItemId;

    private Date startDate;

    private Date endDate;

    private String content;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMeetingItemId() {
        return meetingItemId;
    }

    public void setMeetingItemId(Long meetingItemId) {
        this.meetingItemId = meetingItemId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}