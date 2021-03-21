package dataaccess;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

/**
 *
 * @author 827637
 */
public class UserDB {
    public List<User> getAll(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        
        try {
            Role role = em.find(Role.class, user);
            return role.getUserList();
        } finally {
            em.close();
            
        }

    }
    
    public User get(String email)  throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        
        try {
           User user = em.find(User.class, email);
           
           // get all users of the same role as that user
           List<User> users = user.getRole().getUserList();
           
            return user;
        } finally {
            em.close();
          
        }
        
       
    }
    
    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Role role = user.getRole();
            role.getUserList().add(user);
            trans.begin();
            em.persist(user);
            em.merge(role);
           trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(user);
           trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void delete(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Role role = user.getRole();
            role.getUserList().remove(user);
            trans.begin();
            em.remove(em.merge(user));
            em.merge(role);
           trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }
}