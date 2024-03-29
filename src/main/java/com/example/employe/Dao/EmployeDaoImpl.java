package com.example.employe.Dao;

import com.example.employe.Model.EmployeEntityManager;
import jakarta.persistence.*;

import java.util.List;

public class EmployeDaoImpl{

    private static final String PERSISTENCE_UNIT_NAME = "employe_persist";
    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();

    @SuppressWarnings("unchecked")
    public static List getAllEmployeDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM EmployeEntityManager s");
        List employeList = queryObj.getResultList();
        if (employeList != null && employeList.size() > 0) {
            return employeList;
        } else {
            return null;
        }
    }

    public static void createNewEmploye(String name, String email, String skills) {
        if(!transactionObj.isActive()) {
            transactionObj.begin();
        }

        EmployeEntityManager newEmployeObj = new EmployeEntityManager();
        newEmployeObj.setId(getMaxEmployeId());
        newEmployeObj.setName(name);
        newEmployeObj.setEmail(email);
        newEmployeObj.setName(skills);
        entityMgrObj.persist(newEmployeObj);
        transactionObj.commit();
    }

    public static String deleteEmployeDetails(int EmployeId) {
        if (!transactionObj.isActive()) {
            transactionObj.begin();
        }

        EmployeEntityManager deleteEmployeObj = new EmployeEntityManager();
        if(isEmployeIdPresent(EmployeId)) {
            deleteEmployeObj.setId(EmployeId);
            entityMgrObj.remove(entityMgrObj.merge(deleteEmployeObj));
        }
        transactionObj.commit();
        return "EmployesList.xhtml?faces-redirect=true";
    }


    private static int getMaxEmployeId() {
        int maxEmployeId = 1;
        Query queryObj = entityMgrObj.createQuery("SELECT MAX(s.id)+1 FROM EmployeEntityManager s");
        if(queryObj.getSingleResult() != null) {
            maxEmployeId = (Integer) queryObj.getSingleResult();
        }
        return maxEmployeId;
    }

    private static boolean isEmployeIdPresent(int EmployeId) {
        boolean idResult = false;
        Query queryObj = entityMgrObj.createQuery("SELECT s FROM EmployeEntityManager s WHERE s.id = :id");
        queryObj.setParameter("id", EmployeId);
        EmployeEntityManager selectedEmployeId = (EmployeEntityManager) queryObj.getSingleResult();
        if(selectedEmployeId != null) {
            idResult = true;
        }
        return idResult;
    }
}

