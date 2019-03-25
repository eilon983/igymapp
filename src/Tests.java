import il.ac.hit.HibernateUtilties;
import il.ac.hit.dao.GymExceptionHandeler;
import il.ac.hit.dao.HibernateGymDAO;
import il.ac.hit.dao.UsersDAO;
import il.ac.hit.dao.WorkoutDAO;
import il.ac.hit.domains.Activities;

import java.io.IOException;
import java.sql.SQLException;

public class Tests {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, GymExceptionHandeler, IOException, GymExceptionHandeler {

        UsersDAO users_service = new UsersDAO(HibernateUtilties.getInstance().getSessionFactory());
        WorkoutDAO wo_service = new WorkoutDAO(HibernateUtilties.getInstance().getSessionFactory());
        HibernateGymDAO dao = new HibernateGymDAO(Activities.class,HibernateUtilties.getInstance().getSessionFactory());

       // String u1_uuid =   users_service.signUp(u1);
        Activities a1 = new Activities("Benchpress",5); dao.save(a1);
        Activities a2 = new Activities("Pull-ups",7); dao.save(a2);
        Activities a3 = new Activities("Push-ups",4); dao.save(a3);
        Activities a4 = new Activities("Saw",10); dao.save(a4);
        Activities a5 = new Activities("W-pole",17); dao.save(a5);
        Activities a6 = new Activities("Squat",90); dao.save(a6);
        Activities a7 = new Activities("ABS",1); dao.save(a7);
        Activities a8 = new Activities("Dead lift",89); dao.save(a8);
/*
        User u2 = new User("Shon","654321");

        Activities a1 = new Activities("sit","10");
        Activities a2 = new Activities("play","3");
        Activities a3 = new Activities("run","0.1 kilomters");


        String u2_uuid =   users_service.signUp(u2);


        WorkOuts wo1 = new WorkOuts(u1_uuid,"running",new ArrayList<String>());


      users_service.addWorkOuts(u1,wo1);

*/


    }
}
