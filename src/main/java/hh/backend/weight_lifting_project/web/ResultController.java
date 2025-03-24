package hh.backend.weight_lifting_project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.backend.weight_lifting_project.domain.CategoryRepository;
import hh.backend.weight_lifting_project.domain.ResultRepository;

@Controller
public class ResultController {

    @Autowired
    private ResultRepository repository; // Replace with constructor injection

    @Autowired
    private CategoryRepository crepository; // Replace with constructor injection

    @GetMapping ("/resultlist")
    public String showResultList(Model model) {
        model.addAttribute("results", repository.findAll());
        return "resultlist";
    }

}
