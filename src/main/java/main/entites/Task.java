package main.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import main.entites.constants.State;

import javax.persistence.*;

/**
 * Created by Michal
 * 2015-01-23.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer estimate;

    @Column
    private State state;

    @Column
    private Long storyId;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public Task withId(final Long id) {
        this.id = id;
        return this;
    }

    public Task withName(final String name) {
        this.name = name;
        return this;
    }

    public Task withEstimate(final Integer estimate) {
        this.estimate = estimate;
        return this;
    }

    public Task withState(final State state) {
        this.state = state;
        return this;
    }

    public Task withStoryId(final Long storyId) {
        this.storyId = storyId;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", estimate=" + estimate +
                ", state=" + state +
                ", storyId=" + storyId +
                '}';
    }
}
