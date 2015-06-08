package main.entites;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Michal
 * 2015-06-08.
 */
@Entity
public class Privilege {
    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @ManyToMany
    List<Role> roles;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
