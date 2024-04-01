package com.example.employe.Bean;

import com.example.employe.Model.EmployeEntityManager;
import com.example.employe.Model.ProjectEntity;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AffectionBean implements Serializable {

    private int selectedEmployeeId;
    private int selectedProjectId;
    private List<EmployeEntityManager> availableEmployees;
    private List<ProjectEntity> availableProjects;
    private int contributionPercentage;

    public int getContributionPercentage() {
        return contributionPercentage;
    }

    public void setContributionPercentage(int contributionPercentage) {
        this.contributionPercentage = contributionPercentage;
    }
    public List<SelectItem> getContributionOptions() {
        List<SelectItem> options = new ArrayList<>();
        for (int i = 10; i <= 100; i += 10) {
            options.add(new SelectItem(i, i + "%"));
        }
        return options;
    }

    @PostConstruct
    public void init() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("employe_persist").createEntityManager();
        Query employeeQuery = entityManager.createQuery("SELECT e FROM EmployeEntityManager e");
        Query projectQuery = entityManager.createQuery("SELECT p FROM ProjectEntity p");
        availableEmployees = employeeQuery.getResultList();
        availableProjects = projectQuery.getResultList();
        entityManager.close();
    }

    public void associateEmployeeWithProject() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("employe_persist").createEntityManager();
        try {
            EmployeEntityManager employee = entityManager.find(EmployeEntityManager.class, selectedEmployeeId);
            ProjectEntity project = entityManager.find(ProjectEntity.class, selectedProjectId);
            if (employee != null && project != null) {
                entityManager.getTransaction().begin();
                List<ProjectEntity> projects = employee.getProjects();
                if (projects == null) {
                    projects = new ArrayList<>();
                }
                projects.add(project);
                employee.setProjects(projects);
                entityManager.getTransaction().commit();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Employee associated with project."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Employee or project not found."));
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to associate employee with project."));
        } finally {
            entityManager.close();
        }
    }


    public int getSelectedEmployeeId() {
        return selectedEmployeeId;
    }

    public void setSelectedEmployeeId(int selectedEmployeeId) {
        this.selectedEmployeeId = selectedEmployeeId;
    }

    public int getSelectedProjectId() {
        return selectedProjectId;
    }

    public void setSelectedProjectId(int selectedProjectId) {
        this.selectedProjectId = selectedProjectId;
    }

    public List<EmployeEntityManager> getAvailableEmployees() {
        return availableEmployees;
    }

    public List<ProjectEntity> getAvailableProjects() {
        return availableProjects;
    }
}
