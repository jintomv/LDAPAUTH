package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LDAPController {

	@GetMapping("/login")
    public String login() {
        return "signin";
    }//
    
    @RequestMapping(value = "/home")
    public String home() {
        return "index";
    }
    
    @RequestMapping(value = "/")
    public String home1() {
        return "index";
    }
}
