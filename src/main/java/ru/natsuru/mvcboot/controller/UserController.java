package ru.natsuru.mvcboot.controller;

import ru.natsuru.mvcboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.natsuru.mvcboot.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("listUsers", userService.getListUsers());
        return "/pages/users.html";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "/pages/new.html";
    }
    @PostMapping("/create")
    public String creatingNewUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    //Цей метод контролера треба використовувати через кнопку в шаблоні html, інакше помилка #405
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "/pages/single_user.html";
    }

    @PatchMapping("/change")
    public String changeUser(@ModelAttribute User user) {
        userService.changeUser(user);
        return "redirect:/";
    }
}
