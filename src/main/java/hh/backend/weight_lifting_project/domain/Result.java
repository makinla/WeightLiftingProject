package hh.backend.weight_lifting_project.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Result {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private double weight;
    private int amountOfReps;
    private int amountOfSets;
    private int rpe;
    private LocalDate date;
    
    @ManyToOne
    @JsonIgnoreProperties ("results")
    @JoinColumn(name = "exerciseId")
    private Exercise exercise;

    // @ManyToOne 
    // @JsonIgnoreProperties ("appUsers")
    // @JoinColumn (name = "appUserId")
    // private AppUser appUser;

    public Result() {}
    
    public Result(double weight, int amountOfReps, int amountOfSets, int rpe, LocalDate date, Exercise exercise) {
        super();
        this.weight = weight;
        this.amountOfReps = amountOfReps;
        this.amountOfSets = amountOfSets;
        this.rpe = rpe;
        this.date = date;
        this.exercise = exercise;
        // this.appUser = appUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
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

    // public AppUser getAppUser() {
    //     return appUser;
    // }

    // public void setAppUser(AppUser appUser) {
    //     this.appUser = appUser;
    // }

    @Override
    public String toString() {
        return "Result [id=" + id + ", weight=" + weight + ", amountOfSets=" + amountOfSets + ", rpe=" + rpe + ", date="
                + date + "]";
    }

}
