/*
*Keiron Garro M
*C23212
*UCR
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controllers.DB;

import Controllers.DB.exceptions.NonexistentEntityException;
import Controllers.DB.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.DB.Recipes;
import Models.DB.Users;
import Models.DB.UsersSaveRecipes;
import Models.DB.UsersSaveRecipesPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class UsersSaveRecipesJpaController implements Serializable {

    public UsersSaveRecipesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsersSaveRecipes usersSaveRecipes) throws PreexistingEntityException, Exception {
        if (usersSaveRecipes.getUsersSaveRecipesPK() == null) {
            usersSaveRecipes.setUsersSaveRecipesPK(new UsersSaveRecipesPK());
        }
        usersSaveRecipes.getUsersSaveRecipesPK().setUsersId(usersSaveRecipes.getUsers().getId());
        usersSaveRecipes.getUsersSaveRecipesPK().setRecipesId(usersSaveRecipes.getRecipes().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recipes recipes = usersSaveRecipes.getRecipes();
            if (recipes != null) {
                recipes = em.getReference(recipes.getClass(), recipes.getId());
                usersSaveRecipes.setRecipes(recipes);
            }
            Users users = usersSaveRecipes.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getId());
                usersSaveRecipes.setUsers(users);
            }
            em.persist(usersSaveRecipes);
            if (recipes != null) {
                recipes.getUsersSaveRecipesCollection().add(usersSaveRecipes);
                recipes = em.merge(recipes);
            }
            if (users != null) {
                users.getUsersSaveRecipesCollection().add(usersSaveRecipes);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsersSaveRecipes(usersSaveRecipes.getUsersSaveRecipesPK()) != null) {
                throw new PreexistingEntityException("UsersSaveRecipes " + usersSaveRecipes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsersSaveRecipes usersSaveRecipes) throws NonexistentEntityException, Exception {
        usersSaveRecipes.getUsersSaveRecipesPK().setUsersId(usersSaveRecipes.getUsers().getId());
        usersSaveRecipes.getUsersSaveRecipesPK().setRecipesId(usersSaveRecipes.getRecipes().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsersSaveRecipes persistentUsersSaveRecipes = em.find(UsersSaveRecipes.class, usersSaveRecipes.getUsersSaveRecipesPK());
            Recipes recipesOld = persistentUsersSaveRecipes.getRecipes();
            Recipes recipesNew = usersSaveRecipes.getRecipes();
            Users usersOld = persistentUsersSaveRecipes.getUsers();
            Users usersNew = usersSaveRecipes.getUsers();
            if (recipesNew != null) {
                recipesNew = em.getReference(recipesNew.getClass(), recipesNew.getId());
                usersSaveRecipes.setRecipes(recipesNew);
            }
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getId());
                usersSaveRecipes.setUsers(usersNew);
            }
            usersSaveRecipes = em.merge(usersSaveRecipes);
            if (recipesOld != null && !recipesOld.equals(recipesNew)) {
                recipesOld.getUsersSaveRecipesCollection().remove(usersSaveRecipes);
                recipesOld = em.merge(recipesOld);
            }
            if (recipesNew != null && !recipesNew.equals(recipesOld)) {
                recipesNew.getUsersSaveRecipesCollection().add(usersSaveRecipes);
                recipesNew = em.merge(recipesNew);
            }
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getUsersSaveRecipesCollection().remove(usersSaveRecipes);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getUsersSaveRecipesCollection().add(usersSaveRecipes);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UsersSaveRecipesPK id = usersSaveRecipes.getUsersSaveRecipesPK();
                if (findUsersSaveRecipes(id) == null) {
                    throw new NonexistentEntityException("The usersSaveRecipes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UsersSaveRecipesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsersSaveRecipes usersSaveRecipes;
            try {
                usersSaveRecipes = em.getReference(UsersSaveRecipes.class, id);
                usersSaveRecipes.getUsersSaveRecipesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usersSaveRecipes with id " + id + " no longer exists.", enfe);
            }
            Recipes recipes = usersSaveRecipes.getRecipes();
            if (recipes != null) {
                recipes.getUsersSaveRecipesCollection().remove(usersSaveRecipes);
                recipes = em.merge(recipes);
            }
            Users users = usersSaveRecipes.getUsers();
            if (users != null) {
                users.getUsersSaveRecipesCollection().remove(usersSaveRecipes);
                users = em.merge(users);
            }
            em.remove(usersSaveRecipes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsersSaveRecipes> findUsersSaveRecipesEntities() {
        return findUsersSaveRecipesEntities(true, -1, -1);
    }

    public List<UsersSaveRecipes> findUsersSaveRecipesEntities(int maxResults, int firstResult) {
        return findUsersSaveRecipesEntities(false, maxResults, firstResult);
    }

    private List<UsersSaveRecipes> findUsersSaveRecipesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsersSaveRecipes.class));
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

    public UsersSaveRecipes findUsersSaveRecipes(UsersSaveRecipesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsersSaveRecipes.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersSaveRecipesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsersSaveRecipes> rt = cq.from(UsersSaveRecipes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
