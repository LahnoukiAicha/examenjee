package com.example.employe.Dao;

import com.example.employe.Model.EmployeEntityManager;
import jakarta.persistence.*;

import java.util.List;

public class EmployeDaoImpl {

    private static final String PERSISTENCE_UNIT_NAME = "employe_persist";
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static List<EmployeEntityManager> getAllEmployeDetails() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<EmployeEntityManager> query = entityManager.createQuery("SELECT s FROM EmployeEntityManager s", EmployeEntityManager.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public static void createNewEmploye(String name, String email, String skills) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            EmployeEntityManager newEmployeObj = new EmployeEntityManager();
            newEmployeObj.setId(getMaxEmployeId(entityManager));
            newEmployeObj.setName(name);
            newEmployeObj.setEmail(email);
            newEmployeObj.setSkills(skills);
            entityManager.persist(newEmployeObj);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle or log the exception appropriately
        } finally {
            entityManager.close();
        }
    }

    public static String deleteEmployeDetails(int employeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            EmployeEntityManager deleteEmployeObj = entityManager.find(EmployeEntityManager.class, employeId);
            if (deleteEmployeObj != null) {
                entityManager.remove(deleteEmployeObj);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle or log the exception appropriately
        } finally {
            entityManager.close();
        }
        return "EmployesList.xhtml?faces-redirect=true";
    }

    private static int getMaxEmployeId(EntityManager entityManager) {
        Integer maxEmployeId = entityManager.createQuery("SELECT MAX(s.id) FROM EmployeEntityManager s", Integer.class).getSingleResult();
        return maxEmployeId != null ? maxEmployeId + 1 : 1;
    }
}
