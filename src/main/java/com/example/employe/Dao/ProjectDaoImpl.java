package com.example.employe.Dao;

import com.example.employe.Model.ProjectEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    private EntityManager entityManager;

    public ProjectDaoImpl() {
        this.entityManager = entityManager;
    }

    public List<ProjectEntity> getAllProjects() {
        Query query = entityManager.createQuery("SELECT p FROM ProjectEntity p");
        return query.getResultList();
    }

    public void createNewProject(ProjectEntity projectEntity) {
        entityManager.getTransaction().begin();
        entityManager.persist(projectEntity);
        entityManager.getTransaction().commit();
    }

    public void deleteProject(int projectId) {
        ProjectEntity projectEntity = entityManager.find(ProjectEntity.class, projectId);
        if (projectEntity != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(projectEntity);
            entityManager.getTransaction().commit();
        }
    }
}
