/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Receta;
import Modelo.Usuario;
import Modelo.UsuarioHasReceta;
import Modelo.UsuarioHasRecetaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author laura
 */
public class UsuarioHasRecetaJpaController implements Serializable {

    public UsuarioHasRecetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioHasReceta usuarioHasReceta) throws PreexistingEntityException, Exception {
        if (usuarioHasReceta.getUsuarioHasRecetaPK() == null) {
            usuarioHasReceta.setUsuarioHasRecetaPK(new UsuarioHasRecetaPK());
        }
        usuarioHasReceta.getUsuarioHasRecetaPK().setUsuarioId(usuarioHasReceta.getUsuario().getId());
        usuarioHasReceta.getUsuarioHasRecetaPK().setRecetaid(usuarioHasReceta.getReceta().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Receta receta = usuarioHasReceta.getReceta();
            if (receta != null) {
                receta = em.getReference(receta.getClass(), receta.getId());
                usuarioHasReceta.setReceta(receta);
            }
            Usuario usuario = usuarioHasReceta.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                usuarioHasReceta.setUsuario(usuario);
            }
            em.persist(usuarioHasReceta);
            if (receta != null) {
                receta.getUsuarioHasRecetaCollection().add(usuarioHasReceta);
                receta = em.merge(receta);
            }
            if (usuario != null) {
                usuario.getUsuarioHasRecetaCollection().add(usuarioHasReceta);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioHasReceta(usuarioHasReceta.getUsuarioHasRecetaPK()) != null) {
                throw new PreexistingEntityException("UsuarioHasReceta " + usuarioHasReceta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioHasReceta usuarioHasReceta) throws NonexistentEntityException, Exception {
        usuarioHasReceta.getUsuarioHasRecetaPK().setUsuarioId(usuarioHasReceta.getUsuario().getId());
        usuarioHasReceta.getUsuarioHasRecetaPK().setRecetaid(usuarioHasReceta.getReceta().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioHasReceta persistentUsuarioHasReceta = em.find(UsuarioHasReceta.class, usuarioHasReceta.getUsuarioHasRecetaPK());
            Receta recetaOld = persistentUsuarioHasReceta.getReceta();
            Receta recetaNew = usuarioHasReceta.getReceta();
            Usuario usuarioOld = persistentUsuarioHasReceta.getUsuario();
            Usuario usuarioNew = usuarioHasReceta.getUsuario();
            if (recetaNew != null) {
                recetaNew = em.getReference(recetaNew.getClass(), recetaNew.getId());
                usuarioHasReceta.setReceta(recetaNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                usuarioHasReceta.setUsuario(usuarioNew);
            }
            usuarioHasReceta = em.merge(usuarioHasReceta);
            if (recetaOld != null && !recetaOld.equals(recetaNew)) {
                recetaOld.getUsuarioHasRecetaCollection().remove(usuarioHasReceta);
                recetaOld = em.merge(recetaOld);
            }
            if (recetaNew != null && !recetaNew.equals(recetaOld)) {
                recetaNew.getUsuarioHasRecetaCollection().add(usuarioHasReceta);
                recetaNew = em.merge(recetaNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getUsuarioHasRecetaCollection().remove(usuarioHasReceta);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getUsuarioHasRecetaCollection().add(usuarioHasReceta);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UsuarioHasRecetaPK id = usuarioHasReceta.getUsuarioHasRecetaPK();
                if (findUsuarioHasReceta(id) == null) {
                    throw new NonexistentEntityException("The usuarioHasReceta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UsuarioHasRecetaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UsuarioHasReceta usuarioHasReceta;
            try {
                usuarioHasReceta = em.getReference(UsuarioHasReceta.class, id);
                usuarioHasReceta.getUsuarioHasRecetaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioHasReceta with id " + id + " no longer exists.", enfe);
            }
            Receta receta = usuarioHasReceta.getReceta();
            if (receta != null) {
                receta.getUsuarioHasRecetaCollection().remove(usuarioHasReceta);
                receta = em.merge(receta);
            }
            Usuario usuario = usuarioHasReceta.getUsuario();
            if (usuario != null) {
                usuario.getUsuarioHasRecetaCollection().remove(usuarioHasReceta);
                usuario = em.merge(usuario);
            }
            em.remove(usuarioHasReceta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioHasReceta> findUsuarioHasRecetaEntities() {
        return findUsuarioHasRecetaEntities(true, -1, -1);
    }

    public List<UsuarioHasReceta> findUsuarioHasRecetaEntities(int maxResults, int firstResult) {
        return findUsuarioHasRecetaEntities(false, maxResults, firstResult);
    }

    private List<UsuarioHasReceta> findUsuarioHasRecetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioHasReceta.class));
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

    public UsuarioHasReceta findUsuarioHasReceta(UsuarioHasRecetaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioHasReceta.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioHasRecetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioHasReceta> rt = cq.from(UsuarioHasReceta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
