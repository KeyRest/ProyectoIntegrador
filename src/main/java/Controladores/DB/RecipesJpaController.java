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
import Entidades.UsersVoteRecipes;
import Entidades.Vistis;
import Entidades.FeaturedRecipe;
import Entidades.Recipe;
import Entidades.RecipesHasIngredients;
import Entidades.UsersSaveRecipes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class RecipesJpaController implements Serializable {

    public RecipesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recipe recipes) {
        if (recipes.getCategoriesCollection() == null) {
            recipes.setCategoriesCollection(new ArrayList<Categories>());
        }
        if (recipes.getOccasionsCollection() == null) {
            recipes.setOccasionsCollection(new ArrayList<Occasions>());
        }
        if (recipes.getUsersVoteRecipesCollection() == null) {
            recipes.setUsersVoteRecipesCollection(new ArrayList<UsersVoteRecipes>());
        }
        if (recipes.getVistisCollection() == null) {
            recipes.setVistisCollection(new ArrayList<Vistis>());
        }
        if (recipes.getFeaturedRecipeCollection() == null) {
            recipes.setFeaturedRecipeCollection(new ArrayList<FeaturedRecipe>());
        }
        if (recipes.getRecipesHasIngredientsCollection() == null) {
            recipes.setRecipesHasIngredientsCollection(new ArrayList<RecipesHasIngredients>());
        }
        if (recipes.getUsersSaveRecipesCollection() == null) {
            recipes.setUsersSaveRecipesCollection(new ArrayList<UsersSaveRecipes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Levels levelsId = recipes.getLevelsId();
            if (levelsId != null) {
                levelsId = em.getReference(levelsId.getClass(), levelsId.getId());
                recipes.setLevelsId(levelsId);
            }
            Collection<Categories> attachedCategoriesCollection = new ArrayList<Categories>();
            for (Categories categoriesCollectionCategoriesToAttach : recipes.getCategoriesCollection()) {
                categoriesCollectionCategoriesToAttach = em.getReference(categoriesCollectionCategoriesToAttach.getClass(), categoriesCollectionCategoriesToAttach.getId());
                attachedCategoriesCollection.add(categoriesCollectionCategoriesToAttach);
            }
            recipes.setCategoriesCollection(attachedCategoriesCollection);
            Collection<Occasions> attachedOccasionsCollection = new ArrayList<Occasions>();
            for (Occasions occasionsCollectionOccasionsToAttach : recipes.getOccasionsCollection()) {
                occasionsCollectionOccasionsToAttach = em.getReference(occasionsCollectionOccasionsToAttach.getClass(), occasionsCollectionOccasionsToAttach.getId());
                attachedOccasionsCollection.add(occasionsCollectionOccasionsToAttach);
            }
            recipes.setOccasionsCollection(attachedOccasionsCollection);
            Collection<UsersVoteRecipes> attachedUsersVoteRecipesCollection = new ArrayList<UsersVoteRecipes>();
            for (UsersVoteRecipes usersVoteRecipesCollectionUsersVoteRecipesToAttach : recipes.getUsersVoteRecipesCollection()) {
                usersVoteRecipesCollectionUsersVoteRecipesToAttach = em.getReference(usersVoteRecipesCollectionUsersVoteRecipesToAttach.getClass(), usersVoteRecipesCollectionUsersVoteRecipesToAttach.getUsersVoteRecipesPK());
                attachedUsersVoteRecipesCollection.add(usersVoteRecipesCollectionUsersVoteRecipesToAttach);
            }
            recipes.setUsersVoteRecipesCollection(attachedUsersVoteRecipesCollection);
            Collection<Vistis> attachedVistisCollection = new ArrayList<Vistis>();
            for (Vistis vistisCollectionVistisToAttach : recipes.getVistisCollection()) {
                vistisCollectionVistisToAttach = em.getReference(vistisCollectionVistisToAttach.getClass(), vistisCollectionVistisToAttach.getId());
                attachedVistisCollection.add(vistisCollectionVistisToAttach);
            }
            recipes.setVistisCollection(attachedVistisCollection);
            Collection<FeaturedRecipe> attachedFeaturedRecipeCollection = new ArrayList<FeaturedRecipe>();
            for (FeaturedRecipe featuredRecipeCollectionFeaturedRecipeToAttach : recipes.getFeaturedRecipeCollection()) {
                featuredRecipeCollectionFeaturedRecipeToAttach = em.getReference(featuredRecipeCollectionFeaturedRecipeToAttach.getClass(), featuredRecipeCollectionFeaturedRecipeToAttach.getFeaturedRecipePK());
                attachedFeaturedRecipeCollection.add(featuredRecipeCollectionFeaturedRecipeToAttach);
            }
            recipes.setFeaturedRecipeCollection(attachedFeaturedRecipeCollection);
            Collection<RecipesHasIngredients> attachedRecipesHasIngredientsCollection = new ArrayList<RecipesHasIngredients>();
            for (RecipesHasIngredients recipesHasIngredientsCollectionRecipesHasIngredientsToAttach : recipes.getRecipesHasIngredientsCollection()) {
                recipesHasIngredientsCollectionRecipesHasIngredientsToAttach = em.getReference(recipesHasIngredientsCollectionRecipesHasIngredientsToAttach.getClass(), recipesHasIngredientsCollectionRecipesHasIngredientsToAttach.getRecipesHasIngredientsPK());
                attachedRecipesHasIngredientsCollection.add(recipesHasIngredientsCollectionRecipesHasIngredientsToAttach);
            }
            recipes.setRecipesHasIngredientsCollection(attachedRecipesHasIngredientsCollection);
            Collection<UsersSaveRecipes> attachedUsersSaveRecipesCollection = new ArrayList<UsersSaveRecipes>();
            for (UsersSaveRecipes usersSaveRecipesCollectionUsersSaveRecipesToAttach : recipes.getUsersSaveRecipesCollection()) {
                usersSaveRecipesCollectionUsersSaveRecipesToAttach = em.getReference(usersSaveRecipesCollectionUsersSaveRecipesToAttach.getClass(), usersSaveRecipesCollectionUsersSaveRecipesToAttach.getUsersSaveRecipesPK());
                attachedUsersSaveRecipesCollection.add(usersSaveRecipesCollectionUsersSaveRecipesToAttach);
            }
            recipes.setUsersSaveRecipesCollection(attachedUsersSaveRecipesCollection);
            em.persist(recipes);
            if (levelsId != null) {
                levelsId.getRecipesCollection().add(recipes);
                levelsId = em.merge(levelsId);
            }
            for (Categories categoriesCollectionCategories : recipes.getCategoriesCollection()) {
                categoriesCollectionCategories.getRecipesCollection().add(recipes);
                categoriesCollectionCategories = em.merge(categoriesCollectionCategories);
            }
            for (Occasions occasionsCollectionOccasions : recipes.getOccasionsCollection()) {
                occasionsCollectionOccasions.getRecipesCollection().add(recipes);
                occasionsCollectionOccasions = em.merge(occasionsCollectionOccasions);
            }
            for (UsersVoteRecipes usersVoteRecipesCollectionUsersVoteRecipes : recipes.getUsersVoteRecipesCollection()) {
                Recipe oldRecipesOfUsersVoteRecipesCollectionUsersVoteRecipes = usersVoteRecipesCollectionUsersVoteRecipes.getRecipes();
                usersVoteRecipesCollectionUsersVoteRecipes.setRecipes(recipes);
                usersVoteRecipesCollectionUsersVoteRecipes = em.merge(usersVoteRecipesCollectionUsersVoteRecipes);
                if (oldRecipesOfUsersVoteRecipesCollectionUsersVoteRecipes != null) {
                    oldRecipesOfUsersVoteRecipesCollectionUsersVoteRecipes.getUsersVoteRecipesCollection().remove(usersVoteRecipesCollectionUsersVoteRecipes);
                    oldRecipesOfUsersVoteRecipesCollectionUsersVoteRecipes = em.merge(oldRecipesOfUsersVoteRecipesCollectionUsersVoteRecipes);
                }
            }
            for (Vistis vistisCollectionVistis : recipes.getVistisCollection()) {
                Recipe oldRecipesIdOfVistisCollectionVistis = vistisCollectionVistis.getRecipesId();
                vistisCollectionVistis.setRecipesId(recipes);
                vistisCollectionVistis = em.merge(vistisCollectionVistis);
                if (oldRecipesIdOfVistisCollectionVistis != null) {
                    oldRecipesIdOfVistisCollectionVistis.getVistisCollection().remove(vistisCollectionVistis);
                    oldRecipesIdOfVistisCollectionVistis = em.merge(oldRecipesIdOfVistisCollectionVistis);
                }
            }
            for (FeaturedRecipe featuredRecipeCollectionFeaturedRecipe : recipes.getFeaturedRecipeCollection()) {
                Recipe oldRecipesOfFeaturedRecipeCollectionFeaturedRecipe = featuredRecipeCollectionFeaturedRecipe.getRecipes();
                featuredRecipeCollectionFeaturedRecipe.setRecipes(recipes);
                featuredRecipeCollectionFeaturedRecipe = em.merge(featuredRecipeCollectionFeaturedRecipe);
                if (oldRecipesOfFeaturedRecipeCollectionFeaturedRecipe != null) {
                    oldRecipesOfFeaturedRecipeCollectionFeaturedRecipe.getFeaturedRecipeCollection().remove(featuredRecipeCollectionFeaturedRecipe);
                    oldRecipesOfFeaturedRecipeCollectionFeaturedRecipe = em.merge(oldRecipesOfFeaturedRecipeCollectionFeaturedRecipe);
                }
            }
            for (RecipesHasIngredients recipesHasIngredientsCollectionRecipesHasIngredients : recipes.getRecipesHasIngredientsCollection()) {
                Recipe oldRecipesOfRecipesHasIngredientsCollectionRecipesHasIngredients = recipesHasIngredientsCollectionRecipesHasIngredients.getRecipes();
                recipesHasIngredientsCollectionRecipesHasIngredients.setRecipes(recipes);
                recipesHasIngredientsCollectionRecipesHasIngredients = em.merge(recipesHasIngredientsCollectionRecipesHasIngredients);
                if (oldRecipesOfRecipesHasIngredientsCollectionRecipesHasIngredients != null) {
                    oldRecipesOfRecipesHasIngredientsCollectionRecipesHasIngredients.getRecipesHasIngredientsCollection().remove(recipesHasIngredientsCollectionRecipesHasIngredients);
                    oldRecipesOfRecipesHasIngredientsCollectionRecipesHasIngredients = em.merge(oldRecipesOfRecipesHasIngredientsCollectionRecipesHasIngredients);
                }
            }
            for (UsersSaveRecipes usersSaveRecipesCollectionUsersSaveRecipes : recipes.getUsersSaveRecipesCollection()) {
                Recipe oldRecipesOfUsersSaveRecipesCollectionUsersSaveRecipes = usersSaveRecipesCollectionUsersSaveRecipes.getRecipes();
                usersSaveRecipesCollectionUsersSaveRecipes.setRecipes(recipes);
                usersSaveRecipesCollectionUsersSaveRecipes = em.merge(usersSaveRecipesCollectionUsersSaveRecipes);
                if (oldRecipesOfUsersSaveRecipesCollectionUsersSaveRecipes != null) {
                    oldRecipesOfUsersSaveRecipesCollectionUsersSaveRecipes.getUsersSaveRecipesCollection().remove(usersSaveRecipesCollectionUsersSaveRecipes);
                    oldRecipesOfUsersSaveRecipesCollectionUsersSaveRecipes = em.merge(oldRecipesOfUsersSaveRecipesCollectionUsersSaveRecipes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recipe recipes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recipe persistentRecipes = em.find(Recipe.class, recipes.getId());
            Levels levelsIdOld = persistentRecipes.getLevelsId();
            Levels levelsIdNew = recipes.getLevelsId();
            Collection<Categories> categoriesCollectionOld = persistentRecipes.getCategoriesCollection();
            Collection<Categories> categoriesCollectionNew = recipes.getCategoriesCollection();
            Collection<Occasions> occasionsCollectionOld = persistentRecipes.getOccasionsCollection();
            Collection<Occasions> occasionsCollectionNew = recipes.getOccasionsCollection();
            Collection<UsersVoteRecipes> usersVoteRecipesCollectionOld = persistentRecipes.getUsersVoteRecipesCollection();
            Collection<UsersVoteRecipes> usersVoteRecipesCollectionNew = recipes.getUsersVoteRecipesCollection();
            Collection<Vistis> vistisCollectionOld = persistentRecipes.getVistisCollection();
            Collection<Vistis> vistisCollectionNew = recipes.getVistisCollection();
            Collection<FeaturedRecipe> featuredRecipeCollectionOld = persistentRecipes.getFeaturedRecipeCollection();
            Collection<FeaturedRecipe> featuredRecipeCollectionNew = recipes.getFeaturedRecipeCollection();
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionOld = persistentRecipes.getRecipesHasIngredientsCollection();
            Collection<RecipesHasIngredients> recipesHasIngredientsCollectionNew = recipes.getRecipesHasIngredientsCollection();
            Collection<UsersSaveRecipes> usersSaveRecipesCollectionOld = persistentRecipes.getUsersSaveRecipesCollection();
            Collection<UsersSaveRecipes> usersSaveRecipesCollectionNew = recipes.getUsersSaveRecipesCollection();
            List<String> illegalOrphanMessages = null;
            for (UsersVoteRecipes usersVoteRecipesCollectionOldUsersVoteRecipes : usersVoteRecipesCollectionOld) {
                if (!usersVoteRecipesCollectionNew.contains(usersVoteRecipesCollectionOldUsersVoteRecipes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsersVoteRecipes " + usersVoteRecipesCollectionOldUsersVoteRecipes + " since its recipes field is not nullable.");
                }
            }
            for (Vistis vistisCollectionOldVistis : vistisCollectionOld) {
                if (!vistisCollectionNew.contains(vistisCollectionOldVistis)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vistis " + vistisCollectionOldVistis + " since its recipesId field is not nullable.");
                }
            }
            for (FeaturedRecipe featuredRecipeCollectionOldFeaturedRecipe : featuredRecipeCollectionOld) {
                if (!featuredRecipeCollectionNew.contains(featuredRecipeCollectionOldFeaturedRecipe)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FeaturedRecipe " + featuredRecipeCollectionOldFeaturedRecipe + " since its recipes field is not nullable.");
                }
            }
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
            Collection<UsersVoteRecipes> attachedUsersVoteRecipesCollectionNew = new ArrayList<UsersVoteRecipes>();
            for (UsersVoteRecipes usersVoteRecipesCollectionNewUsersVoteRecipesToAttach : usersVoteRecipesCollectionNew) {
                usersVoteRecipesCollectionNewUsersVoteRecipesToAttach = em.getReference(usersVoteRecipesCollectionNewUsersVoteRecipesToAttach.getClass(), usersVoteRecipesCollectionNewUsersVoteRecipesToAttach.getUsersVoteRecipesPK());
                attachedUsersVoteRecipesCollectionNew.add(usersVoteRecipesCollectionNewUsersVoteRecipesToAttach);
            }
            usersVoteRecipesCollectionNew = attachedUsersVoteRecipesCollectionNew;
            recipes.setUsersVoteRecipesCollection(usersVoteRecipesCollectionNew);
            Collection<Vistis> attachedVistisCollectionNew = new ArrayList<Vistis>();
            for (Vistis vistisCollectionNewVistisToAttach : vistisCollectionNew) {
                vistisCollectionNewVistisToAttach = em.getReference(vistisCollectionNewVistisToAttach.getClass(), vistisCollectionNewVistisToAttach.getId());
                attachedVistisCollectionNew.add(vistisCollectionNewVistisToAttach);
            }
            vistisCollectionNew = attachedVistisCollectionNew;
            recipes.setVistisCollection(vistisCollectionNew);
            Collection<FeaturedRecipe> attachedFeaturedRecipeCollectionNew = new ArrayList<FeaturedRecipe>();
            for (FeaturedRecipe featuredRecipeCollectionNewFeaturedRecipeToAttach : featuredRecipeCollectionNew) {
                featuredRecipeCollectionNewFeaturedRecipeToAttach = em.getReference(featuredRecipeCollectionNewFeaturedRecipeToAttach.getClass(), featuredRecipeCollectionNewFeaturedRecipeToAttach.getFeaturedRecipePK());
                attachedFeaturedRecipeCollectionNew.add(featuredRecipeCollectionNewFeaturedRecipeToAttach);
            }
            featuredRecipeCollectionNew = attachedFeaturedRecipeCollectionNew;
            recipes.setFeaturedRecipeCollection(featuredRecipeCollectionNew);
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
            for (UsersVoteRecipes usersVoteRecipesCollectionNewUsersVoteRecipes : usersVoteRecipesCollectionNew) {
                if (!usersVoteRecipesCollectionOld.contains(usersVoteRecipesCollectionNewUsersVoteRecipes)) {
                    Recipe oldRecipesOfUsersVoteRecipesCollectionNewUsersVoteRecipes = usersVoteRecipesCollectionNewUsersVoteRecipes.getRecipes();
                    usersVoteRecipesCollectionNewUsersVoteRecipes.setRecipes(recipes);
                    usersVoteRecipesCollectionNewUsersVoteRecipes = em.merge(usersVoteRecipesCollectionNewUsersVoteRecipes);
                    if (oldRecipesOfUsersVoteRecipesCollectionNewUsersVoteRecipes != null && !oldRecipesOfUsersVoteRecipesCollectionNewUsersVoteRecipes.equals(recipes)) {
                        oldRecipesOfUsersVoteRecipesCollectionNewUsersVoteRecipes.getUsersVoteRecipesCollection().remove(usersVoteRecipesCollectionNewUsersVoteRecipes);
                        oldRecipesOfUsersVoteRecipesCollectionNewUsersVoteRecipes = em.merge(oldRecipesOfUsersVoteRecipesCollectionNewUsersVoteRecipes);
                    }
                }
            }
            for (Vistis vistisCollectionNewVistis : vistisCollectionNew) {
                if (!vistisCollectionOld.contains(vistisCollectionNewVistis)) {
                    Recipe oldRecipesIdOfVistisCollectionNewVistis = vistisCollectionNewVistis.getRecipesId();
                    vistisCollectionNewVistis.setRecipesId(recipes);
                    vistisCollectionNewVistis = em.merge(vistisCollectionNewVistis);
                    if (oldRecipesIdOfVistisCollectionNewVistis != null && !oldRecipesIdOfVistisCollectionNewVistis.equals(recipes)) {
                        oldRecipesIdOfVistisCollectionNewVistis.getVistisCollection().remove(vistisCollectionNewVistis);
                        oldRecipesIdOfVistisCollectionNewVistis = em.merge(oldRecipesIdOfVistisCollectionNewVistis);
                    }
                }
            }
            for (FeaturedRecipe featuredRecipeCollectionNewFeaturedRecipe : featuredRecipeCollectionNew) {
                if (!featuredRecipeCollectionOld.contains(featuredRecipeCollectionNewFeaturedRecipe)) {
                    Recipe oldRecipesOfFeaturedRecipeCollectionNewFeaturedRecipe = featuredRecipeCollectionNewFeaturedRecipe.getRecipes();
                    featuredRecipeCollectionNewFeaturedRecipe.setRecipes(recipes);
                    featuredRecipeCollectionNewFeaturedRecipe = em.merge(featuredRecipeCollectionNewFeaturedRecipe);
                    if (oldRecipesOfFeaturedRecipeCollectionNewFeaturedRecipe != null && !oldRecipesOfFeaturedRecipeCollectionNewFeaturedRecipe.equals(recipes)) {
                        oldRecipesOfFeaturedRecipeCollectionNewFeaturedRecipe.getFeaturedRecipeCollection().remove(featuredRecipeCollectionNewFeaturedRecipe);
                        oldRecipesOfFeaturedRecipeCollectionNewFeaturedRecipe = em.merge(oldRecipesOfFeaturedRecipeCollectionNewFeaturedRecipe);
                    }
                }
            }
            for (RecipesHasIngredients recipesHasIngredientsCollectionNewRecipesHasIngredients : recipesHasIngredientsCollectionNew) {
                if (!recipesHasIngredientsCollectionOld.contains(recipesHasIngredientsCollectionNewRecipesHasIngredients)) {
                    Recipe oldRecipesOfRecipesHasIngredientsCollectionNewRecipesHasIngredients = recipesHasIngredientsCollectionNewRecipesHasIngredients.getRecipes();
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
                    Recipe oldRecipesOfUsersSaveRecipesCollectionNewUsersSaveRecipes = usersSaveRecipesCollectionNewUsersSaveRecipes.getRecipes();
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
            Recipe recipes;
            try {
                recipes = em.getReference(Recipe.class, id);
                recipes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recipes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UsersVoteRecipes> usersVoteRecipesCollectionOrphanCheck = recipes.getUsersVoteRecipesCollection();
            for (UsersVoteRecipes usersVoteRecipesCollectionOrphanCheckUsersVoteRecipes : usersVoteRecipesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recipes (" + recipes + ") cannot be destroyed since the UsersVoteRecipes " + usersVoteRecipesCollectionOrphanCheckUsersVoteRecipes + " in its usersVoteRecipesCollection field has a non-nullable recipes field.");
            }
            Collection<Vistis> vistisCollectionOrphanCheck = recipes.getVistisCollection();
            for (Vistis vistisCollectionOrphanCheckVistis : vistisCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recipes (" + recipes + ") cannot be destroyed since the Vistis " + vistisCollectionOrphanCheckVistis + " in its vistisCollection field has a non-nullable recipesId field.");
            }
            Collection<FeaturedRecipe> featuredRecipeCollectionOrphanCheck = recipes.getFeaturedRecipeCollection();
            for (FeaturedRecipe featuredRecipeCollectionOrphanCheckFeaturedRecipe : featuredRecipeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recipes (" + recipes + ") cannot be destroyed since the FeaturedRecipe " + featuredRecipeCollectionOrphanCheckFeaturedRecipe + " in its featuredRecipeCollection field has a non-nullable recipes field.");
            }
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

    public List<Recipe> findRecipesEntities() {
        return findRecipesEntities(true, -1, -1);
    }

    public List<Recipe> findRecipesEntities(int maxResults, int firstResult) {
        return findRecipesEntities(false, maxResults, firstResult);
    }

    private List<Recipe> findRecipesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recipe.class));
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

    public Recipe findRecipes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recipe.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecipesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recipe> rt = cq.from(Recipe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
