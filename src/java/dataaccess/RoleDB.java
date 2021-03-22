package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

public class RoleDB {
    
    public List<Role> getAll(Role role) throws Exception {
                
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try 
        {
            List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        }
        finally 
        {
            em.close();
        }

    }
    
    public Role get(int roleID) throws Exception {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try 
        {
            Role role = em.find(Role.class, roleID);            
            return role;
        } 
        finally 
        {
            em.close();
        }

    }
    /*
    public void insert(Role role) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {            
            trans.begin();
            em.persist(role);
            em.merge(user);
            trans.commit();
        } 
        catch (Exception e)
        {
            trans.rollback();
        }
        finally 
        {
            em.close();
        }
    }
    public void update(Role role) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE role SET role_name=? WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, role.getRoleName());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    public void delete(Role role) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role.getRoleID());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }*/
}