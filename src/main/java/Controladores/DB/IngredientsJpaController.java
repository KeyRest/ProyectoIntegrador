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
import Entidades.Ingredients;
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




public class IngredientsJpaController implements Serializable {

    public IngredientsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ingredients ingredients) {
        if (ingredients.getRecipesHasIngredientsCollection() == null) {
            ingredients.setRecipesHasIngredientsCollection(new ArrayList<RecipesHasIngredients>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<RecipesHasIngredients> attachedRecipesHasIngredientsCollection = new ArrayList<RecipesHasIngredients>();
            for (RecipesHasIngredients recipesHasIngredientsCollectionRecipesHasIngredientsToAttach : ingredients.getRecipesHasIngredientsCollection()) {
                recipesHasIngredientsCollectionRecipesHasIngredientsToAttach = em.getReference(recipesHasIngredientsCollectionRecipesHasIngredientsToAttach.getClass(), recipesHasIngredientsCollectionRecipesHasIngredientsToAttach.getRecipesHasIngredientsPK());
                attachedRecipesHasIngredientsCollection.add(recipesHasIngredientsCollectionRecipesHasIngredientsToAttach);
            }
            ingredients.setRecipesHasIngredientsCollection(attachedRecipesHasIngredientsCollection);
            em.persist(ingredients);
            for (RecipesHasIngredients recipesHasIngredientsCollectionRecipesHasIngredients : ingredients.getRecipesHasIngredientsCollection()) {
                Ingredients oldIngredientsOfRecipesHasIngredientsCollectionRecipesHasIngredients = recipesHasIngredientsCollectionRecipesHasIngredients.getIngredients();
                recipesHasIngredientsCollectionRecipesHasIngredients.setIngredients(ingredients);
                recipesHasIngredientsCollectionRecipesHasIngredients = em.merge(recipesHasIngredientsCollectionRecipesHasIngredients);
                if (oldIngredientsOfRecipesHasIngredientsCollectionRecipesHasIngredients != null) {
                    oldIngredientsOfRecipesHasIngredientsCollectionRecipesHasIngredients.getRecipesHasIngredientsCollection().remove(recipesHasIngredientsCollectionRecipesHasIngredients);
                    oldIngredientsOfRecipesHasIngredientsCollectionRecipesHasIngredients = em.merge(oldIngredientsOfRecipesHasIngredientsCollectionRecipesHasIngredients);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ingredients ingredients) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingredients persistentIngredients = em.find(Ingredients.class, ingredients.getId());
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionOld = persistentIngredients.getRecipesHasIngredientsCollection();
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionNew = ingredients.getRecipesHasIngredientsCollection();
            List<String> illegalOrphanMessages = null;
            for (RecipesHasIngredients recipesHasIngredientsCollectionOldRecipesHasIngredients : recipesHasIngredientsCollectionOld) {
                if (!recipesHasIngredientsCollectionNew.contains(recipesHasIngredientsCollectionOldRecipesHasIngredients)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RecipesHasIngredients " + recipesHasIngredientsCollectionOldRecipesHasIngredients + " since its ingredients field is not nullable.");
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
            ingredients.setRecipesHasIngredientsCollection(recipesHasIngredientsCollectionNew);
            ingredients = em.merge(ingredients);
            for (RecipesHasIngredients recipesHasIngredientsCollectionNewRecipesHasIngredients : recipesHasIngredientsCollectionNew) {
                if (!recipesHasIngredientsCollectionOld.contains(recipesHasIngredientsCollectionNewRecipesHasIngredients)) {
                    Ingredients oldIngredientsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients = recipesHasIngredientsCollectionNewRecipesHasIngredients.getIngredients();
                    recipesHasIngredientsCollectionNewRecipesHasIngredients.setIngredients(ingredients);
                    recipesHasIngredientsCollectionNewRecipesHasIngredients = em.merge(recipesHasIngredientsCollectionNewRecipesHasIngredients);
                    if (oldIngredientsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients != null && !oldIngredientsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients.equals(ingredients)) {
                        oldIngredientsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients.getRecipesHasIngredientsCollection().remove(recipesHasIngredientsCollectionNewRecipesHasIngredients);
                        oldIngredientsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients = em.merge(oldIngredientsOfRecipesHasIngredientsCollectionNewRecipesHasIngredients);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ingredients.getId();
                if (findIngredients(id) == null) {
                    throw new NonexistentEntityException("The ingredients with id " + id + " no longer exists.");
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
            Ingredients ingredients;
            try {
                ingredients = em.getReference(Ingredients.class, id);
                ingredients.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingredients with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionOrphanCheck = ingredients.getRecipesHasIngredientsCollection();
            for (RecipesHasIngredients recipesHasIngredientsCollectionOrphanCheckRecipesHasIngredients : recipesHasIngredientsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ingredients (" + ingredients + ") cannot be destroyed since the RecipesHasIngredients " + recipesHasIngredientsCollectionOrphanCheckRecipesHasIngredients + " in its recipesHasIngredientsCollection field has a non-nullable ingredients field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ingredients);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ingredients> findIngredientsEntities() {
        return findIngredientsEntities(true, -1, -1);
    }

    public List<Ingredients> findIngredientsEntities(int maxResults, int firstResult) {
        return findIngredientsEntities(false, maxResults, firstResult);
    }

    private List<Ingredients> findIngredientsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ingredients.class));
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

    public Ingredients findIngredients(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ingredients.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngredientsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ingredients> rt = cq.from(Ingredients.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
