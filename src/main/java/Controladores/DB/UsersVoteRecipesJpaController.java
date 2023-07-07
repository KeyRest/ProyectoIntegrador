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
import Entidades.Recipe;
import Entidades.User;
import Entidades.UsersVoteRecipes;
import Entidades.UsersVoteRecipesPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class UsersVoteRecipesJpaController implements Serializable {

    public UsersVoteRecipesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsersVoteRecipes usersVoteRecipes) throws PreexistingEntityException, Exception {
        if (usersVoteRecipes.getUsersVoteRecipesPK() == null) {
            usersVoteRecipes.setUsersVoteRecipesPK(new UsersVoteRecipesPK());
        }
        usersVoteRecipes.getUsersVoteRecipesPK().setRecipesId(usersVoteRecipes.getRecipes().getId());
        usersVoteRecipes.getUsersVoteRecipesPK().setUsersId(usersVoteRecipes.getUsers().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recipe recipes = usersVoteRecipes.getRecipes();
            if (recipes != null) {
                recipes = em.getReference(recipes.getClass(), recipes.getId());
                usersVoteRecipes.setRecipes(recipes);
            }
            User users = usersVoteRecipes.getUsers();
            if (users != null) {
                users = em.getReference(users.getClass(), users.getId());
                usersVoteRecipes.setUsers(users);
            }
            em.persist(usersVoteRecipes);
            if (recipes != null) {
                recipes.getUsersVoteRecipesCollection().add(usersVoteRecipes);
                recipes = em.merge(recipes);
            }
            if (users != null) {
                users.getUsersVoteRecipesCollection().add(usersVoteRecipes);
                users = em.merge(users);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsersVoteRecipes(usersVoteRecipes.getUsersVoteRecipesPK()) != null) {
                throw new PreexistingEntityException("UsersVoteRecipes " + usersVoteRecipes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsersVoteRecipes usersVoteRecipes) throws NonexistentEntityException, Exception {
        usersVoteRecipes.getUsersVoteRecipesPK().setRecipesId(usersVoteRecipes.getRecipes().getId());
        usersVoteRecipes.getUsersVoteRecipesPK().setUsersId(usersVoteRecipes.getUsers().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsersVoteRecipes persistentUsersVoteRecipes = em.find(UsersVoteRecipes.class, usersVoteRecipes.getUsersVoteRecipesPK());
            Recipe recipesOld = persistentUsersVoteRecipes.getRecipes();
            Recipe recipesNew = usersVoteRecipes.getRecipes();
            User usersOld = persistentUsersVoteRecipes.getUsers();
            User usersNew = usersVoteRecipes.getUsers();
            if (recipesNew != null) {
                recipesNew = em.getReference(recipesNew.getClass(), recipesNew.getId());
                usersVoteRecipes.setRecipes(recipesNew);
            }
            if (usersNew != null) {
                usersNew = em.getReference(usersNew.getClass(), usersNew.getId());
                usersVoteRecipes.setUsers(usersNew);
            }
            usersVoteRecipes = em.merge(usersVoteRecipes);
            if (recipesOld != null && !recipesOld.equals(recipesNew)) {
                recipesOld.getUsersVoteRecipesCollection().remove(usersVoteRecipes);
                recipesOld = em.merge(recipesOld);
            }
            if (recipesNew != null && !recipesNew.equals(recipesOld)) {
                recipesNew.getUsersVoteRecipesCollection().add(usersVoteRecipes);
                recipesNew = em.merge(recipesNew);
            }
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getUsersVoteRecipesCollection().remove(usersVoteRecipes);
                usersOld = em.merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getUsersVoteRecipesCollection().add(usersVoteRecipes);
                usersNew = em.merge(usersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UsersVoteRecipesPK id = usersVoteRecipes.getUsersVoteRecipesPK();
                if (findUsersVoteRecipes(id) == null) {
                    throw new NonexistentEntityException("The usersVoteRecipes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UsersVoteRecipesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsersVoteRecipes usersVoteRecipes;
            try {
                usersVoteRecipes = em.getReference(UsersVoteRecipes.class, id);
                usersVoteRecipes.getUsersVoteRecipesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usersVoteRecipes with id " + id + " no longer exists.", enfe);
            }
            Recipe recipes = usersVoteRecipes.getRecipes();
            if (recipes != null) {
                recipes.getUsersVoteRecipesCollection().remove(usersVoteRecipes);
                recipes = em.merge(recipes);
            }
            User users = usersVoteRecipes.getUsers();
            if (users != null) {
                users.getUsersVoteRecipesCollection().remove(usersVoteRecipes);
                users = em.merge(users);
            }
            em.remove(usersVoteRecipes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsersVoteRecipes> findUsersVoteRecipesEntities() {
        return findUsersVoteRecipesEntities(true, -1, -1);
    }

    public List<UsersVoteRecipes> findUsersVoteRecipesEntities(int maxResults, int firstResult) {
        return findUsersVoteRecipesEntities(false, maxResults, firstResult);
    }

    private List<UsersVoteRecipes> findUsersVoteRecipesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsersVoteRecipes.class));
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

    public UsersVoteRecipes findUsersVoteRecipes(UsersVoteRecipesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsersVoteRecipes.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersVoteRecipesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsersVoteRecipes> rt = cq.from(UsersVoteRecipes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
