/*
*Keiron Garro M
*C23212
*UCR
*/

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controladores.DB;

import Controladores.DB.exceptions.IllegalOrphanException;
import Controladores.DB.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Profile;
import Entidades.User;
import Entidades.UsersVoteRecipes;
import java.util.ArrayList;
import java.util.Collection;
import Entidades.UsersSaveRecipes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;




public class UsersJpaController implements Serializable {

    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User users) {
        if (users.getUsersVoteRecipesCollection() == null) {
            users.setUsersVoteRecipesCollection(new ArrayList<UsersVoteRecipes>());
        }
        if (users.getUsersSaveRecipesCollection() == null) {
            users.setUsersSaveRecipesCollection(new ArrayList<UsersSaveRecipes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profile profileId = users.getProfileId();
            if (profileId != null) {
                profileId = em.getReference(profileId.getClass(), profileId.getId());
                users.setProfileId(profileId);
            }
            Collection<UsersVoteRecipes> attachedUsersVoteRecipesCollection = new ArrayList<UsersVoteRecipes>();
            for (UsersVoteRecipes usersVoteRecipesCollectionUsersVoteRecipesToAttach : users.getUsersVoteRecipesCollection()) {
                usersVoteRecipesCollectionUsersVoteRecipesToAttach = em.getReference(usersVoteRecipesCollectionUsersVoteRecipesToAttach.getClass(), usersVoteRecipesCollectionUsersVoteRecipesToAttach.getUsersVoteRecipesPK());
                attachedUsersVoteRecipesCollection.add(usersVoteRecipesCollectionUsersVoteRecipesToAttach);
            }
            users.setUsersVoteRecipesCollection(attachedUsersVoteRecipesCollection);
            Collection<UsersSaveRecipes> attachedUsersSaveRecipesCollection = new ArrayList<UsersSaveRecipes>();
            for (UsersSaveRecipes usersSaveRecipesCollectionUsersSaveRecipesToAttach : users.getUsersSaveRecipesCollection()) {
                usersSaveRecipesCollectionUsersSaveRecipesToAttach = em.getReference(usersSaveRecipesCollectionUsersSaveRecipesToAttach.getClass(), usersSaveRecipesCollectionUsersSaveRecipesToAttach.getUsersSaveRecipesPK());
                attachedUsersSaveRecipesCollection.add(usersSaveRecipesCollectionUsersSaveRecipesToAttach);
            }
            users.setUsersSaveRecipesCollection(attachedUsersSaveRecipesCollection);
            em.persist(users);
            if (profileId != null) {
                profileId.getUsersCollection().add(users);
                profileId = em.merge(profileId);
            }
            for (UsersVoteRecipes usersVoteRecipesCollectionUsersVoteRecipes : users.getUsersVoteRecipesCollection()) {
                User oldUsersOfUsersVoteRecipesCollectionUsersVoteRecipes = usersVoteRecipesCollectionUsersVoteRecipes.getUsers();
                usersVoteRecipesCollectionUsersVoteRecipes.setUsers(users);
                usersVoteRecipesCollectionUsersVoteRecipes = em.merge(usersVoteRecipesCollectionUsersVoteRecipes);
                if (oldUsersOfUsersVoteRecipesCollectionUsersVoteRecipes != null) {
                    oldUsersOfUsersVoteRecipesCollectionUsersVoteRecipes.getUsersVoteRecipesCollection().remove(usersVoteRecipesCollectionUsersVoteRecipes);
                    oldUsersOfUsersVoteRecipesCollectionUsersVoteRecipes = em.merge(oldUsersOfUsersVoteRecipesCollectionUsersVoteRecipes);
                }
            }
            for (UsersSaveRecipes usersSaveRecipesCollectionUsersSaveRecipes : users.getUsersSaveRecipesCollection()) {
                User oldUsersOfUsersSaveRecipesCollectionUsersSaveRecipes = usersSaveRecipesCollectionUsersSaveRecipes.getUsers();
                usersSaveRecipesCollectionUsersSaveRecipes.setUsers(users);
                usersSaveRecipesCollectionUsersSaveRecipes = em.merge(usersSaveRecipesCollectionUsersSaveRecipes);
                if (oldUsersOfUsersSaveRecipesCollectionUsersSaveRecipes != null) {
                    oldUsersOfUsersSaveRecipesCollectionUsersSaveRecipes.getUsersSaveRecipesCollection().remove(usersSaveRecipesCollectionUsersSaveRecipes);
                    oldUsersOfUsersSaveRecipesCollectionUsersSaveRecipes = em.merge(oldUsersOfUsersSaveRecipesCollectionUsersSaveRecipes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User users) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUsers = em.find(User.class, users.getId());
            Profile profileIdOld = persistentUsers.getProfileId();
            Profile profileIdNew = users.getProfileId();
            Collection<UsersVoteRecipes> usersVoteRecipesCollectionOld = persistentUsers.getUsersVoteRecipesCollection();
            Collection<UsersVoteRecipes> usersVoteRecipesCollectionNew = users.getUsersVoteRecipesCollection();
            Collection<UsersSaveRecipes> usersSaveRecipesCollectionOld = persistentUsers.getUsersSaveRecipesCollection();
            Collection<UsersSaveRecipes> usersSaveRecipesCollectionNew = users.getUsersSaveRecipesCollection();
            List<String> illegalOrphanMessages = null;
            for (UsersVoteRecipes usersVoteRecipesCollectionOldUsersVoteRecipes : usersVoteRecipesCollectionOld) {
                if (!usersVoteRecipesCollectionNew.contains(usersVoteRecipesCollectionOldUsersVoteRecipes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsersVoteRecipes " + usersVoteRecipesCollectionOldUsersVoteRecipes + " since its users field is not nullable.");
                }
            }
            for (UsersSaveRecipes usersSaveRecipesCollectionOldUsersSaveRecipes : usersSaveRecipesCollectionOld) {
                if (!usersSaveRecipesCollectionNew.contains(usersSaveRecipesCollectionOldUsersSaveRecipes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UsersSaveRecipes " + usersSaveRecipesCollectionOldUsersSaveRecipes + " since its users field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (profileIdNew != null) {
                profileIdNew = em.getReference(profileIdNew.getClass(), profileIdNew.getId());
                users.setProfileId(profileIdNew);
            }
            Collection<UsersVoteRecipes> attachedUsersVoteRecipesCollectionNew = new ArrayList<UsersVoteRecipes>();
            for (UsersVoteRecipes usersVoteRecipesCollectionNewUsersVoteRecipesToAttach : usersVoteRecipesCollectionNew) {
                usersVoteRecipesCollectionNewUsersVoteRecipesToAttach = em.getReference(usersVoteRecipesCollectionNewUsersVoteRecipesToAttach.getClass(), usersVoteRecipesCollectionNewUsersVoteRecipesToAttach.getUsersVoteRecipesPK());
                attachedUsersVoteRecipesCollectionNew.add(usersVoteRecipesCollectionNewUsersVoteRecipesToAttach);
            }
            usersVoteRecipesCollectionNew = attachedUsersVoteRecipesCollectionNew;
            users.setUsersVoteRecipesCollection(usersVoteRecipesCollectionNew);
            Collection<UsersSaveRecipes> attachedUsersSaveRecipesCollectionNew = new ArrayList<UsersSaveRecipes>();
            for (UsersSaveRecipes usersSaveRecipesCollectionNewUsersSaveRecipesToAttach : usersSaveRecipesCollectionNew) {
                usersSaveRecipesCollectionNewUsersSaveRecipesToAttach = em.getReference(usersSaveRecipesCollectionNewUsersSaveRecipesToAttach.getClass(), usersSaveRecipesCollectionNewUsersSaveRecipesToAttach.getUsersSaveRecipesPK());
                attachedUsersSaveRecipesCollectionNew.add(usersSaveRecipesCollectionNewUsersSaveRecipesToAttach);
            }
            usersSaveRecipesCollectionNew = attachedUsersSaveRecipesCollectionNew;
            users.setUsersSaveRecipesCollection(usersSaveRecipesCollectionNew);
            users = em.merge(users);
            if (profileIdOld != null && !profileIdOld.equals(profileIdNew)) {
                profileIdOld.getUsersCollection().remove(users);
                profileIdOld = em.merge(profileIdOld);
            }
            if (profileIdNew != null && !profileIdNew.equals(profileIdOld)) {
                profileIdNew.getUsersCollection().add(users);
                profileIdNew = em.merge(profileIdNew);
            }
            for (UsersVoteRecipes usersVoteRecipesCollectionNewUsersVoteRecipes : usersVoteRecipesCollectionNew) {
                if (!usersVoteRecipesCollectionOld.contains(usersVoteRecipesCollectionNewUsersVoteRecipes)) {
                    User oldUsersOfUsersVoteRecipesCollectionNewUsersVoteRecipes = usersVoteRecipesCollectionNewUsersVoteRecipes.getUsers();
                    usersVoteRecipesCollectionNewUsersVoteRecipes.setUsers(users);
                    usersVoteRecipesCollectionNewUsersVoteRecipes = em.merge(usersVoteRecipesCollectionNewUsersVoteRecipes);
                    if (oldUsersOfUsersVoteRecipesCollectionNewUsersVoteRecipes != null && !oldUsersOfUsersVoteRecipesCollectionNewUsersVoteRecipes.equals(users)) {
                        oldUsersOfUsersVoteRecipesCollectionNewUsersVoteRecipes.getUsersVoteRecipesCollection().remove(usersVoteRecipesCollectionNewUsersVoteRecipes);
                        oldUsersOfUsersVoteRecipesCollectionNewUsersVoteRecipes = em.merge(oldUsersOfUsersVoteRecipesCollectionNewUsersVoteRecipes);
                    }
                }
            }
            for (UsersSaveRecipes usersSaveRecipesCollectionNewUsersSaveRecipes : usersSaveRecipesCollectionNew) {
                if (!usersSaveRecipesCollectionOld.contains(usersSaveRecipesCollectionNewUsersSaveRecipes)) {
                    User oldUsersOfUsersSaveRecipesCollectionNewUsersSaveRecipes = usersSaveRecipesCollectionNewUsersSaveRecipes.getUsers();
                    usersSaveRecipesCollectionNewUsersSaveRecipes.setUsers(users);
                    usersSaveRecipesCollectionNewUsersSaveRecipes = em.merge(usersSaveRecipesCollectionNewUsersSaveRecipes);
                    if (oldUsersOfUsersSaveRecipesCollectionNewUsersSaveRecipes != null && !oldUsersOfUsersSaveRecipesCollectionNewUsersSaveRecipes.equals(users)) {
                        oldUsersOfUsersSaveRecipesCollectionNewUsersSaveRecipes.getUsersSaveRecipesCollection().remove(usersSaveRecipesCollectionNewUsersSaveRecipes);
                        oldUsersOfUsersSaveRecipesCollectionNewUsersSaveRecipes = em.merge(oldUsersOfUsersSaveRecipesCollectionNewUsersSaveRecipes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            User users;
            try {
                users = em.getReference(User.class, id);
                users.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<UsersVoteRecipes> usersVoteRecipesCollectionOrphanCheck = users.getUsersVoteRecipesCollection();
            for (UsersVoteRecipes usersVoteRecipesCollectionOrphanCheckUsersVoteRecipes : usersVoteRecipesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the UsersVoteRecipes " + usersVoteRecipesCollectionOrphanCheckUsersVoteRecipes + " in its usersVoteRecipesCollection field has a non-nullable users field.");
            }
            Collection<UsersSaveRecipes> usersSaveRecipesCollectionOrphanCheck = users.getUsersSaveRecipesCollection();
            for (UsersSaveRecipes usersSaveRecipesCollectionOrphanCheckUsersSaveRecipes : usersSaveRecipesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Users (" + users + ") cannot be destroyed since the UsersSaveRecipes " + usersSaveRecipesCollectionOrphanCheckUsersSaveRecipes + " in its usersSaveRecipesCollection field has a non-nullable users field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Profile profileId = users.getProfileId();
            if (profileId != null) {
                profileId.getUsersCollection().remove(users);
                profileId = em.merge(profileId);
            }
            em.remove(users);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<User> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<User> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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

    public User findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
