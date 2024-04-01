package com.example.employe.Dao;

import com.example.employe.Model.ProjectEntity;

import java.util.List;

public interface ProjectDao {
    List<ProjectEntity> getAllProjects();
    void createNewProject(ProjectEntity projectEntity);
    void deleteProject(int projectId);
}
