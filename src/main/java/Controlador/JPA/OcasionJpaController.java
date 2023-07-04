/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import Modelo.Ocasion;
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

/**
 *
 * @author laura
 */
public class OcasionJpaController implements Serializable {

    public OcasionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ocasion ocasion) throws PreexistingEntityException, Exception {
        if (ocasion.getRecetaCollection() == null) {
            ocasion.setRecetaCollection(new ArrayList<Receta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Receta> attachedRecetaCollection = new ArrayList<Receta>();
            for (Receta recetaCollectionRecetaToAttach : ocasion.getRecetaCollection()) {
                recetaCollectionRecetaToAttach = em.getReference(recetaCollectionRecetaToAttach.getClass(), recetaCollectionRecetaToAttach.getId());
                attachedRecetaCollection.add(recetaCollectionRecetaToAttach);
            }
            ocasion.setRecetaCollection(attachedRecetaCollection);
            em.persist(ocasion);
            for (Receta recetaCollectionReceta : ocasion.getRecetaCollection()) {
                recetaCollectionReceta.getOcasionCollection().add(ocasion);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findOcasion(ocasion.getId()) != null) {
                throw new PreexistingEntityException("Ocasion " + ocasion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ocasion ocasion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ocasion persistentOcasion = em.find(Ocasion.class, ocasion.getId());
            Collection<Receta> recetaCollectionOld = persistentOcasion.getRecetaCollection();
            Collection<Receta> recetaCollectionNew = ocasion.getRecetaCollection();
            Collection<Receta> attachedRecetaCollectionNew = new ArrayList<Receta>();
            for (Receta recetaCollectionNewRecetaToAttach : recetaCollectionNew) {
                recetaCollectionNewRecetaToAttach = em.getReference(recetaCollectionNewRecetaToAttach.getClass(), recetaCollectionNewRecetaToAttach.getId());
                attachedRecetaCollectionNew.add(recetaCollectionNewRecetaToAttach);
            }
            recetaCollectionNew = attachedRecetaCollectionNew;
            ocasion.setRecetaCollection(recetaCollectionNew);
            ocasion = em.merge(ocasion);
            for (Receta recetaCollectionOldReceta : recetaCollectionOld) {
                if (!recetaCollectionNew.contains(recetaCollectionOldReceta)) {
                    recetaCollectionOldReceta.getOcasionCollection().remove(ocasion);
                    recetaCollectionOldReceta = em.merge(recetaCollectionOldReceta);
                }
            }
            for (Receta recetaCollectionNewReceta : recetaCollectionNew) {
                if (!recetaCollectionOld.contains(recetaCollectionNewReceta)) {
                    recetaCollectionNewReceta.getOcasionCollection().add(ocasion);
                    recetaCollectionNewReceta = em.merge(recetaCollectionNewReceta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ocasion.getId();
                if (findOcasion(id) == null) {
                    throw new NonexistentEntityException("The ocasion with id " + id + " no longer exists.");
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
            Ocasion ocasion;
            try {
                ocasion = em.getReference(Ocasion.class, id);
                ocasion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ocasion with id " + id + " no longer exists.", enfe);
            }
            Collection<Receta> recetaCollection = ocasion.getRecetaCollection();
            for (Receta recetaCollectionReceta : recetaCollection) {
                recetaCollectionReceta.getOcasionCollection().remove(ocasion);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            em.remove(ocasion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ocasion> findOcasionEntities() {
        return findOcasionEntities(true, -1, -1);
    }

    public List<Ocasion> findOcasionEntities(int maxResults, int firstResult) {
        return findOcasionEntities(false, maxResults, firstResult);
    }

    private List<Ocasion> findOcasionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ocasion.class));
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

    public Ocasion findOcasion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ocasion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOcasionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ocasion> rt = cq.from(Ocasion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
