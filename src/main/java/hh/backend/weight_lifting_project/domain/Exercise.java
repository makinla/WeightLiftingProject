package hh.backend.weight_lifting_project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Exercise {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long exerciseId;

    @NotNull
    @Size(min=3, max=100, message = "The exercise name must be between 3 and 100 characters")
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exercise")
    @JsonIgnoreProperties ("exercise")
    private List<Result> results;

    @ManyToOne
    @JsonIgnoreProperties ("exercises")
    @JoinColumn (name = "categoryId")
    private Category category;

    public Exercise() {}

    public Exercise(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
    }
   
    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    } 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Exercise [exerciseId=" + exerciseId + ", name=" + name + ", category=" + category + "]";
    } 

}
