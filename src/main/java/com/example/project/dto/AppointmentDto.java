package com.example.project.dto;

public class AppointmentDto {

    private String date;
    private String time;
    private String pacientFirstName;
    private String pacientLastName;
    private String docFirstName;
    private String docLastName;

    public AppointmentDto() {

    }

    public AppointmentDto(String date, String time, String pacientFirstName, String pacientLastName, String docFirstName, String docLastName) {
        this.date = date;
        this.time = time;
        this.pacientFirstName = pacientFirstName;
        this.pacientLastName = pacientLastName;
        this.docFirstName = docFirstName;
        this.docLastName = docLastName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPacientFirstName() {
        return pacientFirstName;
    }

    public void setPacientFirstName(String pacientFirstName) {
        this.pacientFirstName = pacientFirstName;
    }

    public String getPacientLastName() {
        return pacientLastName;
    }

    public void setPacientLastName(String pacientLastName) {
        this.pacientLastName = pacientLastName;
    }

    public String getDocFirstName() {
        return docFirstName;
    }

    public void setDocFirstName(String docFirstName) {
        this.docFirstName = docFirstName;
    }

    public String getDocLastName() {
        return docLastName;
    }

    public void setDocLastName(String docLastName) {
        this.docLastName = docLastName;
    }
}
