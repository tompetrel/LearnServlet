/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.javashoponline.controllers;

import com.webapp.javashoponline.controllers.exceptions.IllegalOrphanException;
import com.webapp.javashoponline.controllers.exceptions.NonexistentEntityException;
import com.webapp.javashoponline.entities.Category;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.webapp.javashoponline.entities.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author TAI
 */
public class CategoriesJpaController implements Serializable {

    public CategoriesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Category categories) {
        if (categories.getProductsCollection() == null) {
            categories.setProductsCollection(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Product> attachedProductsCollection = new ArrayList<Product>();
            for (Product productsCollectionProductsToAttach : categories.getProductsCollection()) {
                productsCollectionProductsToAttach = em.getReference(productsCollectionProductsToAttach.getClass(), productsCollectionProductsToAttach.getProductId());
                attachedProductsCollection.add(productsCollectionProductsToAttach);
            }
            categories.setProductsCollection(attachedProductsCollection);
            em.persist(categories);
            for (Product productsCollectionProducts : categories.getProductsCollection()) {
                Category oldCategoryIdOfProductsCollectionProducts = productsCollectionProducts.getCategoryId();
                productsCollectionProducts.setCategoryId(categories);
                productsCollectionProducts = em.merge(productsCollectionProducts);
                if (oldCategoryIdOfProductsCollectionProducts != null) {
                    oldCategoryIdOfProductsCollectionProducts.getProductsCollection().remove(productsCollectionProducts);
                    oldCategoryIdOfProductsCollectionProducts = em.merge(oldCategoryIdOfProductsCollectionProducts);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Category categories) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category persistentCategories = em.find(Category.class, categories.getCategoryId());
            Collection<Product> productsCollectionOld = persistentCategories.getProductsCollection();
            Collection<Product> productsCollectionNew = categories.getProductsCollection();
            List<String> illegalOrphanMessages = null;
            for (Product productsCollectionOldProducts : productsCollectionOld) {
                if (!productsCollectionNew.contains(productsCollectionOldProducts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Products " + productsCollectionOldProducts + " since its categoryId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Product> attachedProductsCollectionNew = new ArrayList<Product>();
            for (Product productsCollectionNewProductsToAttach : productsCollectionNew) {
                productsCollectionNewProductsToAttach = em.getReference(productsCollectionNewProductsToAttach.getClass(), productsCollectionNewProductsToAttach.getProductId());
                attachedProductsCollectionNew.add(productsCollectionNewProductsToAttach);
            }
            productsCollectionNew = attachedProductsCollectionNew;
            categories.setProductsCollection(productsCollectionNew);
            categories = em.merge(categories);
            for (Product productsCollectionNewProducts : productsCollectionNew) {
                if (!productsCollectionOld.contains(productsCollectionNewProducts)) {
                    Category oldCategoryIdOfProductsCollectionNewProducts = productsCollectionNewProducts.getCategoryId();
                    productsCollectionNewProducts.setCategoryId(categories);
                    productsCollectionNewProducts = em.merge(productsCollectionNewProducts);
                    if (oldCategoryIdOfProductsCollectionNewProducts != null && !oldCategoryIdOfProductsCollectionNewProducts.equals(categories)) {
                        oldCategoryIdOfProductsCollectionNewProducts.getProductsCollection().remove(productsCollectionNewProducts);
                        oldCategoryIdOfProductsCollectionNewProducts = em.merge(oldCategoryIdOfProductsCollectionNewProducts);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categories.getCategoryId();
                if (findCategories(id) == null) {
                    throw new NonexistentEntityException("The categories with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category categories;
            try {
                categories = em.getReference(Category.class, id);
                categories.getCategoryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categories with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Product> productsCollectionOrphanCheck = categories.getProductsCollection();
            for (Product productsCollectionOrphanCheckProducts : productsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categories (" + categories + ") cannot be destroyed since the Products " + productsCollectionOrphanCheckProducts + " in its productsCollection field has a non-nullable categoryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(categories);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Category> findCategoriesEntities() {
        return findCategoriesEntities(true, -1, -1);
    }

    public List<Category> findCategoriesEntities(int maxResults, int firstResult) {
        return findCategoriesEntities(false, maxResults, firstResult);
    }

    private List<Category> findCategoriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Category.class));
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

    public Category findCategories(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Category> rt = cq.from(Category.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
