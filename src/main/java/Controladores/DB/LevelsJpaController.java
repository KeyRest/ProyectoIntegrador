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

import Controladores.DB.exceptions.IllegalOrphanException;
import Controladores.DB.exceptions.NonexistentEntityException;
import Entidades.Levels;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Recipes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class LevelsJpaController implements Serializable {

    public LevelsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Levels levels) {
        if (levels.getRecipesCollection() == null) {
            levels.setRecipesCollection(new ArrayList<Recipes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Recipes> attachedRecipesCollection = new ArrayList<Recipes>();
            for (Recipes recipesCollectionRecipesToAttach : levels.getRecipesCollection()) {
                recipesCollectionRecipesToAttach = em.getReference(recipesCollectionRecipesToAttach.getClass(), recipesCollectionRecipesToAttach.getId());
                attachedRecipesCollection.add(recipesCollectionRecipesToAttach);
            }
            levels.setRecipesCollection(attachedRecipesCollection);
            em.persist(levels);
            for (Recipes recipesCollectionRecipes : levels.getRecipesCollection()) {
                Levels oldLevelsIdOfRecipesCollectionRecipes = recipesCollectionRecipes.getLevelsId();
                recipesCollectionRecipes.setLevelsId(levels);
                recipesCollectionRecipes = em.merge(recipesCollectionRecipes);
                if (oldLevelsIdOfRecipesCollectionRecipes != null) {
                    oldLevelsIdOfRecipesCollectionRecipes.getRecipesCollection().remove(recipesCollectionRecipes);
                    oldLevelsIdOfRecipesCollectionRecipes = em.merge(oldLevelsIdOfRecipesCollectionRecipes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Levels levels) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Levels persistentLevels = em.find(Levels.class, levels.getId());
            Collection<Recipes> recipesCollectionOld = persistentLevels.getRecipesCollection();
            Collection<Recipes> recipesCollectionNew = levels.getRecipesCollection();
            List<String> illegalOrphanMessages = null;
            for (Recipes recipesCollectionOldRecipes : recipesCollectionOld) {
                if (!recipesCollectionNew.contains(recipesCollectionOldRecipes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Recipes " + recipesCollectionOldRecipes + " since its levelsId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Recipes> attachedRecipesCollectionNew = new ArrayList<Recipes>();
            for (Recipes recipesCollectionNewRecipesToAttach : recipesCollectionNew) {
                recipesCollectionNewRecipesToAttach = em.getReference(recipesCollectionNewRecipesToAttach.getClass(), recipesCollectionNewRecipesToAttach.getId());
                attachedRecipesCollectionNew.add(recipesCollectionNewRecipesToAttach);
            }
            recipesCollectionNew = attachedRecipesCollectionNew;
            levels.setRecipesCollection(recipesCollectionNew);
            levels = em.merge(levels);
            for (Recipes recipesCollectionNewRecipes : recipesCollectionNew) {
                if (!recipesCollectionOld.contains(recipesCollectionNewRecipes)) {
                    Levels oldLevelsIdOfRecipesCollectionNewRecipes = recipesCollectionNewRecipes.getLevelsId();
                    recipesCollectionNewRecipes.setLevelsId(levels);
                    recipesCollectionNewRecipes = em.merge(recipesCollectionNewRecipes);
                    if (oldLevelsIdOfRecipesCollectionNewRecipes != null && !oldLevelsIdOfRecipesCollectionNewRecipes.equals(levels)) {
                        oldLevelsIdOfRecipesCollectionNewRecipes.getRecipesCollection().remove(recipesCollectionNewRecipes);
                        oldLevelsIdOfRecipesCollectionNewRecipes = em.merge(oldLevelsIdOfRecipesCollectionNewRecipes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = levels.getId();
                if (findLevels(id) == null) {
                    throw new NonexistentEntityException("The levels with id " + id + " no longer exists.");
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
            Levels levels;
            try {
                levels = em.getReference(Levels.class, id);
                levels.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The levels with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Recipes> recipesCollectionOrphanCheck = levels.getRecipesCollection();
            for (Recipes recipesCollectionOrphanCheckRecipes : recipesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Levels (" + levels + ") cannot be destroyed since the Recipes " + recipesCollectionOrphanCheckRecipes + " in its recipesCollection field has a non-nullable levelsId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(levels);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Levels> findLevelsEntities() {
        return findLevelsEntities(true, -1, -1);
    }

    public List<Levels> findLevelsEntities(int maxResults, int firstResult) {
        return findLevelsEntities(false, maxResults, firstResult);
    }

    private List<Levels> findLevelsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Levels.class));
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

    public Levels findLevels(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Levels.class, id);
        } finally {
            em.close();
        }
    }

    public int getLevelsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Levels> rt = cq.from(Levels.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
