package com.example.project.repository;

import com.example.project.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByDateAndTimeAndDocFirstNameAndDocLastName(String date, String time, String docFirstName, String docLastName);
    List<Appointment> findByPacientFirstNameAndPacientLastName(String pacientFirstName, String pacientLastName);
    List<Appointment> findByDocFirstNameAndDocLastName(String docFirstName, String docLastName);
}
