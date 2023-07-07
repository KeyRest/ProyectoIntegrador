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
import Entidades.Occasions;
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




public class OccasionsJpaController implements Serializable {

    public OccasionsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Occasions occasions) {
        if (occasions.getRecipesCollection() == null) {
            occasions.setRecipesCollection(new ArrayList<Recipe>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Recipe> attachedRecipesCollection = new ArrayList<Recipe>();
            for (Recipe recipesCollectionRecipesToAttach : occasions.getRecipesCollection()) {
                recipesCollectionRecipesToAttach = em.getReference(recipesCollectionRecipesToAttach.getClass(), recipesCollectionRecipesToAttach.getId());
                attachedRecipesCollection.add(recipesCollectionRecipesToAttach);
            }
            occasions.setRecipesCollection(attachedRecipesCollection);
            em.persist(occasions);
            for (Recipe recipesCollectionRecipes : occasions.getRecipesCollection()) {
                recipesCollectionRecipes.getOccasionsCollection().add(occasions);
                recipesCollectionRecipes = em.merge(recipesCollectionRecipes);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Occasions occasions) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Occasions persistentOccasions = em.find(Occasions.class, occasions.getId());
            Collection<Recipe> recipesCollectionOld = persistentOccasions.getRecipesCollection();
            Collection<Recipe> recipesCollectionNew = occasions.getRecipesCollection();
            Collection<Recipe> attachedRecipesCollectionNew = new ArrayList<Recipe>();
            for (Recipe recipesCollectionNewRecipesToAttach : recipesCollectionNew) {
                recipesCollectionNewRecipesToAttach = em.getReference(recipesCollectionNewRecipesToAttach.getClass(), recipesCollectionNewRecipesToAttach.getId());
                attachedRecipesCollectionNew.add(recipesCollectionNewRecipesToAttach);
            }
            recipesCollectionNew = attachedRecipesCollectionNew;
            occasions.setRecipesCollection(recipesCollectionNew);
            occasions = em.merge(occasions);
            for (Recipe recipesCollectionOldRecipes : recipesCollectionOld) {
                if (!recipesCollectionNew.contains(recipesCollectionOldRecipes)) {
                    recipesCollectionOldRecipes.getOccasionsCollection().remove(occasions);
                    recipesCollectionOldRecipes = em.merge(recipesCollectionOldRecipes);
                }
            }
            for (Recipe recipesCollectionNewRecipes : recipesCollectionNew) {
                if (!recipesCollectionOld.contains(recipesCollectionNewRecipes)) {
                    recipesCollectionNewRecipes.getOccasionsCollection().add(occasions);
                    recipesCollectionNewRecipes = em.merge(recipesCollectionNewRecipes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = occasions.getId();
                if (findOccasions(id) == null) {
                    throw new NonexistentEntityException("The occasions with id " + id + " no longer exists.");
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
            Occasions occasions;
            try {
                occasions = em.getReference(Occasions.class, id);
                occasions.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The occasions with id " + id + " no longer exists.", enfe);
            }
            Collection<Recipe> recipesCollection = occasions.getRecipesCollection();
            for (Recipe recipesCollectionRecipes : recipesCollection) {
                recipesCollectionRecipes.getOccasionsCollection().remove(occasions);
                recipesCollectionRecipes = em.merge(recipesCollectionRecipes);
            }
            em.remove(occasions);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Occasions> findOccasionsEntities() {
        return findOccasionsEntities(true, -1, -1);
    }

    public List<Occasions> findOccasionsEntities(int maxResults, int firstResult) {
        return findOccasionsEntities(false, maxResults, firstResult);
    }

    private List<Occasions> findOccasionsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Occasions.class));
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

    public Occasions findOccasions(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Occasions.class, id);
        } finally {
            em.close();
        }
    }

    public int getOccasionsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Occasions> rt = cq.from(Occasions.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
