/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.JPA;

import Controlador.JPA.exceptions.IllegalOrphanException;
import Controlador.JPA.exceptions.NonexistentEntityException;
import Controlador.JPA.exceptions.PreexistingEntityException;
import Modelo.Ingredientes;
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
public class IngredientesJpaController implements Serializable {

    public IngredientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ingredientes ingredientes) throws PreexistingEntityException, Exception {
        if (ingredientes.getRecetaHasMedidasCollection() == null) {
            ingredientes.setRecetaHasMedidasCollection(new ArrayList<RecetaHasMedidas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<RecetaHasMedidas> attachedRecetaHasMedidasCollection = new ArrayList<RecetaHasMedidas>();
            for (RecetaHasMedidas recetaHasMedidasCollectionRecetaHasMedidasToAttach : ingredientes.getRecetaHasMedidasCollection()) {
                recetaHasMedidasCollectionRecetaHasMedidasToAttach = em.getReference(recetaHasMedidasCollectionRecetaHasMedidasToAttach.getClass(), recetaHasMedidasCollectionRecetaHasMedidasToAttach.getRecetaHasMedidasPK());
                attachedRecetaHasMedidasCollection.add(recetaHasMedidasCollectionRecetaHasMedidasToAttach);
            }
            ingredientes.setRecetaHasMedidasCollection(attachedRecetaHasMedidasCollection);
            em.persist(ingredientes);
            for (RecetaHasMedidas recetaHasMedidasCollectionRecetaHasMedidas : ingredientes.getRecetaHasMedidasCollection()) {
                Ingredientes oldIngredientesOfRecetaHasMedidasCollectionRecetaHasMedidas = recetaHasMedidasCollectionRecetaHasMedidas.getIngredientes();
                recetaHasMedidasCollectionRecetaHasMedidas.setIngredientes(ingredientes);
                recetaHasMedidasCollectionRecetaHasMedidas = em.merge(recetaHasMedidasCollectionRecetaHasMedidas);
                if (oldIngredientesOfRecetaHasMedidasCollectionRecetaHasMedidas != null) {
                    oldIngredientesOfRecetaHasMedidasCollectionRecetaHasMedidas.getRecetaHasMedidasCollection().remove(recetaHasMedidasCollectionRecetaHasMedidas);
                    oldIngredientesOfRecetaHasMedidasCollectionRecetaHasMedidas = em.merge(oldIngredientesOfRecetaHasMedidasCollectionRecetaHasMedidas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIngredientes(ingredientes.getId()) != null) {
                throw new PreexistingEntityException("Ingredientes " + ingredientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ingredientes ingredientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingredientes persistentIngredientes = em.find(Ingredientes.class, ingredientes.getId());
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionOld = persistentIngredientes.getRecetaHasMedidasCollection();
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionNew = ingredientes.getRecetaHasMedidasCollection();
            List<String> illegalOrphanMessages = null;
            for (RecetaHasMedidas recetaHasMedidasCollectionOldRecetaHasMedidas : recetaHasMedidasCollectionOld) {
                if (!recetaHasMedidasCollectionNew.contains(recetaHasMedidasCollectionOldRecetaHasMedidas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RecetaHasMedidas " + recetaHasMedidasCollectionOldRecetaHasMedidas + " since its ingredientes field is not nullable.");
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
            ingredientes.setRecetaHasMedidasCollection(recetaHasMedidasCollectionNew);
            ingredientes = em.merge(ingredientes);
            for (RecetaHasMedidas recetaHasMedidasCollectionNewRecetaHasMedidas : recetaHasMedidasCollectionNew) {
                if (!recetaHasMedidasCollectionOld.contains(recetaHasMedidasCollectionNewRecetaHasMedidas)) {
                    Ingredientes oldIngredientesOfRecetaHasMedidasCollectionNewRecetaHasMedidas = recetaHasMedidasCollectionNewRecetaHasMedidas.getIngredientes();
                    recetaHasMedidasCollectionNewRecetaHasMedidas.setIngredientes(ingredientes);
                    recetaHasMedidasCollectionNewRecetaHasMedidas = em.merge(recetaHasMedidasCollectionNewRecetaHasMedidas);
                    if (oldIngredientesOfRecetaHasMedidasCollectionNewRecetaHasMedidas != null && !oldIngredientesOfRecetaHasMedidasCollectionNewRecetaHasMedidas.equals(ingredientes)) {
                        oldIngredientesOfRecetaHasMedidasCollectionNewRecetaHasMedidas.getRecetaHasMedidasCollection().remove(recetaHasMedidasCollectionNewRecetaHasMedidas);
                        oldIngredientesOfRecetaHasMedidasCollectionNewRecetaHasMedidas = em.merge(oldIngredientesOfRecetaHasMedidasCollectionNewRecetaHasMedidas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ingredientes.getId();
                if (findIngredientes(id) == null) {
                    throw new NonexistentEntityException("The ingredientes with id " + id + " no longer exists.");
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
            Ingredientes ingredientes;
            try {
                ingredientes = em.getReference(Ingredientes.class, id);
                ingredientes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingredientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RecetaHasMedidas> recetaHasMedidasCollectionOrphanCheck = ingredientes.getRecetaHasMedidasCollection();
            for (RecetaHasMedidas recetaHasMedidasCollectionOrphanCheckRecetaHasMedidas : recetaHasMedidasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ingredientes (" + ingredientes + ") cannot be destroyed since the RecetaHasMedidas " + recetaHasMedidasCollectionOrphanCheckRecetaHasMedidas + " in its recetaHasMedidasCollection field has a non-nullable ingredientes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ingredientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ingredientes> findIngredientesEntities() {
        return findIngredientesEntities(true, -1, -1);
    }

    public List<Ingredientes> findIngredientesEntities(int maxResults, int firstResult) {
        return findIngredientesEntities(false, maxResults, firstResult);
    }

    private List<Ingredientes> findIngredientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ingredientes.class));
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

    public Ingredientes findIngredientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ingredientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngredientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ingredientes> rt = cq.from(Ingredientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
