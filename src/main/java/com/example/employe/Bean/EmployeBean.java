package com.example.employe.Bean;

import com.example.employe.Model.EmployeEntityManager;
import com.example.employe.Service.EmployeService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class EmployeBean {
    private int id; // Changed from Id to id to follow Java naming conventions
    private String name;
    private String email;
    private String skills;

    // Getters and setters

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

    // Methods for interacting with EmployeService

    public List<EmployeEntityManager> getEmployeListFromDb() {
        return EmployeService.getEmployesListService();
    }

    public void addNewEmploye() {
        if (name != null && !name.isEmpty() && email != null && !email.isEmpty() && skills != null && !skills.isEmpty()) {
            EmployeEntityManager employeEntityManager = new EmployeEntityManager();
            employeEntityManager.setName(name);
            employeEntityManager.setEmail(email);
            employeEntityManager.setSkills(skills);
            EmployeService.addEmployeService(employeEntityManager);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please provide valid name, email, and skills."));
        }
    }

    public void deleteEmploye(int employeId) {
        EmployeService.deleteEmployeService(employeId);
    }
}
