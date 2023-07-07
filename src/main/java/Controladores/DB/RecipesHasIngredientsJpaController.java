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
import Controladores.DB.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Ingredients;
import Entidades.MeasurementUnits;
import Entidades.Recipes;
import Entidades.RecipesHasIngredients;
import Entidades.RecipesHasIngredientsPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class RecipesHasIngredientsJpaController implements Serializable {

    public RecipesHasIngredientsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RecipesHasIngredients recipesHasIngredients) throws PreexistingEntityException, Exception {
        if (recipesHasIngredients.getRecipesHasIngredientsPK() == null) {
            recipesHasIngredients.setRecipesHasIngredientsPK(new RecipesHasIngredientsPK());
        }
        recipesHasIngredients.getRecipesHasIngredientsPK().setIngredientsId(recipesHasIngredients.getIngredients().getId());
        recipesHasIngredients.getRecipesHasIngredientsPK().setRecipesId(recipesHasIngredients.getRecipes().getId());
        recipesHasIngredients.getRecipesHasIngredientsPK().setMeasurementUnitsId(recipesHasIngredients.getMeasurementUnits().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingredients ingredients = recipesHasIngredients.getIngredients();
            if (ingredients != null) {
                ingredients = em.getReference(ingredients.getClass(), ingredients.getId());
                recipesHasIngredients.setIngredients(ingredients);
            }
            MeasurementUnits measurementUnits = recipesHasIngredients.getMeasurementUnits();
            if (measurementUnits != null) {
                measurementUnits = em.getReference(measurementUnits.getClass(), measurementUnits.getId());
                recipesHasIngredients.setMeasurementUnits(measurementUnits);
            }
            Recipes recipes = recipesHasIngredients.getRecipes();
            if (recipes != null) {
                recipes = em.getReference(recipes.getClass(), recipes.getId());
                recipesHasIngredients.setRecipes(recipes);
            }
            em.persist(recipesHasIngredients);
            if (ingredients != null) {
                ingredients.getRecipesHasIngredientsCollection().add(recipesHasIngredients);
                ingredients = em.merge(ingredients);
            }
            if (measurementUnits != null) {
                measurementUnits.getRecipesHasIngredientsCollection().add(recipesHasIngredients);
                measurementUnits = em.merge(measurementUnits);
            }
            if (recipes != null) {
                recipes.getRecipesHasIngredientsCollection().add(recipesHasIngredients);
                recipes = em.merge(recipes);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRecipesHasIngredients(recipesHasIngredients.getRecipesHasIngredientsPK()) != null) {
                throw new PreexistingEntityException("RecipesHasIngredients " + recipesHasIngredients + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RecipesHasIngredients recipesHasIngredients) throws NonexistentEntityException, Exception {
        recipesHasIngredients.getRecipesHasIngredientsPK().setIngredientsId(recipesHasIngredients.getIngredients().getId());
        recipesHasIngredients.getRecipesHasIngredientsPK().setRecipesId(recipesHasIngredients.getRecipes().getId());
        recipesHasIngredients.getRecipesHasIngredientsPK().setMeasurementUnitsId(recipesHasIngredients.getMeasurementUnits().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecipesHasIngredients persistentRecipesHasIngredients = em.find(RecipesHasIngredients.class, recipesHasIngredients.getRecipesHasIngredientsPK());
            Ingredients ingredientsOld = persistentRecipesHasIngredients.getIngredients();
            Ingredients ingredientsNew = recipesHasIngredients.getIngredients();
            MeasurementUnits measurementUnitsOld = persistentRecipesHasIngredients.getMeasurementUnits();
            MeasurementUnits measurementUnitsNew = recipesHasIngredients.getMeasurementUnits();
            Recipes recipesOld = persistentRecipesHasIngredients.getRecipes();
            Recipes recipesNew = recipesHasIngredients.getRecipes();
            if (ingredientsNew != null) {
                ingredientsNew = em.getReference(ingredientsNew.getClass(), ingredientsNew.getId());
                recipesHasIngredients.setIngredients(ingredientsNew);
            }
            if (measurementUnitsNew != null) {
                measurementUnitsNew = em.getReference(measurementUnitsNew.getClass(), measurementUnitsNew.getId());
                recipesHasIngredients.setMeasurementUnits(measurementUnitsNew);
            }
            if (recipesNew != null) {
                recipesNew = em.getReference(recipesNew.getClass(), recipesNew.getId());
                recipesHasIngredients.setRecipes(recipesNew);
            }
            recipesHasIngredients = em.merge(recipesHasIngredients);
            if (ingredientsOld != null && !ingredientsOld.equals(ingredientsNew)) {
                ingredientsOld.getRecipesHasIngredientsCollection().remove(recipesHasIngredients);
                ingredientsOld = em.merge(ingredientsOld);
            }
            if (ingredientsNew != null && !ingredientsNew.equals(ingredientsOld)) {
                ingredientsNew.getRecipesHasIngredientsCollection().add(recipesHasIngredients);
                ingredientsNew = em.merge(ingredientsNew);
            }
            if (measurementUnitsOld != null && !measurementUnitsOld.equals(measurementUnitsNew)) {
                measurementUnitsOld.getRecipesHasIngredientsCollection().remove(recipesHasIngredients);
                measurementUnitsOld = em.merge(measurementUnitsOld);
            }
            if (measurementUnitsNew != null && !measurementUnitsNew.equals(measurementUnitsOld)) {
                measurementUnitsNew.getRecipesHasIngredientsCollection().add(recipesHasIngredients);
                measurementUnitsNew = em.merge(measurementUnitsNew);
            }
            if (recipesOld != null && !recipesOld.equals(recipesNew)) {
                recipesOld.getRecipesHasIngredientsCollection().remove(recipesHasIngredients);
                recipesOld = em.merge(recipesOld);
            }
            if (recipesNew != null && !recipesNew.equals(recipesOld)) {
                recipesNew.getRecipesHasIngredientsCollection().add(recipesHasIngredients);
                recipesNew = em.merge(recipesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RecipesHasIngredientsPK id = recipesHasIngredients.getRecipesHasIngredientsPK();
                if (findRecipesHasIngredients(id) == null) {
                    throw new NonexistentEntityException("The recipesHasIngredients with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RecipesHasIngredientsPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecipesHasIngredients recipesHasIngredients;
            try {
                recipesHasIngredients = em.getReference(RecipesHasIngredients.class, id);
                recipesHasIngredients.getRecipesHasIngredientsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recipesHasIngredients with id " + id + " no longer exists.", enfe);
            }
            Ingredients ingredients = recipesHasIngredients.getIngredients();
            if (ingredients != null) {
                ingredients.getRecipesHasIngredientsCollection().remove(recipesHasIngredients);
                ingredients = em.merge(ingredients);
            }
            MeasurementUnits measurementUnits = recipesHasIngredients.getMeasurementUnits();
            if (measurementUnits != null) {
                measurementUnits.getRecipesHasIngredientsCollection().remove(recipesHasIngredients);
                measurementUnits = em.merge(measurementUnits);
            }
            Recipes recipes = recipesHasIngredients.getRecipes();
            if (recipes != null) {
                recipes.getRecipesHasIngredientsCollection().remove(recipesHasIngredients);
                recipes = em.merge(recipes);
            }
            em.remove(recipesHasIngredients);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RecipesHasIngredients> findRecipesHasIngredientsEntities() {
        return findRecipesHasIngredientsEntities(true, -1, -1);
    }

    public List<RecipesHasIngredients> findRecipesHasIngredientsEntities(int maxResults, int firstResult) {
        return findRecipesHasIngredientsEntities(false, maxResults, firstResult);
    }

    private List<RecipesHasIngredients> findRecipesHasIngredientsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RecipesHasIngredients.class));
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

    public RecipesHasIngredients findRecipesHasIngredients(RecipesHasIngredientsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RecipesHasIngredients.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecipesHasIngredientsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RecipesHasIngredients> rt = cq.from(RecipesHasIngredients.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
