package com.example.exercisenine.controller;


import com.example.exercisenine.entity.enumiration.UserRole;
import com.example.exercisenine.service.MeterService;
import com.example.exercisenine.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(path = "/api/v1.0")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class MeterController {
    private final MeterService meterService;
    private final UserService userService;

    @GetMapping(path = "/report")
    public ModelAndView report() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("report");
        modelAndView.addObject("reports", meterService.report());
        modelAndView.addObject("total", meterService.reportTotal());
        return modelAndView;
    }

    @GetMapping(path = "/read")
    public String read() {
        return "download";
    }

    @PostMapping(path = "/read")
    public String excellRead(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException {
        meterService.excellRead(multipartFile);
        return "download";
    }

    @GetMapping(path = "/users")
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", userService.userList());
        return modelAndView;
    }

    @PostMapping(path = "/users")
    public String saveUser(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam UserRole role
    ) {
        userService.saveUser(login, password, role);
        return "redirect:/api/v1.0/users";
    }

    @GetMapping(path = "/users/edit")
    public ModelAndView editUser(
            @RequestParam(required = false) Long userId
    ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", userService.getUser(userId).get());
        return modelAndView;
    }

    @PostMapping(path = "users/edit")
    public String editUsers(
            @RequestParam(required = false) Long userId,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam UserRole role
    ) {
        userService.editUser(userId, login, password, role);
        return "redirect:/api/v1.0/users/edit?userId=" + userId;
    }

    @GetMapping(path = "/users/block")
    public String blockUser(
            @RequestParam(required = false) Long userId
    ) {
        userService.blockUser(userId);
        return "redirect:/api/v1.0/users";
    }
}
