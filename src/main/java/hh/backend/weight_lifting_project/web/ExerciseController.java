package hh.backend.weight_lifting_project.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.weight_lifting_project.domain.CategoryRepository;
import hh.backend.weight_lifting_project.domain.Exercise;
import hh.backend.weight_lifting_project.domain.ExerciseRepository;
import jakarta.validation.Valid;

@Controller
public class ExerciseController {

    private ExerciseRepository repository;
    private CategoryRepository crepository;

    public ExerciseController(ExerciseRepository repository, CategoryRepository crepository) {
        this.repository = repository;
        this.crepository = crepository;
    }

    @GetMapping("/exerciselist")
    public String showExercises(Model model) {
        model.addAttribute("exercises", repository.findAll());
        return "exerciselist";
    }

    @GetMapping("/addexerc")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addExercise(Model model) {
        model.addAttribute("exercise", new Exercise());
        model.addAttribute("categories", crepository.findAll());
        return "addexercise";
    }

    @PostMapping("/saveexercise")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveExercise(@Valid @ModelAttribute("exercise") Exercise exercise, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", crepository.findAll());
            return "addexercise";
        } else if (repository.existsByName(exercise.getName())) {
            bindingResult.rejectValue("name", "duplicate", "An exercise with this name already exists.");
            model.addAttribute("categories", crepository.findAll());
            return "addexercise";
        } else {
            repository.save(exercise);
            return "redirect:exerciselist";
        }
    }

    @GetMapping("/deleteexerc/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteResult(@PathVariable("id") Long exerciseId, Model model) {
        repository.deleteById(exerciseId);
        return "redirect:../exerciselist";
    }

}
