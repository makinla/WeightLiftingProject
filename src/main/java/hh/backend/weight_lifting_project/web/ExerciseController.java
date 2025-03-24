package hh.backend.weight_lifting_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExerciseController {

    @GetMapping("/index")
    public String showExercises() {
        return "index";
    }




}
