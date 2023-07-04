/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.IllegalOrphanException;
import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Consultas;
import java.util.ArrayList;
import java.util.Collection;
import Modelo.UsuarioHasReceta;
import Modelo.Perfil;
import Modelo.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author laura
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getConsultasCollection() == null) {
            usuario.setConsultasCollection(new ArrayList<Consultas>());
        }
        if (usuario.getUsuarioHasRecetaCollection() == null) {
            usuario.setUsuarioHasRecetaCollection(new ArrayList<UsuarioHasReceta>());
        }
        if (usuario.getPerfilCollection() == null) {
            usuario.setPerfilCollection(new ArrayList<Perfil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Consultas> attachedConsultasCollection = new ArrayList<Consultas>();
            for (Consultas consultasCollectionConsultasToAttach : usuario.getConsultasCollection()) {
                consultasCollectionConsultasToAttach = em.getReference(consultasCollectionConsultasToAttach.getClass(), consultasCollectionConsultasToAttach.getId());
                attachedConsultasCollection.add(consultasCollectionConsultasToAttach);
            }
            usuario.setConsultasCollection(attachedConsultasCollection);
            Collection<UsuarioHasReceta> attachedUsuarioHasRecetaCollection = new ArrayList<UsuarioHasReceta>();
            for (UsuarioHasReceta usuarioHasRecetaCollectionUsuarioHasRecetaToAttach : usuario.getUsuarioHasRecetaCollection()) {
                usuarioHasRecetaCollectionUsuarioHasRecetaToAttach = em.getReference(usuarioHasRecetaCollectionUsuarioHasRecetaToAttach.getClass(), usuarioHasRecetaCollectionUsuarioHasRecetaToAttach.getUsuarioHasRecetaPK());
                attachedUsuarioHasRecetaCollection.add(usuarioHasRecetaCollectionUsuarioHasRecetaToAttach);
            }
            usuario.setUsuarioHasRecetaCollection(attachedUsuarioHasRecetaCollection);
            Collection<Perfil> attachedPerfilCollection = new ArrayList<Perfil>();
            for (Perfil perfilCollectionPerfilToAttach : usuario.getPerfilCollection()) {
                perfilCollectionPerfilToAttach = em.getReference(perfilCollectionPerfilToAttach.getClass(), perfilCollectionPerfilToAttach.getPerfilPK());
                attachedPerfilCollection.add(perfilCollectionPerfilToAttach);
            }
            usuario.setPerfilCollection(attachedPerfilCollection);
            em.persist(usuario);
            for (Consultas consultasCollectionConsultas : usuario.getConsultasCollection()) {
                consultasCollectionConsultas.getUsuarioCollection().add(usuario);
                consultasCollectionConsultas = em.merge(consultasCollectionConsultas);
            }
            for (UsuarioHasReceta usuarioHasRecetaCollectionUsuarioHasReceta : usuario.getUsuarioHasRecetaCollection()) {
                Usuario oldUsuarioOfUsuarioHasRecetaCollectionUsuarioHasReceta = usuarioHasRecetaCollectionUsuarioHasReceta.getUsuario();
                usuarioHasRecetaCollectionUsuarioHasReceta.setUsuario(usuario);
                usuarioHasRecetaCollectionUsuarioHasReceta = em.merge(usuarioHasRecetaCollectionUsuarioHasReceta);
                if (oldUsuarioOfUsuarioHasRecetaCollectionUsuarioHasReceta != null) {
                    oldUsuarioOfUsuarioHasRecetaCollectionUsuarioHasReceta.getUsuarioHasRecetaCollection().remove(usuarioHasRecetaCollectionUsuarioHasReceta);
                    oldUsuarioOfUsuarioHasRecetaCollectionUsuarioHasReceta = em.merge(oldUsuarioOfUsuarioHasRecetaCollectionUsuarioHasReceta);
                }
            }
            for (Perfil perfilCollectionPerfil : usuario.getPerfilCollection()) {
                Usuario oldUsuarioOfPerfilCollectionPerfil = perfilCollectionPerfil.getUsuario();
                perfilCollectionPerfil.setUsuario(usuario);
                perfilCollectionPerfil = em.merge(perfilCollectionPerfil);
                if (oldUsuarioOfPerfilCollectionPerfil != null) {
                    oldUsuarioOfPerfilCollectionPerfil.getPerfilCollection().remove(perfilCollectionPerfil);
                    oldUsuarioOfPerfilCollectionPerfil = em.merge(oldUsuarioOfPerfilCollectionPerfil);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getId()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Collection<Consultas> consultasCollectionOld = persistentUsuario.getConsultasCollection();
            Collection<Consultas> consultasCollectionNew = usuario.getConsultasCollection();
            Collection<UsuarioHasReceta> usuarioHasRecetaCollectionOld = persistentUsuario.getUsuarioHasRecetaCollection();
            Collection<UsuarioHasReceta> usuarioHasRecetaCollectionNew = usuario.getUsuarioHasRecetaCollection();
            Collection<Perfil> perfilCollectionOld = persistentUsuario.getPerfilCollection();
            Collection<Perfil> perfilCollectionNew = usuario.getPerfilCollection();
            List<String> illegalOrphanMessages = null;
            for (UsuarioHasReceta usuarioHasRecetaCollectionOldUsuarioHasReceta : usuarioHasRecetaCollectionOld) {
                if (!usuarioHasRecetaCollectionNew.contains(usuarioHasRecetaCollectionOldUsuarioHasReceta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsuarioHasReceta " + usuarioHasRecetaCollectionOldUsuarioHasReceta + " since its usuario field is not nullable.");
                }
            }
            for (Perfil perfilCollectionOldPerfil : perfilCollectionOld) {
                if (!perfilCollectionNew.contains(perfilCollectionOldPerfil)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Perfil " + perfilCollectionOldPerfil + " since its usuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Consultas> attachedConsultasCollectionNew = new ArrayList<Consultas>();
            for (Consultas consultasCollectionNewConsultasToAttach : consultasCollectionNew) {
                consultasCollectionNewConsultasToAttach = em.getReference(consultasCollectionNewConsultasToAttach.getClass(), consultasCollectionNewConsultasToAttach.getId());
                attachedConsultasCollectionNew.add(consultasCollectionNewConsultasToAttach);
            }
            consultasCollectionNew = attachedConsultasCollectionNew;
            usuario.setConsultasCollection(consultasCollectionNew);
            Collection<UsuarioHasReceta> attachedUsuarioHasRecetaCollectionNew = new ArrayList<UsuarioHasReceta>();
            for (UsuarioHasReceta usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach : usuarioHasRecetaCollectionNew) {
                usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach = em.getReference(usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach.getClass(), usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach.getUsuarioHasRecetaPK());
                attachedUsuarioHasRecetaCollectionNew.add(usuarioHasRecetaCollectionNewUsuarioHasRecetaToAttach);
            }
            usuarioHasRecetaCollectionNew = attachedUsuarioHasRecetaCollectionNew;
            usuario.setUsuarioHasRecetaCollection(usuarioHasRecetaCollectionNew);
            Collection<Perfil> attachedPerfilCollectionNew = new ArrayList<Perfil>();
            for (Perfil perfilCollectionNewPerfilToAttach : perfilCollectionNew) {
                perfilCollectionNewPerfilToAttach = em.getReference(perfilCollectionNewPerfilToAttach.getClass(), perfilCollectionNewPerfilToAttach.getPerfilPK());
                attachedPerfilCollectionNew.add(perfilCollectionNewPerfilToAttach);
            }
            perfilCollectionNew = attachedPerfilCollectionNew;
            usuario.setPerfilCollection(perfilCollectionNew);
            usuario = em.merge(usuario);
            for (Consultas consultasCollectionOldConsultas : consultasCollectionOld) {
                if (!consultasCollectionNew.contains(consultasCollectionOldConsultas)) {
                    consultasCollectionOldConsultas.getUsuarioCollection().remove(usuario);
                    consultasCollectionOldConsultas = em.merge(consultasCollectionOldConsultas);
                }
            }
            for (Consultas consultasCollectionNewConsultas : consultasCollectionNew) {
                if (!consultasCollectionOld.contains(consultasCollectionNewConsultas)) {
                    consultasCollectionNewConsultas.getUsuarioCollection().add(usuario);
                    consultasCollectionNewConsultas = em.merge(consultasCollectionNewConsultas);
                }
            }
            for (UsuarioHasReceta usuarioHasRecetaCollectionNewUsuarioHasReceta : usuarioHasRecetaCollectionNew) {
                if (!usuarioHasRecetaCollectionOld.contains(usuarioHasRecetaCollectionNewUsuarioHasReceta)) {
                    Usuario oldUsuarioOfUsuarioHasRecetaCollectionNewUsuarioHasReceta = usuarioHasRecetaCollectionNewUsuarioHasReceta.getUsuario();
                    usuarioHasRecetaCollectionNewUsuarioHasReceta.setUsuario(usuario);
                    usuarioHasRecetaCollectionNewUsuarioHasReceta = em.merge(usuarioHasRecetaCollectionNewUsuarioHasReceta);
                    if (oldUsuarioOfUsuarioHasRecetaCollectionNewUsuarioHasReceta != null && !oldUsuarioOfUsuarioHasRecetaCollectionNewUsuarioHasReceta.equals(usuario)) {
                        oldUsuarioOfUsuarioHasRecetaCollectionNewUsuarioHasReceta.getUsuarioHasRecetaCollection().remove(usuarioHasRecetaCollectionNewUsuarioHasReceta);
                        oldUsuarioOfUsuarioHasRecetaCollectionNewUsuarioHasReceta = em.merge(oldUsuarioOfUsuarioHasRecetaCollectionNewUsuarioHasReceta);
                    }
                }
            }
            for (Perfil perfilCollectionNewPerfil : perfilCollectionNew) {
                if (!perfilCollectionOld.contains(perfilCollectionNewPerfil)) {
                    Usuario oldUsuarioOfPerfilCollectionNewPerfil = perfilCollectionNewPerfil.getUsuario();
                    perfilCollectionNewPerfil.setUsuario(usuario);
                    perfilCollectionNewPerfil = em.merge(perfilCollectionNewPerfil);
                    if (oldUsuarioOfPerfilCollectionNewPerfil != null && !oldUsuarioOfPerfilCollectionNewPerfil.equals(usuario)) {
                        oldUsuarioOfPerfilCollectionNewPerfil.getPerfilCollection().remove(perfilCollectionNewPerfil);
                        oldUsuarioOfPerfilCollectionNewPerfil = em.merge(oldUsuarioOfPerfilCollectionNewPerfil);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UsuarioHasReceta> usuarioHasRecetaCollectionOrphanCheck = usuario.getUsuarioHasRecetaCollection();
            for (UsuarioHasReceta usuarioHasRecetaCollectionOrphanCheckUsuarioHasReceta : usuarioHasRecetaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the UsuarioHasReceta " + usuarioHasRecetaCollectionOrphanCheckUsuarioHasReceta + " in its usuarioHasRecetaCollection field has a non-nullable usuario field.");
            }
            Collection<Perfil> perfilCollectionOrphanCheck = usuario.getPerfilCollection();
            for (Perfil perfilCollectionOrphanCheckPerfil : perfilCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Perfil " + perfilCollectionOrphanCheckPerfil + " in its perfilCollection field has a non-nullable usuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Consultas> consultasCollection = usuario.getConsultasCollection();
            for (Consultas consultasCollectionConsultas : consultasCollection) {
                consultasCollectionConsultas.getUsuarioCollection().remove(usuario);
                consultasCollectionConsultas = em.merge(consultasCollectionConsultas);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
