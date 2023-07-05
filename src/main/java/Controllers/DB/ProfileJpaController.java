/*
*Keiron Garro M
*C23212
*UCR
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controllers.DB;

import Controllers.DB.exceptions.IllegalOrphanException;
import Controllers.DB.exceptions.NonexistentEntityException;
import Models.DB.Profile;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.DB.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class ProfileJpaController implements Serializable {

    public ProfileJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Profile profile) {
        if (profile.getUsersCollection() == null) {
            profile.setUsersCollection(new ArrayList<Users>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : profile.getUsersCollection()) {
                usersCollectionUsersToAttach = em.getReference(usersCollectionUsersToAttach.getClass(), usersCollectionUsersToAttach.getId());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            profile.setUsersCollection(attachedUsersCollection);
            em.persist(profile);
            for (Users usersCollectionUsers : profile.getUsersCollection()) {
                Profile oldProfileIdOfUsersCollectionUsers = usersCollectionUsers.getProfileId();
                usersCollectionUsers.setProfileId(profile);
                usersCollectionUsers = em.merge(usersCollectionUsers);
                if (oldProfileIdOfUsersCollectionUsers != null) {
                    oldProfileIdOfUsersCollectionUsers.getUsersCollection().remove(usersCollectionUsers);
                    oldProfileIdOfUsersCollectionUsers = em.merge(oldProfileIdOfUsersCollectionUsers);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profile profile) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profile persistentProfile = em.find(Profile.class, profile.getId());
            Collection<Users> usersCollectionOld = persistentProfile.getUsersCollection();
            Collection<Users> usersCollectionNew = profile.getUsersCollection();
            List<String> illegalOrphanMessages = null;
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Users " + usersCollectionOldUsers + " since its profileId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = em.getReference(usersCollectionNewUsersToAttach.getClass(), usersCollectionNewUsersToAttach.getId());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            profile.setUsersCollection(usersCollectionNew);
            profile = em.merge(profile);
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    Profile oldProfileIdOfUsersCollectionNewUsers = usersCollectionNewUsers.getProfileId();
                    usersCollectionNewUsers.setProfileId(profile);
                    usersCollectionNewUsers = em.merge(usersCollectionNewUsers);
                    if (oldProfileIdOfUsersCollectionNewUsers != null && !oldProfileIdOfUsersCollectionNewUsers.equals(profile)) {
                        oldProfileIdOfUsersCollectionNewUsers.getUsersCollection().remove(usersCollectionNewUsers);
                        oldProfileIdOfUsersCollectionNewUsers = em.merge(oldProfileIdOfUsersCollectionNewUsers);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profile.getId();
                if (findProfile(id) == null) {
                    throw new NonexistentEntityException("The profile with id " + id + " no longer exists.");
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
            Profile profile;
            try {
                profile = em.getReference(Profile.class, id);
                profile.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profile with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Users> usersCollectionOrphanCheck = profile.getUsersCollection();
            for (Users usersCollectionOrphanCheckUsers : usersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profile (" + profile + ") cannot be destroyed since the Users " + usersCollectionOrphanCheckUsers + " in its usersCollection field has a non-nullable profileId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(profile);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profile> findProfileEntities() {
        return findProfileEntities(true, -1, -1);
    }

    public List<Profile> findProfileEntities(int maxResults, int firstResult) {
        return findProfileEntities(false, maxResults, firstResult);
    }

    private List<Profile> findProfileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profile.class));
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

    public Profile findProfile(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profile.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profile> rt = cq.from(Profile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
