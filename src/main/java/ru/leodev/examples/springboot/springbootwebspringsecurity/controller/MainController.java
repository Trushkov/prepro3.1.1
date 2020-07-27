package ru.leodev.examples.springboot.springbootwebspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.leodev.examples.springboot.springbootwebspringsecurity.model.Role;
import ru.leodev.examples.springboot.springbootwebspringsecurity.model.User;
import ru.leodev.examples.springboot.springbootwebspringsecurity.service.RoleServiceImpl;
import ru.leodev.examples.springboot.springbootwebspringsecurity.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleServiceImpl roleService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUserInfo(Model model){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("user", user);
        return "/user";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getUsers(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("ROLES", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        return "/admin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model,
                          @RequestParam(value = "rolesValues") String [] roles) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        for (String role: roles
        ) {
            if (!roleService.hasRole(role)){
                roleService.addRole(new Role(role));
            }
            roleSet.add(roleService.getRole(role));
        }
        user.setRoles(roleSet);
        if (user.getId() == 0) {
            userService.addUser(user);
            model.addAttribute("users", userService.getUsers());
            model.addAttribute("ROLES", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));

        } else {
            userService.updateUser(user);
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public String removeUser(@RequestParam("id") long id) {
        userService.remove(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/edit-user", method = {RequestMethod.POST, RequestMethod.GET})
    public String edit(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("ROLES", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        return "/edit-user";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("ROLES", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        return "/registration";
    }

    @RequestMapping(value = "/registration/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user,
                       @RequestParam(value = "rolesValues") String [] roles){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roleSet = new HashSet<>();
        for (String role: roles
        ) {
            if (!roleService.hasRole(role)){
                roleService.addRole(new Role(role));
            }
            roleSet.add(roleService.getRole(role));
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/registration";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
