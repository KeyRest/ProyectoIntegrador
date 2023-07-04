/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import Modelo.Destacada;
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
public class DestacadaJpaController implements Serializable {

    public DestacadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Destacada destacada) throws PreexistingEntityException, Exception {
        if (destacada.getRecetaCollection() == null) {
            destacada.setRecetaCollection(new ArrayList<Receta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Receta> attachedRecetaCollection = new ArrayList<Receta>();
            for (Receta recetaCollectionRecetaToAttach : destacada.getRecetaCollection()) {
                recetaCollectionRecetaToAttach = em.getReference(recetaCollectionRecetaToAttach.getClass(), recetaCollectionRecetaToAttach.getId());
                attachedRecetaCollection.add(recetaCollectionRecetaToAttach);
            }
            destacada.setRecetaCollection(attachedRecetaCollection);
            em.persist(destacada);
            for (Receta recetaCollectionReceta : destacada.getRecetaCollection()) {
                recetaCollectionReceta.getDestacadaCollection().add(destacada);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDestacada(destacada.getId()) != null) {
                throw new PreexistingEntityException("Destacada " + destacada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Destacada destacada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Destacada persistentDestacada = em.find(Destacada.class, destacada.getId());
            Collection<Receta> recetaCollectionOld = persistentDestacada.getRecetaCollection();
            Collection<Receta> recetaCollectionNew = destacada.getRecetaCollection();
            Collection<Receta> attachedRecetaCollectionNew = new ArrayList<Receta>();
            for (Receta recetaCollectionNewRecetaToAttach : recetaCollectionNew) {
                recetaCollectionNewRecetaToAttach = em.getReference(recetaCollectionNewRecetaToAttach.getClass(), recetaCollectionNewRecetaToAttach.getId());
                attachedRecetaCollectionNew.add(recetaCollectionNewRecetaToAttach);
            }
            recetaCollectionNew = attachedRecetaCollectionNew;
            destacada.setRecetaCollection(recetaCollectionNew);
            destacada = em.merge(destacada);
            for (Receta recetaCollectionOldReceta : recetaCollectionOld) {
                if (!recetaCollectionNew.contains(recetaCollectionOldReceta)) {
                    recetaCollectionOldReceta.getDestacadaCollection().remove(destacada);
                    recetaCollectionOldReceta = em.merge(recetaCollectionOldReceta);
                }
            }
            for (Receta recetaCollectionNewReceta : recetaCollectionNew) {
                if (!recetaCollectionOld.contains(recetaCollectionNewReceta)) {
                    recetaCollectionNewReceta.getDestacadaCollection().add(destacada);
                    recetaCollectionNewReceta = em.merge(recetaCollectionNewReceta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = destacada.getId();
                if (findDestacada(id) == null) {
                    throw new NonexistentEntityException("The destacada with id " + id + " no longer exists.");
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
            Destacada destacada;
            try {
                destacada = em.getReference(Destacada.class, id);
                destacada.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The destacada with id " + id + " no longer exists.", enfe);
            }
            Collection<Receta> recetaCollection = destacada.getRecetaCollection();
            for (Receta recetaCollectionReceta : recetaCollection) {
                recetaCollectionReceta.getDestacadaCollection().remove(destacada);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            em.remove(destacada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Destacada> findDestacadaEntities() {
        return findDestacadaEntities(true, -1, -1);
    }

    public List<Destacada> findDestacadaEntities(int maxResults, int firstResult) {
        return findDestacadaEntities(false, maxResults, firstResult);
    }

    private List<Destacada> findDestacadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Destacada.class));
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

    public Destacada findDestacada(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Destacada.class, id);
        } finally {
            em.close();
        }
    }

    public int getDestacadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Destacada> rt = cq.from(Destacada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
