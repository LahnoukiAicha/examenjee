package com.example.employe.Dao;

import java.util.List;

public interface EmployeDao {
    public  void createNewEmploye(String name, String email, String skills);
    public  List getAllEmployeDetails();
    public  String deleteEmployeDetails(int EmployeId);
}
