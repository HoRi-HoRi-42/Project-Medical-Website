package com.example.project.service;

import com.example.project.dto.AppointmentDto;
import com.example.project.model.Appointment;

public interface AppointmentService {
    Appointment save(AppointmentDto appointmentDto);
}
