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
import Modelo.Perfil;
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
public class PrivilegioJpaController implements Serializable {

    public PrivilegioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Privilegio privilegio) throws PreexistingEntityException, Exception {
        if (privilegio.getPerfilCollection() == null) {
            privilegio.setPerfilCollection(new ArrayList<Perfil>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Perfil> attachedPerfilCollection = new ArrayList<Perfil>();
            for (Perfil perfilCollectionPerfilToAttach : privilegio.getPerfilCollection()) {
                perfilCollectionPerfilToAttach = em.getReference(perfilCollectionPerfilToAttach.getClass(), perfilCollectionPerfilToAttach.getPerfilPK());
                attachedPerfilCollection.add(perfilCollectionPerfilToAttach);
            }
            privilegio.setPerfilCollection(attachedPerfilCollection);
            em.persist(privilegio);
            for (Perfil perfilCollectionPerfil : privilegio.getPerfilCollection()) {
                perfilCollectionPerfil.getPrivilegioCollection().add(privilegio);
                perfilCollectionPerfil = em.merge(perfilCollectionPerfil);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrivilegio(privilegio.getId()) != null) {
                throw new PreexistingEntityException("Privilegio " + privilegio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Privilegio privilegio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Privilegio persistentPrivilegio = em.find(Privilegio.class, privilegio.getId());
            Collection<Perfil> perfilCollectionOld = persistentPrivilegio.getPerfilCollection();
            Collection<Perfil> perfilCollectionNew = privilegio.getPerfilCollection();
            Collection<Perfil> attachedPerfilCollectionNew = new ArrayList<Perfil>();
            for (Perfil perfilCollectionNewPerfilToAttach : perfilCollectionNew) {
                perfilCollectionNewPerfilToAttach = em.getReference(perfilCollectionNewPerfilToAttach.getClass(), perfilCollectionNewPerfilToAttach.getPerfilPK());
                attachedPerfilCollectionNew.add(perfilCollectionNewPerfilToAttach);
            }
            perfilCollectionNew = attachedPerfilCollectionNew;
            privilegio.setPerfilCollection(perfilCollectionNew);
            privilegio = em.merge(privilegio);
            for (Perfil perfilCollectionOldPerfil : perfilCollectionOld) {
                if (!perfilCollectionNew.contains(perfilCollectionOldPerfil)) {
                    perfilCollectionOldPerfil.getPrivilegioCollection().remove(privilegio);
                    perfilCollectionOldPerfil = em.merge(perfilCollectionOldPerfil);
                }
            }
            for (Perfil perfilCollectionNewPerfil : perfilCollectionNew) {
                if (!perfilCollectionOld.contains(perfilCollectionNewPerfil)) {
                    perfilCollectionNewPerfil.getPrivilegioCollection().add(privilegio);
                    perfilCollectionNewPerfil = em.merge(perfilCollectionNewPerfil);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = privilegio.getId();
                if (findPrivilegio(id) == null) {
                    throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.");
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
            Privilegio privilegio;
            try {
                privilegio = em.getReference(Privilegio.class, id);
                privilegio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The privilegio with id " + id + " no longer exists.", enfe);
            }
            Collection<Perfil> perfilCollection = privilegio.getPerfilCollection();
            for (Perfil perfilCollectionPerfil : perfilCollection) {
                perfilCollectionPerfil.getPrivilegioCollection().remove(privilegio);
                perfilCollectionPerfil = em.merge(perfilCollectionPerfil);
            }
            em.remove(privilegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Privilegio> findPrivilegioEntities() {
        return findPrivilegioEntities(true, -1, -1);
    }

    public List<Privilegio> findPrivilegioEntities(int maxResults, int firstResult) {
        return findPrivilegioEntities(false, maxResults, firstResult);
    }

    private List<Privilegio> findPrivilegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Privilegio.class));
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

    public Privilegio findPrivilegio(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Privilegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrivilegioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Privilegio> rt = cq.from(Privilegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
