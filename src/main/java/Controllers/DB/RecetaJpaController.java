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
import Modelo.Receta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class RecetaJpaController implements Serializable {

    public RecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Receta receta) throws PreexistingEntityException, Exception {
        if (receta.getRecetaCollection() == null) {
            receta.setRecetaCollection(new ArrayList<Receta>());
        }
        if (receta.getRecetaCollection1() == null) {
            receta.setRecetaCollection1(new ArrayList<Receta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Receta> attachedRecetaCollection = new ArrayList<Receta>();
            for (Receta recetaCollectionRecetaToAttach : receta.getRecetaCollection()) {
                recetaCollectionRecetaToAttach = em.getReference(recetaCollectionRecetaToAttach.getClass(), recetaCollectionRecetaToAttach.getId());
                attachedRecetaCollection.add(recetaCollectionRecetaToAttach);
            }
            receta.setRecetaCollection(attachedRecetaCollection);
            Collection<Receta> attachedRecetaCollection1 = new ArrayList<Receta>();
            for (Receta recetaCollection1RecetaToAttach : receta.getRecetaCollection1()) {
                recetaCollection1RecetaToAttach = em.getReference(recetaCollection1RecetaToAttach.getClass(), recetaCollection1RecetaToAttach.getId());
                attachedRecetaCollection1.add(recetaCollection1RecetaToAttach);
            }
            receta.setRecetaCollection1(attachedRecetaCollection1);
            em.persist(receta);
            for (Receta recetaCollectionReceta : receta.getRecetaCollection()) {
                recetaCollectionReceta.getRecetaCollection().add(receta);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            for (Receta recetaCollection1Receta : receta.getRecetaCollection1()) {
                recetaCollection1Receta.getRecetaCollection().add(receta);
                recetaCollection1Receta = em.merge(recetaCollection1Receta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReceta(receta.getId()) != null) {
                throw new PreexistingEntityException("Receta " + receta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Receta receta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta persistentReceta = em.find(Receta.class, receta.getId());
            Collection<Receta> recetaCollectionOld = persistentReceta.getRecetaCollection();
            Collection<Receta> recetaCollectionNew = receta.getRecetaCollection();
            Collection<Receta> recetaCollection1Old = persistentReceta.getRecetaCollection1();
            Collection<Receta> recetaCollection1New = receta.getRecetaCollection1();
            Collection<Receta> attachedRecetaCollectionNew = new ArrayList<Receta>();
            for (Receta recetaCollectionNewRecetaToAttach : recetaCollectionNew) {
                recetaCollectionNewRecetaToAttach = em.getReference(recetaCollectionNewRecetaToAttach.getClass(), recetaCollectionNewRecetaToAttach.getId());
                attachedRecetaCollectionNew.add(recetaCollectionNewRecetaToAttach);
            }
            recetaCollectionNew = attachedRecetaCollectionNew;
            receta.setRecetaCollection(recetaCollectionNew);
            Collection<Receta> attachedRecetaCollection1New = new ArrayList<Receta>();
            for (Receta recetaCollection1NewRecetaToAttach : recetaCollection1New) {
                recetaCollection1NewRecetaToAttach = em.getReference(recetaCollection1NewRecetaToAttach.getClass(), recetaCollection1NewRecetaToAttach.getId());
                attachedRecetaCollection1New.add(recetaCollection1NewRecetaToAttach);
            }
            recetaCollection1New = attachedRecetaCollection1New;
            receta.setRecetaCollection1(recetaCollection1New);
            receta = em.merge(receta);
            for (Receta recetaCollectionOldReceta : recetaCollectionOld) {
                if (!recetaCollectionNew.contains(recetaCollectionOldReceta)) {
                    recetaCollectionOldReceta.getRecetaCollection().remove(receta);
                    recetaCollectionOldReceta = em.merge(recetaCollectionOldReceta);
                }
            }
            for (Receta recetaCollectionNewReceta : recetaCollectionNew) {
                if (!recetaCollectionOld.contains(recetaCollectionNewReceta)) {
                    recetaCollectionNewReceta.getRecetaCollection().add(receta);
                    recetaCollectionNewReceta = em.merge(recetaCollectionNewReceta);
                }
            }
            for (Receta recetaCollection1OldReceta : recetaCollection1Old) {
                if (!recetaCollection1New.contains(recetaCollection1OldReceta)) {
                    recetaCollection1OldReceta.getRecetaCollection().remove(receta);
                    recetaCollection1OldReceta = em.merge(recetaCollection1OldReceta);
                }
            }
            for (Receta recetaCollection1NewReceta : recetaCollection1New) {
                if (!recetaCollection1Old.contains(recetaCollection1NewReceta)) {
                    recetaCollection1NewReceta.getRecetaCollection().add(receta);
                    recetaCollection1NewReceta = em.merge(recetaCollection1NewReceta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = receta.getId();
                if (findReceta(id) == null) {
                    throw new NonexistentEntityException("The receta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta receta;
            try {
                receta = em.getReference(Receta.class, id);
                receta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The receta with id " + id + " no longer exists.", enfe);
            }
            Collection<Receta> recetaCollection = receta.getRecetaCollection();
            for (Receta recetaCollectionReceta : recetaCollection) {
                recetaCollectionReceta.getRecetaCollection().remove(receta);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            Collection<Receta> recetaCollection1 = receta.getRecetaCollection1();
            for (Receta recetaCollection1Receta : recetaCollection1) {
                recetaCollection1Receta.getRecetaCollection().remove(receta);
                recetaCollection1Receta = em.merge(recetaCollection1Receta);
            }
            em.remove(receta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Receta> findRecetaEntities() {
        return findRecetaEntities(true, -1, -1);
    }

    public List<Receta> findRecetaEntities(int maxResults, int firstResult) {
        return findRecetaEntities(false, maxResults, firstResult);
    }

    private List<Receta> findRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Receta.class));
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

    public Receta findReceta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Receta.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Receta> rt = cq.from(Receta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
