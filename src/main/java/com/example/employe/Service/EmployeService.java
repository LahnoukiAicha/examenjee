package com.example.employe.Service;

import com.example.employe.Dao.EmployeDaoImpl;
import com.example.employe.Model.EmployeEntityManager;

import java.util.List;

public class EmployeService {

    private static final EmployeDaoImpl employeDao = new EmployeDaoImpl();

    public static List<EmployeEntityManager> getEmployesListService() {
        return employeDao.getAllEmployeDetails();
    }

    public static void addEmployeService(EmployeEntityManager employeEntityManager) {
        employeDao.createNewEmploye(employeEntityManager.getName(), employeEntityManager.getEmail(), employeEntityManager.getSkills());
    }

    public static void deleteEmployeService(int id) {
        employeDao.deleteEmployeDetails(id);
    }
}
