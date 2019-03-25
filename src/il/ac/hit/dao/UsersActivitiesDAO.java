package il.ac.hit.dao;

import il.ac.hit.domains.UsersActivities;
import org.hibernate.SessionFactory;
/**
 *  UsersActivies DAO is a class that provides a few opertions in the users activities objects without giving access to data.
 */
public class UsersActivitiesDAO   {
    private IGymDAO<UsersActivities,String> dao;
    private final  Class cl = UsersActivities.class;
    /**
     * constructor   that creating a dao object after creating a  HibernateGymDAO object.
     * @param sf
     */
    public UsersActivitiesDAO(SessionFactory sf) {
        dao = new HibernateGymDAO<>(cl,sf);
    }
    public IGymDAO getDao() {
        return dao;
    }
}
