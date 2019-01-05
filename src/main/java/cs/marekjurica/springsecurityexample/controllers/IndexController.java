package cs.marekjurica.springsecurityexample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cs.marekjurica.springsecurityexample.services.UserService;

@Controller
public class IndexController {
    
    UserService userService;
    
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {
        
        model.addAttribute("users", userService.findAll());
        return "index";
    }
}
