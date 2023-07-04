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
import Modelo.Ingredientes;
import Modelo.Medidas;
import Modelo.Receta;
import Modelo.RecetaHasMedidas;
import Modelo.RecetaHasMedidasPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author laura
 */
public class RecetaHasMedidasJpaController implements Serializable {

    public RecetaHasMedidasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RecetaHasMedidas recetaHasMedidas) throws PreexistingEntityException, Exception {
        if (recetaHasMedidas.getRecetaHasMedidasPK() == null) {
            recetaHasMedidas.setRecetaHasMedidasPK(new RecetaHasMedidasPK());
        }
        recetaHasMedidas.getRecetaHasMedidasPK().setRecetaid(recetaHasMedidas.getReceta().getId());
        recetaHasMedidas.getRecetaHasMedidasPK().setMedidasId(recetaHasMedidas.getMedidas().getId());
        recetaHasMedidas.getRecetaHasMedidasPK().setIngredientesId(recetaHasMedidas.getIngredientes().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ingredientes ingredientes = recetaHasMedidas.getIngredientes();
            if (ingredientes != null) {
                ingredientes = em.getReference(ingredientes.getClass(), ingredientes.getId());
                recetaHasMedidas.setIngredientes(ingredientes);
            }
            Medidas medidas = recetaHasMedidas.getMedidas();
            if (medidas != null) {
                medidas = em.getReference(medidas.getClass(), medidas.getId());
                recetaHasMedidas.setMedidas(medidas);
            }
            Receta receta = recetaHasMedidas.getReceta();
            if (receta != null) {
                receta = em.getReference(receta.getClass(), receta.getId());
                recetaHasMedidas.setReceta(receta);
            }
            em.persist(recetaHasMedidas);
            if (ingredientes != null) {
                ingredientes.getRecetaHasMedidasCollection().add(recetaHasMedidas);
                ingredientes = em.merge(ingredientes);
            }
            if (medidas != null) {
                medidas.getRecetaHasMedidasCollection().add(recetaHasMedidas);
                medidas = em.merge(medidas);
            }
            if (receta != null) {
                receta.getRecetaHasMedidasCollection().add(recetaHasMedidas);
                receta = em.merge(receta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRecetaHasMedidas(recetaHasMedidas.getRecetaHasMedidasPK()) != null) {
                throw new PreexistingEntityException("RecetaHasMedidas " + recetaHasMedidas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RecetaHasMedidas recetaHasMedidas) throws NonexistentEntityException, Exception {
        recetaHasMedidas.getRecetaHasMedidasPK().setRecetaid(recetaHasMedidas.getReceta().getId());
        recetaHasMedidas.getRecetaHasMedidasPK().setMedidasId(recetaHasMedidas.getMedidas().getId());
        recetaHasMedidas.getRecetaHasMedidasPK().setIngredientesId(recetaHasMedidas.getIngredientes().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecetaHasMedidas persistentRecetaHasMedidas = em.find(RecetaHasMedidas.class, recetaHasMedidas.getRecetaHasMedidasPK());
            Ingredientes ingredientesOld = persistentRecetaHasMedidas.getIngredientes();
            Ingredientes ingredientesNew = recetaHasMedidas.getIngredientes();
            Medidas medidasOld = persistentRecetaHasMedidas.getMedidas();
            Medidas medidasNew = recetaHasMedidas.getMedidas();
            Receta recetaOld = persistentRecetaHasMedidas.getReceta();
            Receta recetaNew = recetaHasMedidas.getReceta();
            if (ingredientesNew != null) {
                ingredientesNew = em.getReference(ingredientesNew.getClass(), ingredientesNew.getId());
                recetaHasMedidas.setIngredientes(ingredientesNew);
            }
            if (medidasNew != null) {
                medidasNew = em.getReference(medidasNew.getClass(), medidasNew.getId());
                recetaHasMedidas.setMedidas(medidasNew);
            }
            if (recetaNew != null) {
                recetaNew = em.getReference(recetaNew.getClass(), recetaNew.getId());
                recetaHasMedidas.setReceta(recetaNew);
            }
            recetaHasMedidas = em.merge(recetaHasMedidas);
            if (ingredientesOld != null && !ingredientesOld.equals(ingredientesNew)) {
                ingredientesOld.getRecetaHasMedidasCollection().remove(recetaHasMedidas);
                ingredientesOld = em.merge(ingredientesOld);
            }
            if (ingredientesNew != null && !ingredientesNew.equals(ingredientesOld)) {
                ingredientesNew.getRecetaHasMedidasCollection().add(recetaHasMedidas);
                ingredientesNew = em.merge(ingredientesNew);
            }
            if (medidasOld != null && !medidasOld.equals(medidasNew)) {
                medidasOld.getRecetaHasMedidasCollection().remove(recetaHasMedidas);
                medidasOld = em.merge(medidasOld);
            }
            if (medidasNew != null && !medidasNew.equals(medidasOld)) {
                medidasNew.getRecetaHasMedidasCollection().add(recetaHasMedidas);
                medidasNew = em.merge(medidasNew);
            }
            if (recetaOld != null && !recetaOld.equals(recetaNew)) {
                recetaOld.getRecetaHasMedidasCollection().remove(recetaHasMedidas);
                recetaOld = em.merge(recetaOld);
            }
            if (recetaNew != null && !recetaNew.equals(recetaOld)) {
                recetaNew.getRecetaHasMedidasCollection().add(recetaHasMedidas);
                recetaNew = em.merge(recetaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RecetaHasMedidasPK id = recetaHasMedidas.getRecetaHasMedidasPK();
                if (findRecetaHasMedidas(id) == null) {
                    throw new NonexistentEntityException("The recetaHasMedidas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RecetaHasMedidasPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RecetaHasMedidas recetaHasMedidas;
            try {
                recetaHasMedidas = em.getReference(RecetaHasMedidas.class, id);
                recetaHasMedidas.getRecetaHasMedidasPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recetaHasMedidas with id " + id + " no longer exists.", enfe);
            }
            Ingredientes ingredientes = recetaHasMedidas.getIngredientes();
            if (ingredientes != null) {
                ingredientes.getRecetaHasMedidasCollection().remove(recetaHasMedidas);
                ingredientes = em.merge(ingredientes);
            }
            Medidas medidas = recetaHasMedidas.getMedidas();
            if (medidas != null) {
                medidas.getRecetaHasMedidasCollection().remove(recetaHasMedidas);
                medidas = em.merge(medidas);
            }
            Receta receta = recetaHasMedidas.getReceta();
            if (receta != null) {
                receta.getRecetaHasMedidasCollection().remove(recetaHasMedidas);
                receta = em.merge(receta);
            }
            em.remove(recetaHasMedidas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RecetaHasMedidas> findRecetaHasMedidasEntities() {
        return findRecetaHasMedidasEntities(true, -1, -1);
    }

    public List<RecetaHasMedidas> findRecetaHasMedidasEntities(int maxResults, int firstResult) {
        return findRecetaHasMedidasEntities(false, maxResults, firstResult);
    }

    private List<RecetaHasMedidas> findRecetaHasMedidasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RecetaHasMedidas.class));
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

    public RecetaHasMedidas findRecetaHasMedidas(RecetaHasMedidasPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RecetaHasMedidas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecetaHasMedidasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RecetaHasMedidas> rt = cq.from(RecetaHasMedidas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
