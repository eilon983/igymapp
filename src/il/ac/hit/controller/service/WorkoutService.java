package il.ac.hit.controller.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import il.ac.hit.HibernateUtilties;
import il.ac.hit.dao.GymExceptionHandeler;
import il.ac.hit.dao.UsersDAO;
import il.ac.hit.dao.WorkoutDAO;
import il.ac.hit.domains.User;
import il.ac.hit.domains.UsersActivities;
import il.ac.hit.domains.WorkOuts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * this service load, delete and update the users workouts
 * to his workout list
 */
@WebServlet(name = "WorkoutService", urlPatterns = {"/myworkouts/*"})
public class WorkoutService extends HttpServlet {


    public void load(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, GymExceptionHandeler {
      String path = request.getPathInfo();
      WorkoutDAO wo_service = new WorkoutDAO(HibernateUtilties.getInstance().getSessionFactory());
      String user_id =(String) request.getSession().getAttribute("id");

          Map params = new HashMap<String,Object>();
          params.put("x",user_id);
          String sqlQuery = "from WorkOuts where USER_ID = :x ";
          List<WorkOuts> list = null;
          try {
              list = wo_service.getDao().query(sqlQuery,params);
          } catch (GymExceptionHandeler gymExceptionHandeler) {
              gymExceptionHandeler.printStackTrace();
          }
          request.setAttribute("workoutList", list);
          request.setAttribute("id", user_id);
          request.getRequestDispatcher("/Workouts/Workout.jsp").forward(
                  request, response);

  }
    public void update(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, GymExceptionHandeler {
        String path = request.getPathInfo();
        WorkoutDAO wo_service = new WorkoutDAO(HibernateUtilties.getInstance().getSessionFactory());
        String user_id =(String) request.getSession().getAttribute("id");
        String array = request.getParameter("workout_acts");
        String workout_id = request.getParameter("workout_id");
        Gson g = new Gson();
        Type collectionType = new TypeToken<ArrayList<UsersActivities>>() {
        }.getType();
        ArrayList<UsersActivities> activitiesList = (ArrayList<UsersActivities>) new Gson()
                .fromJson(array, collectionType);

        try {
            wo_service.updateWorkout(activitiesList,workout_id);
        } catch (GymExceptionHandeler gymExceptionHandeler) {
            gymExceptionHandeler.printStackTrace();
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson("OK" ));

    }
    public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, GymExceptionHandeler {
        String path = request.getPathInfo();
        WorkoutDAO wo_service = new WorkoutDAO(HibernateUtilties.getInstance().getSessionFactory());
        String user_id =(String) request.getSession().getAttribute("id");
            try {
                wo_service.deleteWorkout(request.getParameter("workout_delete"));
            } catch (GymExceptionHandeler gymExceptionHandeler) {
                gymExceptionHandeler.printStackTrace();
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson("OK" ));

  }
    public void home(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, GymExceptionHandeler
    {
        String userName = (String) request.getSession().getAttribute("user_name");
        Cookie c = new Cookie("userid", userName);
        c.setMaxAge(24*60*60);
        response.addCookie(c);
        c.setDomain(request.getServerName());
        c.setPath(request.getContextPath());
        load(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }
}
