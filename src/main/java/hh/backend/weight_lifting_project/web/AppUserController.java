package hh.backend.weight_lifting_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppUserController {

    @GetMapping("/login")
    public String login() {	
        return "login";
    }

}
