package com.example;

public class ReservationRequest {

    public ReservationRequest(String userId, String startDate, String endDate, String roomType) {
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomType = roomType;
    }

    private String userId;
    private String startDate;
    private String endDate;
    private String roomType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
