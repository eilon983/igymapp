package il.ac.hit.controller.service;

import il.ac.hit.HibernateUtilties;
import il.ac.hit.dao.GymExceptionHandeler;
import il.ac.hit.dao.WorkoutDAO;
import il.ac.hit.domains.UsersActivities;
import il.ac.hit.domains.WorkOuts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * class that shows the statistics of your workout
 *
 */

@WebServlet(urlPatterns = "/StaticsService/*")
public class StaticsService extends HttpServlet {


    /**
     * by id input get method return the graph of your workouts
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void statics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        WorkoutDAO wo_service = new WorkoutDAO(HibernateUtilties.getInstance().getSessionFactory());
        String user_id =(String) request.getSession().getAttribute("id");
        Map params = new HashMap<String,Object>();
        params.put("x",user_id);
        String sqlQuery = "from WorkOuts where USER_ID = :x ";
        List<WorkOuts> list = null;
        List<WorkOuts> lastWeekList = new LinkedList<>();
        List<WorkOuts> top3 = null;
        Date oneWeekAgo = new Date(System.currentTimeMillis() - 7L * 24 * 3600 * 1000);
        try {
            list = wo_service.getDao().query(sqlQuery,params);
        } catch (GymExceptionHandeler gymExceptionHandeler) {
            gymExceptionHandeler.printStackTrace();
        }

        Map<String,Integer> map = new HashMap<>();
        double total = 0;
        for (WorkOuts w:list) {
            if(w.getWorkOutDate().after(oneWeekAgo))
            {
                lastWeekList.add(w);
            }

            for (UsersActivities u:w.getActivitiesList()) {
                map.merge(u.getActivityName(), u.getRepeats(), Integer::sum);
                total += u.getRepeats();
            }
        }

        Collections.sort(lastWeekList,new CaloriesComperator());
        if(lastWeekList.size() > 3)
        top3 = new ArrayList<WorkOuts>(lastWeekList.subList(list.size() -3, list.size()));
        else top3 = lastWeekList;

        request.setAttribute("staticsMap", map);
        request.setAttribute("top3", top3);
        request.setAttribute("id", user_id);
        request.setAttribute("total", total);
        request.getRequestDispatcher("/Graphs/MostUsedActivity.jsp").forward(
                request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
