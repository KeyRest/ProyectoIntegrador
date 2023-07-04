/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import Modelo.Perfil;
import Modelo.PerfilPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Usuario;
import Modelo.Privilegio;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author laura
 */
public class PerfilJpaController implements Serializable {

    public PerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfil perfil) throws PreexistingEntityException, Exception {
        if (perfil.getPerfilPK() == null) {
            perfil.setPerfilPK(new PerfilPK());
        }
        if (perfil.getPrivilegioCollection() == null) {
            perfil.setPrivilegioCollection(new ArrayList<Privilegio>());
        }
        perfil.getPerfilPK().setUsuarioId(perfil.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = perfil.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                perfil.setUsuario(usuario);
            }
            Collection<Privilegio> attachedPrivilegioCollection = new ArrayList<Privilegio>();
            for (Privilegio privilegioCollectionPrivilegioToAttach : perfil.getPrivilegioCollection()) {
                privilegioCollectionPrivilegioToAttach = em.getReference(privilegioCollectionPrivilegioToAttach.getClass(), privilegioCollectionPrivilegioToAttach.getId());
                attachedPrivilegioCollection.add(privilegioCollectionPrivilegioToAttach);
            }
            perfil.setPrivilegioCollection(attachedPrivilegioCollection);
            em.persist(perfil);
            if (usuario != null) {
                usuario.getPerfilCollection().add(perfil);
                usuario = em.merge(usuario);
            }
            for (Privilegio privilegioCollectionPrivilegio : perfil.getPrivilegioCollection()) {
                privilegioCollectionPrivilegio.getPerfilCollection().add(perfil);
                privilegioCollectionPrivilegio = em.merge(privilegioCollectionPrivilegio);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerfil(perfil.getPerfilPK()) != null) {
                throw new PreexistingEntityException("Perfil " + perfil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfil perfil) throws NonexistentEntityException, Exception {
        perfil.getPerfilPK().setUsuarioId(perfil.getUsuario().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getPerfilPK());
            Usuario usuarioOld = persistentPerfil.getUsuario();
            Usuario usuarioNew = perfil.getUsuario();
            Collection<Privilegio> privilegioCollectionOld = persistentPerfil.getPrivilegioCollection();
            Collection<Privilegio> privilegioCollectionNew = perfil.getPrivilegioCollection();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                perfil.setUsuario(usuarioNew);
            }
            Collection<Privilegio> attachedPrivilegioCollectionNew = new ArrayList<Privilegio>();
            for (Privilegio privilegioCollectionNewPrivilegioToAttach : privilegioCollectionNew) {
                privilegioCollectionNewPrivilegioToAttach = em.getReference(privilegioCollectionNewPrivilegioToAttach.getClass(), privilegioCollectionNewPrivilegioToAttach.getId());
                attachedPrivilegioCollectionNew.add(privilegioCollectionNewPrivilegioToAttach);
            }
            privilegioCollectionNew = attachedPrivilegioCollectionNew;
            perfil.setPrivilegioCollection(privilegioCollectionNew);
            perfil = em.merge(perfil);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPerfilCollection().remove(perfil);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPerfilCollection().add(perfil);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Privilegio privilegioCollectionOldPrivilegio : privilegioCollectionOld) {
                if (!privilegioCollectionNew.contains(privilegioCollectionOldPrivilegio)) {
                    privilegioCollectionOldPrivilegio.getPerfilCollection().remove(perfil);
                    privilegioCollectionOldPrivilegio = em.merge(privilegioCollectionOldPrivilegio);
                }
            }
            for (Privilegio privilegioCollectionNewPrivilegio : privilegioCollectionNew) {
                if (!privilegioCollectionOld.contains(privilegioCollectionNewPrivilegio)) {
                    privilegioCollectionNewPrivilegio.getPerfilCollection().add(perfil);
                    privilegioCollectionNewPrivilegio = em.merge(privilegioCollectionNewPrivilegio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PerfilPK id = perfil.getPerfilPK();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PerfilPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getPerfilPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = perfil.getUsuario();
            if (usuario != null) {
                usuario.getPerfilCollection().remove(perfil);
                usuario = em.merge(usuario);
            }
            Collection<Privilegio> privilegioCollection = perfil.getPrivilegioCollection();
            for (Privilegio privilegioCollectionPrivilegio : privilegioCollection) {
                privilegioCollectionPrivilegio.getPerfilCollection().remove(perfil);
                privilegioCollectionPrivilegio = em.merge(privilegioCollectionPrivilegio);
            }
            em.remove(perfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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

    public Perfil findPerfil(PerfilPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
