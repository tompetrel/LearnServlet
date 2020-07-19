/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.student.controller;

import com.softech.student.controller.exceptions.NonexistentEntityException;
import com.softech.student.entities.StudentsEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author TAI
 */
public class StudentsEntityJpaController implements Serializable {

    public StudentsEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentsEntity studentsEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(studentsEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StudentsEntity studentsEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            studentsEntity = em.merge(studentsEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = studentsEntity.getStudentId();
                if (findStudentsEntity(id) == null) {
                    throw new NonexistentEntityException("The studentsEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StudentsEntity studentsEntity;
            try {
                studentsEntity = em.getReference(StudentsEntity.class, id);
                studentsEntity.getStudentId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentsEntity with id " + id + " no longer exists.", enfe);
            }
            em.remove(studentsEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StudentsEntity> findStudentsEntityEntities() {
        return findStudentsEntityEntities(true, -1, -1);
    }

    public List<StudentsEntity> findStudentsEntityEntities(int maxResults, int firstResult) {
        return findStudentsEntityEntities(false, maxResults, firstResult);
    }

    private List<StudentsEntity> findStudentsEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentsEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public StudentsEntity findStudentsEntity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentsEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentsEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentsEntity> rt = cq.from(StudentsEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
