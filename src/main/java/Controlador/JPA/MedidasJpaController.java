/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.IllegalOrphanException;
import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import Modelo.Medidas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.RecetaHasMedidas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author laura
 */
public class MedidasJpaController implements Serializable {

    public MedidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medidas medidas) throws PreexistingEntityException, Exception {
        if (medidas.getRecetaHasMedidasCollection() == null) {
            medidas.setRecetaHasMedidasCollection(new ArrayList<RecetaHasMedidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<RecetaHasMedidas> attachedRecetaHasMedidasCollection = new ArrayList<RecetaHasMedidas>();
            for (RecetaHasMedidas recetaHasMedidasCollectionRecetaHasMedidasToAttach : medidas.getRecetaHasMedidasCollection()) {
                recetaHasMedidasCollectionRecetaHasMedidasToAttach = em.getReference(recetaHasMedidasCollectionRecetaHasMedidasToAttach.getClass(), recetaHasMedidasCollectionRecetaHasMedidasToAttach.getRecetaHasMedidasPK());
                attachedRecetaHasMedidasCollection.add(recetaHasMedidasCollectionRecetaHasMedidasToAttach);
            }
            medidas.setRecetaHasMedidasCollection(attachedRecetaHasMedidasCollection);
            em.persist(medidas);
            for (RecetaHasMedidas recetaHasMedidasCollectionRecetaHasMedidas : medidas.getRecetaHasMedidasCollection()) {
                Medidas oldMedidasOfRecetaHasMedidasCollectionRecetaHasMedidas = recetaHasMedidasCollectionRecetaHasMedidas.getMedidas();
                recetaHasMedidasCollectionRecetaHasMedidas.setMedidas(medidas);
                recetaHasMedidasCollectionRecetaHasMedidas = em.merge(recetaHasMedidasCollectionRecetaHasMedidas);
                if (oldMedidasOfRecetaHasMedidasCollectionRecetaHasMedidas != null) {
                    oldMedidasOfRecetaHasMedidasCollectionRecetaHasMedidas.getRecetaHasMedidasCollection().remove(recetaHasMedidasCollectionRecetaHasMedidas);
                    oldMedidasOfRecetaHasMedidasCollectionRecetaHasMedidas = em.merge(oldMedidasOfRecetaHasMedidasCollectionRecetaHasMedidas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedidas(medidas.getId()) != null) {
                throw new PreexistingEntityException("Medidas " + medidas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medidas medidas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidas persistentMedidas = em.find(Medidas.class, medidas.getId());
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionOld = persistentMedidas.getRecetaHasMedidasCollection();
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionNew = medidas.getRecetaHasMedidasCollection();
            List<String> illegalOrphanMessages = null;
            for (RecetaHasMedidas recetaHasMedidasCollectionOldRecetaHasMedidas : recetaHasMedidasCollectionOld) {
                if (!recetaHasMedidasCollectionNew.contains(recetaHasMedidasCollectionOldRecetaHasMedidas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RecetaHasMedidas " + recetaHasMedidasCollectionOldRecetaHasMedidas + " since its medidas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<RecetaHasMedidas> attachedRecetaHasMedidasCollectionNew = new ArrayList<RecetaHasMedidas>();
            for (RecetaHasMedidas recetaHasMedidasCollectionNewRecetaHasMedidasToAttach : recetaHasMedidasCollectionNew) {
                recetaHasMedidasCollectionNewRecetaHasMedidasToAttach = em.getReference(recetaHasMedidasCollectionNewRecetaHasMedidasToAttach.getClass(), recetaHasMedidasCollectionNewRecetaHasMedidasToAttach.getRecetaHasMedidasPK());
                attachedRecetaHasMedidasCollectionNew.add(recetaHasMedidasCollectionNewRecetaHasMedidasToAttach);
            }
            recetaHasMedidasCollectionNew = attachedRecetaHasMedidasCollectionNew;
            medidas.setRecetaHasMedidasCollection(recetaHasMedidasCollectionNew);
            medidas = em.merge(medidas);
            for (RecetaHasMedidas recetaHasMedidasCollectionNewRecetaHasMedidas : recetaHasMedidasCollectionNew) {
                if (!recetaHasMedidasCollectionOld.contains(recetaHasMedidasCollectionNewRecetaHasMedidas)) {
                    Medidas oldMedidasOfRecetaHasMedidasCollectionNewRecetaHasMedidas = recetaHasMedidasCollectionNewRecetaHasMedidas.getMedidas();
                    recetaHasMedidasCollectionNewRecetaHasMedidas.setMedidas(medidas);
                    recetaHasMedidasCollectionNewRecetaHasMedidas = em.merge(recetaHasMedidasCollectionNewRecetaHasMedidas);
                    if (oldMedidasOfRecetaHasMedidasCollectionNewRecetaHasMedidas != null && !oldMedidasOfRecetaHasMedidasCollectionNewRecetaHasMedidas.equals(medidas)) {
                        oldMedidasOfRecetaHasMedidasCollectionNewRecetaHasMedidas.getRecetaHasMedidasCollection().remove(recetaHasMedidasCollectionNewRecetaHasMedidas);
                        oldMedidasOfRecetaHasMedidasCollectionNewRecetaHasMedidas = em.merge(oldMedidasOfRecetaHasMedidasCollectionNewRecetaHasMedidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medidas.getId();
                if (findMedidas(id) == null) {
                    throw new NonexistentEntityException("The medidas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medidas medidas;
            try {
                medidas = em.getReference(Medidas.class, id);
                medidas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medidas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionOrphanCheck = medidas.getRecetaHasMedidasCollection();
            for (RecetaHasMedidas recetaHasMedidasCollectionOrphanCheckRecetaHasMedidas : recetaHasMedidasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Medidas (" + medidas + ") cannot be destroyed since the RecetaHasMedidas " + recetaHasMedidasCollectionOrphanCheckRecetaHasMedidas + " in its recetaHasMedidasCollection field has a non-nullable medidas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(medidas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medidas> findMedidasEntities() {
        return findMedidasEntities(true, -1, -1);
    }

    public List<Medidas> findMedidasEntities(int maxResults, int firstResult) {
        return findMedidasEntities(false, maxResults, firstResult);
    }

    private List<Medidas> findMedidasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medidas.class));
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

    public Medidas findMedidas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medidas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedidasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medidas> rt = cq.from(Medidas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
