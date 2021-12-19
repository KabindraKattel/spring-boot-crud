package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showAllUsers(Model model){
        final List<User> users = userService.getAll();
        model.addAttribute("listAllUsers",users);
        return "users";
    }
    
    @GetMapping("/users/new")
    public String addNewUser(Model model){
        final User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("pageTitle","Add New User");
        return "users_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes re){
        userService.save(user);
        re.addFlashAttribute("message","User has been saved successfully");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes re){
        try {
            final User user = userService.get(id);
            model.addAttribute("pageTitle","Edit User (ID: "+id+")");
            model.addAttribute("user",user);
            return "users_form";
        } catch (UserNotFoundException e) {
            re.addFlashAttribute("message",e.getMessage());
            return "redirect: /users";
        }

    }
    
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id,RedirectAttributes re){
        try {
            userService.delete(id);
            re.addFlashAttribute("message","User with ID: "+id+" has been deleted successfully");
        } catch (UserNotFoundException e) {
            re.addFlashAttribute("message",e.getMessage());
        }
        return "redirect: /users";
    }
}
