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
import Entidades.FeaturedRecipe;
import Entidades.FeaturedRecipePK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Recipes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class FeaturedRecipeJpaController implements Serializable {

    public FeaturedRecipeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FeaturedRecipe featuredRecipe) throws PreexistingEntityException, Exception {
        if (featuredRecipe.getFeaturedRecipePK() == null) {
            featuredRecipe.setFeaturedRecipePK(new FeaturedRecipePK());
        }
        featuredRecipe.getFeaturedRecipePK().setRecipesId(featuredRecipe.getRecipes().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recipes recipes = featuredRecipe.getRecipes();
            if (recipes != null) {
                recipes = em.getReference(recipes.getClass(), recipes.getId());
                featuredRecipe.setRecipes(recipes);
            }
            em.persist(featuredRecipe);
            if (recipes != null) {
                recipes.getFeaturedRecipeCollection().add(featuredRecipe);
                recipes = em.merge(recipes);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFeaturedRecipe(featuredRecipe.getFeaturedRecipePK()) != null) {
                throw new PreexistingEntityException("FeaturedRecipe " + featuredRecipe + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FeaturedRecipe featuredRecipe) throws NonexistentEntityException, Exception {
        featuredRecipe.getFeaturedRecipePK().setRecipesId(featuredRecipe.getRecipes().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FeaturedRecipe persistentFeaturedRecipe = em.find(FeaturedRecipe.class, featuredRecipe.getFeaturedRecipePK());
            Recipes recipesOld = persistentFeaturedRecipe.getRecipes();
            Recipes recipesNew = featuredRecipe.getRecipes();
            if (recipesNew != null) {
                recipesNew = em.getReference(recipesNew.getClass(), recipesNew.getId());
                featuredRecipe.setRecipes(recipesNew);
            }
            featuredRecipe = em.merge(featuredRecipe);
            if (recipesOld != null && !recipesOld.equals(recipesNew)) {
                recipesOld.getFeaturedRecipeCollection().remove(featuredRecipe);
                recipesOld = em.merge(recipesOld);
            }
            if (recipesNew != null && !recipesNew.equals(recipesOld)) {
                recipesNew.getFeaturedRecipeCollection().add(featuredRecipe);
                recipesNew = em.merge(recipesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FeaturedRecipePK id = featuredRecipe.getFeaturedRecipePK();
                if (findFeaturedRecipe(id) == null) {
                    throw new NonexistentEntityException("The featuredRecipe with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FeaturedRecipePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FeaturedRecipe featuredRecipe;
            try {
                featuredRecipe = em.getReference(FeaturedRecipe.class, id);
                featuredRecipe.getFeaturedRecipePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The featuredRecipe with id " + id + " no longer exists.", enfe);
            }
            Recipes recipes = featuredRecipe.getRecipes();
            if (recipes != null) {
                recipes.getFeaturedRecipeCollection().remove(featuredRecipe);
                recipes = em.merge(recipes);
            }
            em.remove(featuredRecipe);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FeaturedRecipe> findFeaturedRecipeEntities() {
        return findFeaturedRecipeEntities(true, -1, -1);
    }

    public List<FeaturedRecipe> findFeaturedRecipeEntities(int maxResults, int firstResult) {
        return findFeaturedRecipeEntities(false, maxResults, firstResult);
    }

    private List<FeaturedRecipe> findFeaturedRecipeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FeaturedRecipe.class));
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

    public FeaturedRecipe findFeaturedRecipe(FeaturedRecipePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FeaturedRecipe.class, id);
        } finally {
            em.close();
        }
    }

    public int getFeaturedRecipeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FeaturedRecipe> rt = cq.from(FeaturedRecipe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
