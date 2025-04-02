package hh.backend.weight_lifting_project.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.backend.weight_lifting_project.domain.Result;
import hh.backend.weight_lifting_project.domain.ResultRepository;

@CrossOrigin
@RestController
public class ResultRestController {

    private final ResultRepository resultRepository;

    public ResultRestController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @GetMapping("/results")
    public @ResponseBody List<Result> getAllResultsRest() {
        return (List<Result>) resultRepository.findAll();
    }

    @GetMapping("/results/{id}")
    public @ResponseBody Optional<Result> getResultByIdRest(@PathVariable (name = "id") long resultId) {
        return resultRepository.findById(resultId);
    }

    @PostMapping("results")
    public @ResponseBody Result addNewResultRest(@RequestBody Result newResult) {
        return resultRepository.save(newResult);
    }

}
