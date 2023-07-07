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
import Entidades.MeasurementUnits;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.RecipesHasIngredients;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class MeasurementUnitsJpaController implements Serializable {

    public MeasurementUnitsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MeasurementUnits measurementUnits) {
        if (measurementUnits.getRecipesHasIngredientsCollection() == null) {
            measurementUnits.setRecipesHasIngredientsCollection(new ArrayList<RecipesHasIngredients>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<RecipesHasIngredients> attachedRecipesHasIngredientsCollection = new ArrayList<RecipesHasIngredients>();
            for (RecipesHasIngredients recipesHasIngredientsCollectionRecipesHasIngredientsToAttach : measurementUnits.getRecipesHasIngredientsCollection()) {
                recipesHasIngredientsCollectionRecipesHasIngredientsToAttach = em.getReference(recipesHasIngredientsCollectionRecipesHasIngredientsToAttach.getClass(), recipesHasIngredientsCollectionRecipesHasIngredientsToAttach.getRecipesHasIngredientsPK());
                attachedRecipesHasIngredientsCollection.add(recipesHasIngredientsCollectionRecipesHasIngredientsToAttach);
            }
            measurementUnits.setRecipesHasIngredientsCollection(attachedRecipesHasIngredientsCollection);
            em.persist(measurementUnits);
            for (RecipesHasIngredients recipesHasIngredientsCollectionRecipesHasIngredients : measurementUnits.getRecipesHasIngredientsCollection()) {
                MeasurementUnits oldMeasurementUnitsOfRecipesHasIngredientsCollectionRecipesHasIngredients = recipesHasIngredientsCollectionRecipesHasIngredients.getMeasurementUnits();
                recipesHasIngredientsCollectionRecipesHasIngredients.setMeasurementUnits(measurementUnits);
                recipesHasIngredientsCollectionRecipesHasIngredients = em.merge(recipesHasIngredientsCollectionRecipesHasIngredients);
                if (oldMeasurementUnitsOfRecipesHasIngredientsCollectionRecipesHasIngredients != null) {
                    oldMeasurementUnitsOfRecipesHasIngredientsCollectionRecipesHasIngredients.getRecipesHasIngredientsCollection().remove(recipesHasIngredientsCollectionRecipesHasIngredients);
                    oldMeasurementUnitsOfRecipesHasIngredientsCollectionRecipesHasIngredients = em.merge(oldMeasurementUnitsOfRecipesHasIngredientsCollectionRecipesHasIngredients);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MeasurementUnits measurementUnits) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MeasurementUnits persistentMeasurementUnits = em.find(MeasurementUnits.class, measurementUnits.getId());
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionOld = persistentMeasurementUnits.getRecipesHasIngredientsCollection();
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionNew = measurementUnits.getRecipesHasIngredientsCollection();
            List<String> illegalOrphanMessages = null;
            for (RecipesHasIngredients recipesHasIngredientsCollectionOldRecipesHasIngredients : recipesHasIngredientsCollectionOld) {
                if (!recipesHasIngredientsCollectionNew.contains(recipesHasIngredientsCollectionOldRecipesHasIngredients)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RecipesHasIngredients " + recipesHasIngredientsCollectionOldRecipesHasIngredients + " since its measurementUnits field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<RecipesHasIngredients> attachedRecipesHasIngredientsCollectionNew = new ArrayList<RecipesHasIngredients>();
            for (RecipesHasIngredients recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach : recipesHasIngredientsCollectionNew) {
                recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach = em.getReference(recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach.getClass(), recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach.getRecipesHasIngredientsPK());
                attachedRecipesHasIngredientsCollectionNew.add(recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach);
            }
            recipesHasIngredientsCollectionNew = attachedRecipesHasIngredientsCollectionNew;
            measurementUnits.setRecipesHasIngredientsCollection(recipesHasIngredientsCollectionNew);
            measurementUnits = em.merge(measurementUnits);
            for (RecipesHasIngredients recipesHasIngredientsCollectionNewRecipesHasIngredients : recipesHasIngredientsCollectionNew) {
                if (!recipesHasIngredientsCollectionOld.contains(recipesHasIngredientsCollectionNewRecipesHasIngredients)) {
                    MeasurementUnits oldMeasurementUnitsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients = recipesHasIngredientsCollectionNewRecipesHasIngredients.getMeasurementUnits();
                    recipesHasIngredientsCollectionNewRecipesHasIngredients.setMeasurementUnits(measurementUnits);
                    recipesHasIngredientsCollectionNewRecipesHasIngredients = em.merge(recipesHasIngredientsCollectionNewRecipesHasIngredients);
                    if (oldMeasurementUnitsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients != null && !oldMeasurementUnitsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients.equals(measurementUnits)) {
                        oldMeasurementUnitsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients.getRecipesHasIngredientsCollection().remove(recipesHasIngredientsCollectionNewRecipesHasIngredients);
                        oldMeasurementUnitsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients = em.merge(oldMeasurementUnitsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = measurementUnits.getId();
                if (findMeasurementUnits(id) == null) {
                    throw new NonexistentEntityException("The measurementUnits with id " + id + " no longer exists.");
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
            MeasurementUnits measurementUnits;
            try {
                measurementUnits = em.getReference(MeasurementUnits.class, id);
                measurementUnits.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The measurementUnits with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionOrphanCheck = measurementUnits.getRecipesHasIngredientsCollection();
            for (RecipesHasIngredients recipesHasIngredientsCollectionOrphanCheckRecipesHasIngredients : recipesHasIngredientsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MeasurementUnits (" + measurementUnits + ") cannot be destroyed since the RecipesHasIngredients " + recipesHasIngredientsCollectionOrphanCheckRecipesHasIngredients + " in its recipesHasIngredientsCollection field has a non-nullable measurementUnits field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(measurementUnits);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MeasurementUnits> findMeasurementUnitsEntities() {
        return findMeasurementUnitsEntities(true, -1, -1);
    }

    public List<MeasurementUnits> findMeasurementUnitsEntities(int maxResults, int firstResult) {
        return findMeasurementUnitsEntities(false, maxResults, firstResult);
    }

    private List<MeasurementUnits> findMeasurementUnitsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MeasurementUnits.class));
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

    public MeasurementUnits findMeasurementUnits(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MeasurementUnits.class, id);
        } finally {
            em.close();
        }
    }

    public int getMeasurementUnitsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MeasurementUnits> rt = cq.from(MeasurementUnits.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
