package com.example.employe.Service;

import com.example.employe.Dao.EmployeDaoImpl;
import com.example.employe.Model.EmployeEntityManager;

import java.util.List;

public class EmployeService {

    private final EmployeDaoImpl EmployeDao = new EmployeDaoImpl();

    public static List getEmployesListService() {
        return EmployeDaoImpl.getAllEmployeDetails();
    }


    public static String addEmployeService(EmployeEntityManager employeEntityManager) {
        EmployeDaoImpl.createNewEmploye(employeEntityManager.getName(),employeEntityManager.getEmail(),employeEntityManager.getSkills());
        return null;
    }

    public static String deleteEmployeService(int id) {
        return  EmployeDaoImpl.deleteEmployeDetails(id);
    }
}
