package com.exchange.exchange.controllers;

import com.exchange.exchange.models.User;
import com.exchange.exchange.repositories.UserRepository;
import com.exchange.exchange.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        String notification = registrationService.checkUser(user);
        model.addAttribute("notification", notification);
        if (notification.equals("Аккаунт успешно создан!")) {
            return "login";
        } else {
            return "registration";
        }
    }
}
