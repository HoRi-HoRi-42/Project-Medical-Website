package com.example.project.controller;

import com.example.project.dto.AppointmentDto;
import com.example.project.model.Appointment;
import com.example.project.model.User;
import com.example.project.repository.AppointmentRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.AppointmentService;
import com.example.project.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/programari")
public class AppointmentController {

    private AppointmentService appointmentService;
    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private UserServiceImpl userService;

    public AppointmentController(AppointmentService appointmentService, AppointmentRepository appointmentRepository, UserRepository userRepository, UserServiceImpl userService) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @ModelAttribute("appointment")
    public AppointmentDto appointmentDto() {
        return new AppointmentDto();
    }

    @PostMapping
    public String registerAppointment(@ModelAttribute("appointment") AppointmentDto appointmentDto) {
        Appointment appointment = appointmentRepository.findByDateAndTimeAndDocFirstNameAndDocLastName(appointmentDto.getDate(), appointmentDto.getTime(), appointmentDto.getDocFirstName(), appointmentDto.getDocLastName());
        User userPacient = userRepository.findByFirstNameAndLastName(appointmentDto.getPacientFirstName(), appointmentDto.getPacientLastName());
        User userDoctor = userRepository.findByFirstNameAndLastName(appointmentDto.getDocFirstName(), appointmentDto.getDocLastName());

        if (userPacient == null) {
            return "redirect:/programari?pacientNotFound";
        }

        if (!userService.getActiveUsers().contains(userPacient)) {
            return "redirect:/programari?pacientNotLoggedIn";
        }

        if (userDoctor == null ) {
            return "redirect:/programari?doctorNotFound";
        } else if (userDoctor.getRole().equals("pacient")) {
            System.out.println(userDoctor.getRole());
            return "redirect:/programari?notADoctor";
        }

        if (appointment != null) {
            return "redirect:/programari?error";
        }
        appointmentDto.setDate(appointmentDto.getDate().substring(0, 10));
        appointmentService.save(appointmentDto);
        return "redirect:/programari?success";
    }

}
