package il.ac.hit.controller.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import il.ac.hit.HibernateUtilties;
import il.ac.hit.dao.GymExceptionHandeler;
import il.ac.hit.dao.HibernateGymDAO;
import il.ac.hit.dao.IGymDAO;
import il.ac.hit.dao.WorkoutDAO;
import il.ac.hit.domains.Activities;
import il.ac.hit.domains.UsersActivities;
import il.ac.hit.domains.WorkOuts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ActivityService")
public class ActivityService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = (String) request.getSession().getAttribute("id");;
        String path = request.getPathInfo();
        IGymDAO<Activities,String> dao;
        WorkoutDAO wo_service = new WorkoutDAO(HibernateUtilties.getInstance().getSessionFactory());
        RequestDispatcher dispatcher = null;
            id = (String) request.getSession().getAttribute("id");
            dao = new HibernateGymDAO<>(Activities.class, HibernateUtilties.getInstance().getSessionFactory());
            List<Activities> list = null;
            try {
                list = dao.query("from Activities",null);
            } catch (GymExceptionHandeler gymExceptionHandeler) {
                gymExceptionHandeler.printStackTrace();
            }
            String s = list.iterator().next().getActivityName();
            request.setAttribute("actvListDB",list);
            request.setAttribute("id",id);
            request.getRequestDispatcher("/Activities/activities.jsp").forward(
                request, response);

    }
    public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = (String) request.getSession().getAttribute("id");;
        String path = request.getPathInfo();
        IGymDAO<Activities,String> dao;
        WorkoutDAO wo_service = new WorkoutDAO(HibernateUtilties.getInstance().getSessionFactory());
        RequestDispatcher dispatcher = null;
        String array = request.getParameter("list");
        String name = request.getParameter("actvName");
        long sum = 0;
        Gson g = new Gson();
        Type collectionType = new TypeToken<ArrayList<UsersActivities>>() {
        }.getType();
        ArrayList<UsersActivities> activitiesList = (ArrayList<UsersActivities>) new Gson()
                .fromJson(array, collectionType);
        for (UsersActivities idx : activitiesList)
            sum += idx.getRepeats() * idx.getCalories();
        WorkOuts wo1 = new WorkOuts(id, name, activitiesList, sum);
        try {
            wo_service.addWorkOuts(wo1);
        } catch (GymExceptionHandeler gymExceptionHandeler) {
            gymExceptionHandeler.printStackTrace();
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(g.toJson("Name: +" + name));
    }
}
