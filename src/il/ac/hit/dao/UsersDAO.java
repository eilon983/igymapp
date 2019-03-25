package il.ac.hit.dao;

import il.ac.hit.domains.User;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.*;
/**
 * Users DAO is a class that provides a few opertions in the users objects without giving access to data.
 */

public class UsersDAO{

    private IGymDAO<User,String> dao;
    private final  Class cl = User.class;
    /**
     * constructor that creating a userDao object and creating connection with hibernate
     * @param sf
     */
    public UsersDAO(SessionFactory sf) {
        dao = new HibernateGymDAO<>(cl,sf);
    }

    public IGymDAO getDao() {
        return dao;
    }
    /**
     * signup function with username, password and email.
     * @param user
     * @return
     * @throws IOException
     * @throws GymExceptionHandeler
     */
    public String signUp(User user) throws GymExceptionHandeler {
        Map params = new HashMap<String,Object>();
        params.put("x",user.getUsername());
        String sqlQuery = "from User where NAME = :x ";
        List<User> list = dao.query(sqlQuery,params);
        Iterator<User> i = list.iterator();
        if(i.hasNext())
            return "3";
        else {
            user.setId( UUID.randomUUID().toString());
            dao.save(user);
            return user.getId();
        }
    }

    /**
     * login function with username and password.
     * @param user
     * @return
     * @throws IOException
     * @throws GymExceptionHandeler
     */
    public String login(User user) throws IOException, GymExceptionHandeler {

        Map params = new HashMap<String,Object>();
        params.put("x",user.getUsername());
        String sqlQuery = "from User where NAME = :x ";
        List<User> list = dao.query(sqlQuery,params);
        Iterator<User> i = list.iterator();
        if (i.hasNext()) {
            User returned_user = i.next();

        if (returned_user.getPassword().equals(user.getPassword()))
                return returned_user.getId();
        else return "0";

        } else return "1";
    }




}
