/*
*Keiron Garro M
*C23212
*UCR
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controladores.DB;

import Controladores.DB.exceptions.NonexistentEntityException;
import Entidades.Categories;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Recipe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class CategoriesJpaController implements Serializable {

    public CategoriesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categories categories) {
        if (categories.getRecipesCollection() == null) {
            categories.setRecipesCollection(new ArrayList<Recipe>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Recipe> attachedRecipesCollection = new ArrayList<Recipe>();
            for (Recipe recipesCollectionRecipesToAttach : categories.getRecipesCollection()) {
                recipesCollectionRecipesToAttach = em.getReference(recipesCollectionRecipesToAttach.getClass(), recipesCollectionRecipesToAttach.getId());
                attachedRecipesCollection.add(recipesCollectionRecipesToAttach);
            }
            categories.setRecipesCollection(attachedRecipesCollection);
            em.persist(categories);
            for (Recipe recipesCollectionRecipes : categories.getRecipesCollection()) {
                recipesCollectionRecipes.getCategoriesCollection().add(categories);
                recipesCollectionRecipes = em.merge(recipesCollectionRecipes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categories categories) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categories persistentCategories = em.find(Categories.class, categories.getId());
            Collection<Recipe> recipesCollectionOld = persistentCategories.getRecipesCollection();
            Collection<Recipe> recipesCollectionNew = categories.getRecipesCollection();
            Collection<Recipe> attachedRecipesCollectionNew = new ArrayList<Recipe>();
            for (Recipe recipesCollectionNewRecipesToAttach : recipesCollectionNew) {
                recipesCollectionNewRecipesToAttach = em.getReference(recipesCollectionNewRecipesToAttach.getClass(), recipesCollectionNewRecipesToAttach.getId());
                attachedRecipesCollectionNew.add(recipesCollectionNewRecipesToAttach);
            }
            recipesCollectionNew = attachedRecipesCollectionNew;
            categories.setRecipesCollection(recipesCollectionNew);
            categories = em.merge(categories);
            for (Recipe recipesCollectionOldRecipes : recipesCollectionOld) {
                if (!recipesCollectionNew.contains(recipesCollectionOldRecipes)) {
                    recipesCollectionOldRecipes.getCategoriesCollection().remove(categories);
                    recipesCollectionOldRecipes = em.merge(recipesCollectionOldRecipes);
                }
            }
            for (Recipe recipesCollectionNewRecipes : recipesCollectionNew) {
                if (!recipesCollectionOld.contains(recipesCollectionNewRecipes)) {
                    recipesCollectionNewRecipes.getCategoriesCollection().add(categories);
                    recipesCollectionNewRecipes = em.merge(recipesCollectionNewRecipes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categories.getId();
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categories categories;
            try {
                categories = em.getReference(Categories.class, id);
                categories.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categories with id " + id + " no longer exists.", enfe);
            }
            Collection<Recipe> recipesCollection = categories.getRecipesCollection();
            for (Recipe recipesCollectionRecipes : recipesCollection) {
                recipesCollectionRecipes.getCategoriesCollection().remove(categories);
                recipesCollectionRecipes = em.merge(recipesCollectionRecipes);
            }
            em.remove(categories);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categories> findCategoriesEntities() {
        return findCategoriesEntities(true, -1, -1);
    }

    public List<Categories> findCategoriesEntities(int maxResults, int firstResult) {
        return findCategoriesEntities(false, maxResults, firstResult);
    }

    private List<Categories> findCategoriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categories.class));
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

    public Categories findCategories(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categories.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categories> rt = cq.from(Categories.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
