package com.ozcsoft.springboot.controller;

import com.ozcsoft.springboot.dto.RegistrationDto;
import com.ozcsoft.springboot.entity.User;
import com.ozcsoft.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistirationForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String register(@ModelAttribute("user") @Valid RegistrationDto newUser, BindingResult result, Model model){
        User existingUser = userService.findByEmail(newUser.getEmail());
        if (existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,"there is already user with this email");
        }
        if (result.hasErrors()){
            model.addAttribute("user", newUser);
            return "register";
        }

        userService.saveUser(newUser);

        return "redirect:/register?success";
    }
}
















