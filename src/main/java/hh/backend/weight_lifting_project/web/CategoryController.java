package hh.backend.weight_lifting_project.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.weight_lifting_project.domain.Category;
import hh.backend.weight_lifting_project.domain.CategoryRepository;
import jakarta.validation.Valid;

@Controller
public class CategoryController {

    private CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/categorylist")
    public String showCategories(Model model) {
        model.addAttribute("categories", repository.findAll());
        return "categorylist";
    }

    @GetMapping("/addcategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }
    
    @PostMapping("/savecategory")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addcategory";
        } else if (repository.existsByCategoryName(category.getCategoryName())) {
            bindingResult.rejectValue("categoryName", "duplicate", "A category with this name already exists.");
            return "addcategory";
        } else {
        repository.save(category);
        return "redirect:categorylist";
        }
    }

    @GetMapping ("/deletecategory/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteResult(@PathVariable("id") Long categoryId, Model model) {
        repository.deleteById(categoryId);
        return "redirect:../categorylist";
    }



}
