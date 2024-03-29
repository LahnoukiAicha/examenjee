package com.example.employe.Bean;


import com.example.employe.Model.EmployeEntityManager;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;
import com.example.employe.Service.EmployeService;

@Named
@RequestScoped
public class EmployeBean {
    private int Id;
    private String name;
    private String email;
    private String skills;
    private EmployeEntityManager employeEntityManager;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
    public List EmployeListFromDb() {
        return EmployeService.getEmployesListService();
    }

    public String addNewEmploye() {
        return EmployeService.addEmployeService(employeEntityManager);
    }

    public String deleteEmploye(int EmployeId) {
        return EmployeService.deleteEmployeService(EmployeId);
    }

}
