/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import Modelo.Complejidad;
import Modelo.ComplejidadPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Receta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author laura
 */
public class ComplejidadJpaController implements Serializable {

    public ComplejidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Complejidad complejidad) throws PreexistingEntityException, Exception {
        if (complejidad.getComplejidadPK() == null) {
            complejidad.setComplejidadPK(new ComplejidadPK());
        }
        complejidad.getComplejidadPK().setRecetaid(complejidad.getReceta().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta receta = complejidad.getReceta();
            if (receta != null) {
                receta = em.getReference(receta.getClass(), receta.getId());
                complejidad.setReceta(receta);
            }
            em.persist(complejidad);
            if (receta != null) {
                receta.getComplejidadCollection().add(complejidad);
                receta = em.merge(receta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComplejidad(complejidad.getComplejidadPK()) != null) {
                throw new PreexistingEntityException("Complejidad " + complejidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Complejidad complejidad) throws NonexistentEntityException, Exception {
        complejidad.getComplejidadPK().setRecetaid(complejidad.getReceta().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Complejidad persistentComplejidad = em.find(Complejidad.class, complejidad.getComplejidadPK());
            Receta recetaOld = persistentComplejidad.getReceta();
            Receta recetaNew = complejidad.getReceta();
            if (recetaNew != null) {
                recetaNew = em.getReference(recetaNew.getClass(), recetaNew.getId());
                complejidad.setReceta(recetaNew);
            }
            complejidad = em.merge(complejidad);
            if (recetaOld != null && !recetaOld.equals(recetaNew)) {
                recetaOld.getComplejidadCollection().remove(complejidad);
                recetaOld = em.merge(recetaOld);
            }
            if (recetaNew != null && !recetaNew.equals(recetaOld)) {
                recetaNew.getComplejidadCollection().add(complejidad);
                recetaNew = em.merge(recetaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ComplejidadPK id = complejidad.getComplejidadPK();
                if (findComplejidad(id) == null) {
                    throw new NonexistentEntityException("The complejidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ComplejidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Complejidad complejidad;
            try {
                complejidad = em.getReference(Complejidad.class, id);
                complejidad.getComplejidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The complejidad with id " + id + " no longer exists.", enfe);
            }
            Receta receta = complejidad.getReceta();
            if (receta != null) {
                receta.getComplejidadCollection().remove(complejidad);
                receta = em.merge(receta);
            }
            em.remove(complejidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Complejidad> findComplejidadEntities() {
        return findComplejidadEntities(true, -1, -1);
    }

    public List<Complejidad> findComplejidadEntities(int maxResults, int firstResult) {
        return findComplejidadEntities(false, maxResults, firstResult);
    }

    private List<Complejidad> findComplejidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Complejidad.class));
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

    public Complejidad findComplejidad(ComplejidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Complejidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getComplejidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Complejidad> rt = cq.from(Complejidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
