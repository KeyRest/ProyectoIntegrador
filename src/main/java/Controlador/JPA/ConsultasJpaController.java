/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import Modelo.Consultas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Receta;
import java.util.ArrayList;
import java.util.Collection;
import Modelo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author laura
 */
public class ConsultasJpaController implements Serializable {

    public ConsultasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consultas consultas) throws PreexistingEntityException, Exception {
        if (consultas.getRecetaCollection() == null) {
            consultas.setRecetaCollection(new ArrayList<Receta>());
        }
        if (consultas.getUsuarioCollection() == null) {
            consultas.setUsuarioCollection(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Receta> attachedRecetaCollection = new ArrayList<Receta>();
            for (Receta recetaCollectionRecetaToAttach : consultas.getRecetaCollection()) {
                recetaCollectionRecetaToAttach = em.getReference(recetaCollectionRecetaToAttach.getClass(), recetaCollectionRecetaToAttach.getId());
                attachedRecetaCollection.add(recetaCollectionRecetaToAttach);
            }
            consultas.setRecetaCollection(attachedRecetaCollection);
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : consultas.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getId());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            consultas.setUsuarioCollection(attachedUsuarioCollection);
            em.persist(consultas);
            for (Receta recetaCollectionReceta : consultas.getRecetaCollection()) {
                recetaCollectionReceta.getConsultasCollection().add(consultas);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            for (Usuario usuarioCollectionUsuario : consultas.getUsuarioCollection()) {
                usuarioCollectionUsuario.getConsultasCollection().add(consultas);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConsultas(consultas.getId()) != null) {
                throw new PreexistingEntityException("Consultas " + consultas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consultas consultas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consultas persistentConsultas = em.find(Consultas.class, consultas.getId());
            Collection<Receta> recetaCollectionOld = persistentConsultas.getRecetaCollection();
            Collection<Receta> recetaCollectionNew = consultas.getRecetaCollection();
            Collection<Usuario> usuarioCollectionOld = persistentConsultas.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = consultas.getUsuarioCollection();
            Collection<Receta> attachedRecetaCollectionNew = new ArrayList<Receta>();
            for (Receta recetaCollectionNewRecetaToAttach : recetaCollectionNew) {
                recetaCollectionNewRecetaToAttach = em.getReference(recetaCollectionNewRecetaToAttach.getClass(), recetaCollectionNewRecetaToAttach.getId());
                attachedRecetaCollectionNew.add(recetaCollectionNewRecetaToAttach);
            }
            recetaCollectionNew = attachedRecetaCollectionNew;
            consultas.setRecetaCollection(recetaCollectionNew);
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getId());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            consultas.setUsuarioCollection(usuarioCollectionNew);
            consultas = em.merge(consultas);
            for (Receta recetaCollectionOldReceta : recetaCollectionOld) {
                if (!recetaCollectionNew.contains(recetaCollectionOldReceta)) {
                    recetaCollectionOldReceta.getConsultasCollection().remove(consultas);
                    recetaCollectionOldReceta = em.merge(recetaCollectionOldReceta);
                }
            }
            for (Receta recetaCollectionNewReceta : recetaCollectionNew) {
                if (!recetaCollectionOld.contains(recetaCollectionNewReceta)) {
                    recetaCollectionNewReceta.getConsultasCollection().add(consultas);
                    recetaCollectionNewReceta = em.merge(recetaCollectionNewReceta);
                }
            }
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.getConsultasCollection().remove(consultas);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    usuarioCollectionNewUsuario.getConsultasCollection().add(consultas);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consultas.getId();
                if (findConsultas(id) == null) {
                    throw new NonexistentEntityException("The consultas with id " + id + " no longer exists.");
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
            Consultas consultas;
            try {
                consultas = em.getReference(Consultas.class, id);
                consultas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consultas with id " + id + " no longer exists.", enfe);
            }
            Collection<Receta> recetaCollection = consultas.getRecetaCollection();
            for (Receta recetaCollectionReceta : recetaCollection) {
                recetaCollectionReceta.getConsultasCollection().remove(consultas);
                recetaCollectionReceta = em.merge(recetaCollectionReceta);
            }
            Collection<Usuario> usuarioCollection = consultas.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                usuarioCollectionUsuario.getConsultasCollection().remove(consultas);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.remove(consultas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consultas> findConsultasEntities() {
        return findConsultasEntities(true, -1, -1);
    }

    public List<Consultas> findConsultasEntities(int maxResults, int firstResult) {
        return findConsultasEntities(false, maxResults, firstResult);
    }

    private List<Consultas> findConsultasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consultas.class));
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

    public Consultas findConsultas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consultas.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsultasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consultas> rt = cq.from(Consultas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
