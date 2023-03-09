package com.example.project.model;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "pacient_first_name")
    private String pacientFirstName;

    @Column(name = "pacient_last_name")
    private String pacientLastName;

    @Column(name = "doc_first_name")
    private String docFirstName;

    @Column(name = "doc_last_name")
    private String docLastName;

    public Appointment() {

    }

    public Appointment(String date, String time, String pacientFirstName, String pacientLastName, String docFirstName, String docLastName) {
        this.date = date;
        this.time = time;
        this.pacientFirstName = pacientFirstName;
        this.pacientLastName = pacientLastName;
        this.docFirstName = docFirstName;
        this.docLastName = docLastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
