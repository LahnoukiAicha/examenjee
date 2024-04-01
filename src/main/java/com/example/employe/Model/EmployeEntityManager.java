package com.example.employe.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "employe")
public class EmployeEntityManager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "skills", nullable = true)
    private String skills;

    @ManyToMany
    @JoinTable(
            name = "employe_project",
            joinColumns = @JoinColumn(name = "employe_id"),
            inverseJoinColumns =
                    @JoinColumn(name = "project_id")
    )
    private List<ProjectEntity> projects;

    public EmployeEntityManager() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }
}
