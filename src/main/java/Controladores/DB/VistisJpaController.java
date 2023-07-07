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
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Recipes;
import Entidades.Vistis;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class VistisJpaController implements Serializable {

    public VistisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vistis vistis) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recipes recipesId = vistis.getRecipesId();
            if (recipesId != null) {
                recipesId = em.getReference(recipesId.getClass(), recipesId.getId());
                vistis.setRecipesId(recipesId);
            }
            em.persist(vistis);
            if (recipesId != null) {
                recipesId.getVistisCollection().add(vistis);
                recipesId = em.merge(recipesId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vistis vistis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vistis persistentVistis = em.find(Vistis.class, vistis.getId());
            Recipes recipesIdOld = persistentVistis.getRecipesId();
            Recipes recipesIdNew = vistis.getRecipesId();
            if (recipesIdNew != null) {
                recipesIdNew = em.getReference(recipesIdNew.getClass(), recipesIdNew.getId());
                vistis.setRecipesId(recipesIdNew);
            }
            vistis = em.merge(vistis);
            if (recipesIdOld != null && !recipesIdOld.equals(recipesIdNew)) {
                recipesIdOld.getVistisCollection().remove(vistis);
                recipesIdOld = em.merge(recipesIdOld);
            }
            if (recipesIdNew != null && !recipesIdNew.equals(recipesIdOld)) {
                recipesIdNew.getVistisCollection().add(vistis);
                recipesIdNew = em.merge(recipesIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vistis.getId();
                if (findVistis(id) == null) {
                    throw new NonexistentEntityException("The vistis with id " + id + " no longer exists.");
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
            Vistis vistis;
            try {
                vistis = em.getReference(Vistis.class, id);
                vistis.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vistis with id " + id + " no longer exists.", enfe);
            }
            Recipes recipesId = vistis.getRecipesId();
            if (recipesId != null) {
                recipesId.getVistisCollection().remove(vistis);
                recipesId = em.merge(recipesId);
            }
            em.remove(vistis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vistis> findVistisEntities() {
        return findVistisEntities(true, -1, -1);
    }

    public List<Vistis> findVistisEntities(int maxResults, int firstResult) {
        return findVistisEntities(false, maxResults, firstResult);
    }

    private List<Vistis> findVistisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vistis.class));
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

    public Vistis findVistis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vistis.class, id);
        } finally {
            em.close();
        }
    }

    public int getVistisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vistis> rt = cq.from(Vistis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
