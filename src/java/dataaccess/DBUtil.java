package dataaccess;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("UserPU");
    
    public static EntityManagerFactory getEmFactory() {
        return EMF;
    }
}
