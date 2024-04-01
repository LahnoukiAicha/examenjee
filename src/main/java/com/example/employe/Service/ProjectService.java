package com.example.employe.Service;


import com.example.employe.Dao.ProjectDaoImpl;
import com.example.employe.Model.ProjectEntity;

import java.util.List;

public class ProjectService {

    private static final ProjectDaoImpl projectDao = new ProjectDaoImpl();

    public static List<ProjectEntity> getProjectsListService() {
        return projectDao.getAllProjects();
    }

    public static void addProjectService(ProjectEntity projectEntity) {
        projectDao.createNewProject(projectEntity);
    }

    public static void deleteProjectService(int projectId) {
        projectDao.deleteProject(projectId);
    }
}
