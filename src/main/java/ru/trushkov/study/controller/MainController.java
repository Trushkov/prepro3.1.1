package ru.trushkov.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.trushkov.study.model.Role;
import ru.trushkov.study.model.User;
import ru.trushkov.study.service.RoleServiceImpl;
import ru.trushkov.study.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleServiceImpl roleService;

    @GetMapping(value = {"/user", "/admin", "/admin/admin-user",
            "/admin/add-new-user", "/admin/add", "/admin/edit-user", "/registration"})
    public ResponseEntity<User> getUserInfo(){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*@GetMapping(value = {"/admin", "/registration"})
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = {"/user", "/admin", "/admin/admin-user",
            "/admin/add-new-user", "/admin/add", "/registration"})
    public ResponseEntity<Set<Role>> getRoles(){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return new ResponseEntity<>(user.getRoles(), HttpStatus.OK);
    }

    @PostMapping(value = "/admin/add")
    public ResponseEntity<?> addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "roles") String [] roles) {
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
        } else {
            userService.updateUser(user);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping(value = "/admin/admin-user")
    public String getAdminUserInfo(Model model){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("user", user);
        return "/admin-user";
    }

    @GetMapping(value = "/admin")
    public String getUsers(ModelMap model) {
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("ROLES", Arrays.asList("USER", "ADMIN"));
        return "/admin";
    }

    @GetMapping(value = "/admin/add-new-user")
    public String addNewUser(Model model){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("ROLES", Arrays.asList("USER", "ADMIN"));
        return "/add-new-user";

    }

    @RequestMapping(value = "/admin/add", method = {RequestMethod.POST, RequestMethod.GET})
    public String addUser(@ModelAttribute("user") User user, Model model,
                          @RequestParam(value = "roles") String [] roles) {
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
            model.addAttribute("ROLES", Arrays.asList("USER", "ADMIN"));

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
        model.addAttribute("ROLES", Arrays.asList("USER", "ADMIN"));
        return "/edit-user";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("ROLES", Arrays.asList("USER", "ADMIN"));
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

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }*/
}
