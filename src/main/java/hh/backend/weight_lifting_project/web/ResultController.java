package hh.backend.weight_lifting_project.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.weight_lifting_project.domain.CategoryRepository;
import hh.backend.weight_lifting_project.domain.ExerciseRepository;
import hh.backend.weight_lifting_project.domain.Result;
import hh.backend.weight_lifting_project.domain.ResultRepository;
import jakarta.validation.Valid;

@Controller
public class ResultController {

    private ResultRepository repository;
    private ExerciseRepository erepository;
    private CategoryRepository crepository;

    public ResultController(ResultRepository repository, CategoryRepository crepository, ExerciseRepository erepository) {
        this.repository = repository;
        this.crepository = crepository;
        this.erepository = erepository;
    }

    @GetMapping ("/resultlist")
    public String showResultList(Model model) {
        model.addAttribute("results", repository.findAll());
        return "resultlist";
    }

    @GetMapping ("/delete/{id}")
    public String deleteResult(@PathVariable("id") Long resultId, Model model) {
        repository.deleteById(resultId);
        return "redirect:../resultlist";
    }

    @GetMapping ("/edit/{id}")
    public String editResult(@PathVariable("id") Long resultId, Model model) {
        model.addAttribute("result", repository.findById(resultId));
        model.addAttribute("exercises", erepository.findAll());
        model.addAttribute("categories", crepository.findAll());
        return "editresult";
    }

    @PostMapping ("/edited")
    public String saveEditedResult(@Valid @ModelAttribute("result") Result result, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exercises", erepository.findAll());
            model.addAttribute("categories", crepository.findAll());
            return "editresult";
        } else {
        repository.save(result);
        return "redirect:resultlist";
        }
    }

    @GetMapping("/add")
    public String addResult(Model model) {
        model.addAttribute("result", new Result());
        model.addAttribute("exercises", erepository.findAll());
        model.addAttribute("categories", crepository.findAll());
        return "addresult";
    }

    @PostMapping ("/save")
    public String saveResult(@Valid @ModelAttribute("result") Result result, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("exercises", erepository.findAll());
            model.addAttribute("categories", crepository.findAll());
            return "addresult";
        } else {
        repository.save(result);
        return "redirect:resultlist";
        }
    }


}
