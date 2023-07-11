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
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Levels;
import Entidades.Categories;
import java.util.ArrayList;
import java.util.Collection;
import Entidades.Occasions;
import Entidades.Recipes;
import Entidades.RecipesHasIngredients;
import Entidades.UsersSaveRecipes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RecipesJpaController implements Serializable {

    public RecipesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("ProyectoIntegrador_jar_1.0");

    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recipes recipes) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.merge(recipes);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recipes recipes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recipes persistentRecipes = em.find(Recipes.class, recipes.getId());
            Levels levelsIdOld = persistentRecipes.getLevelsId();
            Levels levelsIdNew = recipes.getLevelsId();
            Collection<Categories> categoriesCollectionOld = persistentRecipes.getCategoriesCollection();
            Collection<Categories> categoriesCollectionNew = recipes.getCategoriesCollection();
            Collection<Occasions> occasionsCollectionOld = persistentRecipes.getOccasionsCollection();
            Collection<Occasions> occasionsCollectionNew = recipes.getOccasionsCollection();

            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionOld = persistentRecipes.getRecipesHasIngredientsCollection();
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionNew = recipes.getRecipesHasIngredientsCollection();
            Collection<UsersSaveRecipes> usersSaveRecipesCollectionOld = persistentRecipes.getUsersSaveRecipesCollection();
            Collection<UsersSaveRecipes> usersSaveRecipesCollectionNew = recipes.getUsersSaveRecipesCollection();
            List<String> illegalOrphanMessages = null;

            for (RecipesHasIngredients recipesHasIngredientsCollectionOldRecipesHasIngredients : recipesHasIngredientsCollectionOld) {
                if (!recipesHasIngredientsCollectionNew.contains(recipesHasIngredientsCollectionOldRecipesHasIngredients)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RecipesHasIngredients " + recipesHasIngredientsCollectionOldRecipesHasIngredients + " since its recipes field is not nullable.");
                }
            }
            for (UsersSaveRecipes usersSaveRecipesCollectionOldUsersSaveRecipes : usersSaveRecipesCollectionOld) {
                if (!usersSaveRecipesCollectionNew.contains(usersSaveRecipesCollectionOldUsersSaveRecipes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsersSaveRecipes " + usersSaveRecipesCollectionOldUsersSaveRecipes + " since its recipes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (levelsIdNew != null) {
                levelsIdNew = em.getReference(levelsIdNew.getClass(), levelsIdNew.getId());
                recipes.setLevelsId(levelsIdNew);
            }
            Collection<Categories> attachedCategoriesCollectionNew = new ArrayList<Categories>();
            for (Categories categoriesCollectionNewCategoriesToAttach : categoriesCollectionNew) {
                categoriesCollectionNewCategoriesToAttach = em.getReference(categoriesCollectionNewCategoriesToAttach.getClass(), categoriesCollectionNewCategoriesToAttach.getId());
                attachedCategoriesCollectionNew.add(categoriesCollectionNewCategoriesToAttach);
            }
            categoriesCollectionNew = attachedCategoriesCollectionNew;
            recipes.setCategoriesCollection(categoriesCollectionNew);
            Collection<Occasions> attachedOccasionsCollectionNew = new ArrayList<Occasions>();
            for (Occasions occasionsCollectionNewOccasionsToAttach : occasionsCollectionNew) {
                occasionsCollectionNewOccasionsToAttach = em.getReference(occasionsCollectionNewOccasionsToAttach.getClass(), occasionsCollectionNewOccasionsToAttach.getId());
                attachedOccasionsCollectionNew.add(occasionsCollectionNewOccasionsToAttach);
            }
            occasionsCollectionNew = attachedOccasionsCollectionNew;
            recipes.setOccasionsCollection(occasionsCollectionNew);

            Collection<RecipesHasIngredients> attachedRecipesHasIngredientsCollectionNew = new ArrayList<RecipesHasIngredients>();
            for (RecipesHasIngredients recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach : recipesHasIngredientsCollectionNew) {
                recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach = em.getReference(recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach.getClass(), recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach.getRecipesHasIngredientsPK());
                attachedRecipesHasIngredientsCollectionNew.add(recipesHasIngredientsCollectionNewRecipesHasIngredientsToAttach);
            }
            recipesHasIngredientsCollectionNew = attachedRecipesHasIngredientsCollectionNew;
            recipes.setRecipesHasIngredientsCollection(recipesHasIngredientsCollectionNew);
            Collection<UsersSaveRecipes> attachedUsersSaveRecipesCollectionNew = new ArrayList<UsersSaveRecipes>();
            for (UsersSaveRecipes usersSaveRecipesCollectionNewUsersSaveRecipesToAttach : usersSaveRecipesCollectionNew) {
                usersSaveRecipesCollectionNewUsersSaveRecipesToAttach = em.getReference(usersSaveRecipesCollectionNewUsersSaveRecipesToAttach.getClass(), usersSaveRecipesCollectionNewUsersSaveRecipesToAttach.getUsersSaveRecipesPK());
                attachedUsersSaveRecipesCollectionNew.add(usersSaveRecipesCollectionNewUsersSaveRecipesToAttach);
            }
            usersSaveRecipesCollectionNew = attachedUsersSaveRecipesCollectionNew;
            recipes.setUsersSaveRecipesCollection(usersSaveRecipesCollectionNew);
            recipes = em.merge(recipes);
            if (levelsIdOld != null && !levelsIdOld.equals(levelsIdNew)) {
                levelsIdOld.getRecipesCollection().remove(recipes);
                levelsIdOld = em.merge(levelsIdOld);
            }
            if (levelsIdNew != null && !levelsIdNew.equals(levelsIdOld)) {
                levelsIdNew.getRecipesCollection().add(recipes);
                levelsIdNew = em.merge(levelsIdNew);
            }
            for (Categories categoriesCollectionOldCategories : categoriesCollectionOld) {
                if (!categoriesCollectionNew.contains(categoriesCollectionOldCategories)) {
                    categoriesCollectionOldCategories.getRecipesCollection().remove(recipes);
                    categoriesCollectionOldCategories = em.merge(categoriesCollectionOldCategories);
                }
            }
            for (Categories categoriesCollectionNewCategories : categoriesCollectionNew) {
                if (!categoriesCollectionOld.contains(categoriesCollectionNewCategories)) {
                    categoriesCollectionNewCategories.getRecipesCollection().add(recipes);
                    categoriesCollectionNewCategories = em.merge(categoriesCollectionNewCategories);
                }
            }
            for (Occasions occasionsCollectionOldOccasions : occasionsCollectionOld) {
                if (!occasionsCollectionNew.contains(occasionsCollectionOldOccasions)) {
                    occasionsCollectionOldOccasions.getRecipesCollection().remove(recipes);
                    occasionsCollectionOldOccasions = em.merge(occasionsCollectionOldOccasions);
                }
            }
            for (Occasions occasionsCollectionNewOccasions : occasionsCollectionNew) {
                if (!occasionsCollectionOld.contains(occasionsCollectionNewOccasions)) {
                    occasionsCollectionNewOccasions.getRecipesCollection().add(recipes);
                    occasionsCollectionNewOccasions = em.merge(occasionsCollectionNewOccasions);
                }
            }

            for (RecipesHasIngredients recipesHasIngredientsCollectionNewRecipesHasIngredients : recipesHasIngredientsCollectionNew) {
                if (!recipesHasIngredientsCollectionOld.contains(recipesHasIngredientsCollectionNewRecipesHasIngredients)) {
                    Recipes oldRecipesOfRecipesHasIngredientsCollectionNewRecipesHasIngredients = recipesHasIngredientsCollectionNewRecipesHasIngredients.getRecipes();
                    recipesHasIngredientsCollectionNewRecipesHasIngredients.setRecipes(recipes);
                    recipesHasIngredientsCollectionNewRecipesHasIngredients = em.merge(recipesHasIngredientsCollectionNewRecipesHasIngredients);
                    if (oldRecipesOfRecipesHasIngredientsCollectionNewRecipesHasIngredients != null && !oldRecipesOfRecipesHasIngredientsCollectionNewRecipesHasIngredients.equals(recipes)) {
                        oldRecipesOfRecipesHasIngredientsCollectionNewRecipesHasIngredients.getRecipesHasIngredientsCollection().remove(recipesHasIngredientsCollectionNewRecipesHasIngredients);
                        oldRecipesOfRecipesHasIngredientsCollectionNewRecipesHasIngredients = em.merge(oldRecipesOfRecipesHasIngredientsCollectionNewRecipesHasIngredients);
                    }
                }
            }
            for (UsersSaveRecipes usersSaveRecipesCollectionNewUsersSaveRecipes : usersSaveRecipesCollectionNew) {
                if (!usersSaveRecipesCollectionOld.contains(usersSaveRecipesCollectionNewUsersSaveRecipes)) {
                    Recipes oldRecipesOfUsersSaveRecipesCollectionNewUsersSaveRecipes = usersSaveRecipesCollectionNewUsersSaveRecipes.getRecipes();
                    usersSaveRecipesCollectionNewUsersSaveRecipes.setRecipes(recipes);
                    usersSaveRecipesCollectionNewUsersSaveRecipes = em.merge(usersSaveRecipesCollectionNewUsersSaveRecipes);
                    if (oldRecipesOfUsersSaveRecipesCollectionNewUsersSaveRecipes != null && !oldRecipesOfUsersSaveRecipesCollectionNewUsersSaveRecipes.equals(recipes)) {
                        oldRecipesOfUsersSaveRecipesCollectionNewUsersSaveRecipes.getUsersSaveRecipesCollection().remove(usersSaveRecipesCollectionNewUsersSaveRecipes);
                        oldRecipesOfUsersSaveRecipesCollectionNewUsersSaveRecipes = em.merge(oldRecipesOfUsersSaveRecipesCollectionNewUsersSaveRecipes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = recipes.getId();
                if (findRecipes(id) == null) {
                    throw new NonexistentEntityException("The recipes with id " + id + " no longer exists.");
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
            Recipes recipes;
            try {
                recipes = em.getReference(Recipes.class, id);
                recipes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recipes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;

            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionOrphanCheck = recipes.getRecipesHasIngredientsCollection();
            for (RecipesHasIngredients recipesHasIngredientsCollectionOrphanCheckRecipesHasIngredients : recipesHasIngredientsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recipes (" + recipes + ") cannot be destroyed since the RecipesHasIngredients " + recipesHasIngredientsCollectionOrphanCheckRecipesHasIngredients + " in its recipesHasIngredientsCollection field has a non-nullable recipes field.");
            }
            Collection<UsersSaveRecipes> usersSaveRecipesCollectionOrphanCheck = recipes.getUsersSaveRecipesCollection();
            for (UsersSaveRecipes usersSaveRecipesCollectionOrphanCheckUsersSaveRecipes : usersSaveRecipesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recipes (" + recipes + ") cannot be destroyed since the UsersSaveRecipes " + usersSaveRecipesCollectionOrphanCheckUsersSaveRecipes + " in its usersSaveRecipesCollection field has a non-nullable recipes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Levels levelsId = recipes.getLevelsId();
            if (levelsId != null) {
                levelsId.getRecipesCollection().remove(recipes);
                levelsId = em.merge(levelsId);
            }
            Collection<Categories> categoriesCollection = recipes.getCategoriesCollection();
            for (Categories categoriesCollectionCategories : categoriesCollection) {
                categoriesCollectionCategories.getRecipesCollection().remove(recipes);
                categoriesCollectionCategories = em.merge(categoriesCollectionCategories);
            }
            Collection<Occasions> occasionsCollection = recipes.getOccasionsCollection();
            for (Occasions occasionsCollectionOccasions : occasionsCollection) {
                occasionsCollectionOccasions.getRecipesCollection().remove(recipes);
                occasionsCollectionOccasions = em.merge(occasionsCollectionOccasions);
            }
            em.remove(recipes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recipes> findRecipesEntities() {
        return findRecipesEntities(true, -1, -1);
    }

    public List<Recipes> findRecipesEntities(int maxResults, int firstResult) {
        return findRecipesEntities(false, maxResults, firstResult);
    }

    private List<Recipes> findRecipesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recipes.class));
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

    public Recipes findRecipes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recipes.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecipesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recipes> rt = cq.from(Recipes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
