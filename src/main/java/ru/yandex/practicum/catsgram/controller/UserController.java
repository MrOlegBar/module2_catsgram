package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final List<User> users = new ArrayList<>();

    @GetMapping("/posts")
    public List<User> findAll() {
        return users;
    }

    @PostMapping(value = "/post")
    public void create(@RequestBody User user) {
        if (users.contains(user)) {
            throw new UserAlreadyExistException("Пользователь с указанным адресом электронной почты уже был добавлен ранее");
        } else if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты");
        } else {
            users.add(user);
        }
    }

    @PutMapping("/users")
    public void update(@RequestBody User user) {

    }
}
