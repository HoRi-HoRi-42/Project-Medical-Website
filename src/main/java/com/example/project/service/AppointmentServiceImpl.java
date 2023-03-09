package com.example.project.service;

import com.example.project.dto.AppointmentDto;
import com.example.project.model.Appointment;
import com.example.project.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment save(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment(appointmentDto.getDate(), appointmentDto.getTime(), appointmentDto.getPacientFirstName(), appointmentDto.getPacientLastName(), appointmentDto.getDocFirstName(), appointmentDto.getDocLastName());

        return appointmentRepository.save(appointment);
    }
}
