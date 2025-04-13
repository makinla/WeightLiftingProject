package hh.backend.weight_lifting_project.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity 
public class Result {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // Kirjain syötteellä tulee edelleen väärä virhekoodi
    @NotNull(message = "The weight field cannot be empty")
    @Min(value = 1, message = "The weight must be at least 1")
    @Digits(integer = 3, fraction = 2, message = "The weight must be a number less than 1000 with up to two decimal places.")
    private BigDecimal weight;

    @Min(value = 1, message = "The amount must be at least 1")
    private int amountOfReps;
    @Min(value = 1, message = "The amount must be at least 1")
    private int amountOfSets;

    @Min(value = 1, message = "The RPE must be between 1 and 10")
    @Max(value = 10, message = "The RPE must be between 1 and 10")
    private int rpe;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    
    @ManyToOne
    @JsonIgnoreProperties ("results")
    @JoinColumn(name = "exerciseId")
    @NotNull(message = "Category and exercise must be selected")
    private Exercise exercise;

    @ManyToOne 
    @JsonIgnoreProperties ("appUsers")
    @JoinColumn (name = "appUserId")
    private AppUser appUser;

    public Result() {}
    
    public Result(BigDecimal weight, int amountOfReps, int amountOfSets, int rpe, LocalDate date, Exercise exercise, AppUser appUser) {
        super();
        this.weight = weight;
        this.amountOfReps = amountOfReps;
        this.amountOfSets = amountOfSets;
        this.rpe = rpe;
        this.date = date;
        this.exercise = exercise;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public int getAmountOfReps() {
        return amountOfReps;
    }

    public void setAmountOfReps(int amountOfReps) {
        this.amountOfReps = amountOfReps;
    }

    public int getAmountOfSets() {
        return amountOfSets;
    }

    public void setAmountOfSets(int amountOfSets) {
        this.amountOfSets = amountOfSets;
    }

    public int getRpe() {
        return rpe;
    }

    public void setRpe(int rpe) {
        this.rpe = rpe;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "Result [id=" + id + ", weight=" + weight + ", amountOfSets=" + amountOfSets + ", rpe=" + rpe + ", date="
                + date + "]";
    }

}
