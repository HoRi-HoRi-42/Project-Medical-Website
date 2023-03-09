package com.example.project.controller;

import com.example.project.dto.AppointmentUserDto;
import com.example.project.model.Appointment;
import com.example.project.model.User;
import com.example.project.repository.AppointmentRepository;
import java.util.List;
import com.example.project.repository.UserRepository;
import com.example.project.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewAppointmentController {
    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private List<Appointment> appointmentList;
    private UserServiceImpl userService;

    public ViewAppointmentController(AppointmentRepository appointmentRepository, UserRepository userRepository, UserServiceImpl userService) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @ModelAttribute("user")
    public AppointmentUserDto appointmentUserDto() {
        return new AppointmentUserDto();
    }

    @RequestMapping("/veziprogramari")
    @PostMapping()
    public String viewAppointment(@ModelAttribute("user") AppointmentUserDto appointmentUserDto) {
        User user = userRepository.findByFirstNameAndLastName(appointmentUserDto.getPrenume(), appointmentUserDto.getNume());
        if (user == null) {
            return "redirect:/veziprogramari?error";
        }

        if (user.getRole().equals("pacient") && !userService.getActiveUsers().contains(user)) {
            return "redirect:/veziprogramari?pacientNotLoggedIn";
        }else if (!user.getRole().equals("pacient") && !userService.getActiveUsers().contains(user)) {
            return "redirect:/veziprogramari?doctorNotLoggedIn";
        }

        if (user.getRole().equals("pacient")){
            appointmentList = appointmentRepository.findByPacientFirstNameAndPacientLastName(appointmentUserDto.getPrenume(), appointmentUserDto.getNume());
        } else {
            appointmentList = appointmentRepository.findByDocFirstNameAndDocLastName(appointmentUserDto.getPrenume(), appointmentUserDto.getNume());
        }
        return "redirect:/tabelprogramari";
    }

    @GetMapping("/tabelprogramari")
    public ModelAndView getAppointments() {
        ModelAndView mav = new ModelAndView("tabelprogramari");
        mav.addObject("appointments", appointmentList);
        return mav;
    }
}
