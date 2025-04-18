package hh.backend.weight_lifting_project.web;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.backend.weight_lifting_project.domain.AppUser;
import hh.backend.weight_lifting_project.domain.AppUserRepository;
import hh.backend.weight_lifting_project.domain.Category;
import hh.backend.weight_lifting_project.domain.CategoryRepository;
import hh.backend.weight_lifting_project.domain.Exercise;
import hh.backend.weight_lifting_project.domain.ExerciseRepository;
import hh.backend.weight_lifting_project.domain.Result;
import hh.backend.weight_lifting_project.domain.ResultRepository;
import jakarta.validation.Valid;

@Controller
public class ResultController {

    private ResultRepository repository;
    private ExerciseRepository erepository;
    private CategoryRepository crepository;
    private AppUserRepository urepository;

    public ResultController(ResultRepository repository, CategoryRepository crepository,
            ExerciseRepository erepository, AppUserRepository urepository) {
        this.repository = repository;
        this.crepository = crepository;
        this.erepository = erepository;
        this.urepository = urepository;
    }

    @GetMapping("/resultlist")
    public String showResultList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        List<Result> userResults = repository.findByAppUser_Username(username);
        model.addAttribute("results", userResults);
        return "resultlist";
    }

    @GetMapping("/delete/{id}")
    public String deleteResult(@PathVariable("id") Long resultId, Model model) {
        repository.deleteById(resultId);
        return "redirect:../resultlist";
    }

    @GetMapping("/edit/{id}")
    public String editResult(@PathVariable("id") Long resultId, Model model) {
        Result result = repository.findById(resultId).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
        Long selectedCategoryId = result.getExercise().getCategory().getCategoryId();

        model.addAttribute("result", result);
        model.addAttribute("categories", crepository.findAll());
        model.addAttribute("selectedCategory", selectedCategoryId);
        model.addAttribute("exercises", erepository.findByCategory_CategoryId(selectedCategoryId));
        return "editresult";
    }

    @PostMapping("/edited")
    public String saveEditedResult(@Valid @ModelAttribute("result") Result result, BindingResult bindingResult, @RequestParam("selectedCategory") Long selectedCategoryId,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", crepository.findAll());
            model.addAttribute("selectedCategory", selectedCategoryId);
            model.addAttribute("exercises", erepository.findByCategory_CategoryId(selectedCategoryId));
            return "editresult";
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            AppUser user = urepository.findByUsername(username);
            result.setAppUser(user);
            
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

    @GetMapping("/getExercisesByCategory")
    @ResponseBody
    public List<Exercise> getExercisesByCategory(@RequestParam ("categoryId") Long id) {
        Category category = crepository.findById(id).orElse(null);
        if (category != null) {
            return category.getExercises();
        }
        return Collections.emptyList();
    }

    @PostMapping("/save")
    public String saveResult(@Valid @ModelAttribute("result") Result result, BindingResult bindingResult, @RequestParam(value = "selectedCategory", required = false) Long selectedCategoryId, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", crepository.findAll());
            model.addAttribute("selectedCategory", selectedCategoryId);
            model.addAttribute("exercises", erepository.findByCategory_CategoryId(selectedCategoryId));
            return "addresult";
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            AppUser user = urepository.findByUsername(username);
            result.setAppUser(user);

            repository.save(result);
            return "redirect:resultlist";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        
        binder.setBindingErrorProcessor(new org.springframework.validation.DefaultBindingErrorProcessor() {
            @Override
            public void processPropertyAccessException(org.springframework.beans.PropertyAccessException ex,
                                                       BindingResult bindingResult) {
                bindingResult.rejectValue(ex.getPropertyName(), "typeMismatch", "The input type is not valid.");
            }
        });
    }

}
