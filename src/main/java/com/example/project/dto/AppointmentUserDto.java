package com.example.project.dto;

public class AppointmentUserDto {
    private String nume;
    private String prenume;

    public AppointmentUserDto() {

    }

    public AppointmentUserDto(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
}
