package com.example.myapplication6.database;

public class Message {
    private String createDate;
    private int id;
    private int isMyMessageFlag;
    private String message;

    public Message(String message, String date, int uID) {
        this.message = message;
        this.createDate = date;
        this.isMyMessageFlag = uID;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDate() {
        return this.createDate;
    }

    public void setDate(String createDate) {
        this.createDate = createDate;
    }

    public int getUserID() {
        return this.isMyMessageFlag;
    }

    public void setUserID(int isMyMessageFlag) {
        this.isMyMessageFlag = isMyMessageFlag;
    }
}
