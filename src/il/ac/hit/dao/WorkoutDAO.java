package il.ac.hit.dao;

import il.ac.hit.domains.UsersActivities;
import il.ac.hit.domains.WorkOuts;
import org.hibernate.SessionFactory;

import java.util.*;
/**
 * workouts dao class, that preventing access to data
 * and allow a few operations like get, add, delete, update.
 **/

public class WorkoutDAO {

    private IGymDAO<WorkOuts,Long> dao;
    private final  Class cl = WorkOuts.class;
    /**
     *  constrictor
     * @param sf , creating connection with hibernate, to create dao object.
     */
    public WorkoutDAO(SessionFactory sf) {
        dao = new HibernateGymDAO<>(cl,sf);
    }

    public IGymDAO getDao() {
        return dao;
    }
    /**
     * save workout in DB.
     * @param workout
     * @return
     * @throws GymExceptionHandeler
     */

    public WorkOuts addWorkOuts(WorkOuts workout) throws GymExceptionHandeler {
        return dao.save(workout);
    }
    /**
     * delete workout from the DB.
     * @param id
     * @throws GymExceptionHandeler
     */
    public void deleteWorkout(String id) throws GymExceptionHandeler {
        dao.delete(dao.get(cl,Long.parseLong(id)));
    }
    /**
     * update workout in the DB.
     * @param list
     * @param id
     * @throws GymExceptionHandeler
     */
    public void updateWorkout(List<UsersActivities> list,String id) throws GymExceptionHandeler {
        WorkOuts wo = dao.get(cl,Long.parseLong(id));
        wo.setActivitiesList(list);
        dao.update(wo);
        return;
    }
}
