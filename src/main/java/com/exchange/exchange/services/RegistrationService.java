package com.exchange.exchange.services;

import com.exchange.exchange.models.Role;
import com.exchange.exchange.models.User;
import com.exchange.exchange.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String checkUser(User user) {

        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            return "Логин и пароль не могут быть пустыми!";
        }

        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return "Пользователь с таким логином уже существует!";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "Аккаунт успешно создан!";
    }
}
