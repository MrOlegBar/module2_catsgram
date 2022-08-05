package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Set<User> users = new HashSet<>();

    @GetMapping
    public Set<User> findAll() {
        return users;
    }

    @PostMapping
    public void create(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты");
        } else if (users.contains(user)) {
            throw new UserAlreadyExistException("Пользователь с указанным адресом электронной почты уже был добавлен ранее");
        } else {
            users.add(user);
        }
    }

    @PutMapping
    public void update(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException("В переданных данных отсутствует адрес электронной почты");
        } else if (users.contains(user)) {
            users.remove(user);
            users.add(user);
        } else {
            users.add(user);
        }
    }
}
