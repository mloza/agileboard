package main.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.entites.constants.State;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Michal
 * 2015-01-23.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Story {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer estimate;

    @Column
    private State state;

    @OneToMany(mappedBy = "storyId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEstimate() {
        return estimate;
    }

    public void setEstimate(Integer estimate) {
        this.estimate = estimate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Story withId(final Long id) {
        this.id = id;
        return this;
    }

    public Story withName(final String name) {
        this.name = name;
        return this;
    }

    public Story withEstimate(final Integer estimate) {
        this.estimate = estimate;
        return this;
    }

    public Story withTasks(final List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Story withState(final State state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", estimate=" + estimate +
                ", state=" + state +
                ", tasks=" + tasks +
                '}';
    }
}
