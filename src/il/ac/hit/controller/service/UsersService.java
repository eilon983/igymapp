package il.ac.hit.controller.service;

import il.ac.hit.HibernateUtilties;
import il.ac.hit.dao.GymExceptionHandeler;
import il.ac.hit.dao.UsersDAO;
import il.ac.hit.domains.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * this service is for handlling the login and signup
 * and checks if the client have a user
 */
@WebServlet(urlPatterns = "/UsersService/*")
public class UsersService extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(UsersService.class);
    /**
     * check if user exist and login if pass match
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws GymExceptionHandeler
     */
    public void login(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, GymExceptionHandeler {
        String name = null,password = null, loginResponse = null;
        UsersDAO users_service = new UsersDAO(HibernateUtilties.getInstance().getSessionFactory());
         name = request.getParameter("username");
         password = request.getParameter("password");
         loginResponse = users_service.login(new User(name, password, null));

        if (loginResponse.equals("0")) {
            LOGGER.debug("Login invalid:Password");
            request.setAttribute("errorMessage", "Password not match!");
            request.getRequestDispatcher("/LandPage/landpage.jsp").forward(
                    request, response);
        } else if (loginResponse.equals("1")) {
            LOGGER.debug("Login invalid:Username");
            request.setAttribute("errorMessage", "Username is not exsits!");
            request.getRequestDispatcher("/LandPage/landpage.jsp").forward(
                    request, response);
        } else {
            request.getSession().setAttribute("user_name", name);
            request.getSession().setAttribute("id", loginResponse);
            Cookie userCookie = new Cookie("userid", name);
            userCookie.setMaxAge(60*60*24*365);
            response.addCookie(userCookie);
            response.sendRedirect("/controller/WorkoutService/home");

        }
    }

    /**
     * log the user out
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws GymExceptionHandeler
     */
    public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, GymExceptionHandeler {
    request.getSession().invalidate();
    request.setAttribute("id",null);
        response.sendRedirect("/controller/land");
}
/**
     * signing up a new user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws GymExceptionHandeler
     */
    public void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, GymExceptionHandeler {
        UsersDAO users_service = new UsersDAO(HibernateUtilties.getInstance().getSessionFactory());
        String name = null,password = null, email = null,loginResponse = null;
        email = request.getParameter("email");
        name = request.getParameter("username");
        password = request.getParameter("password");
         loginResponse = users_service.signUp(new User(name,password,email));
        if(loginResponse.equals("3"))
        {
            LOGGER.debug("Signup invalid:Username already exists");
            request.setAttribute("errorMessage", "Username already exists");
            request.getRequestDispatcher("/LandPage/landpage.jsp").forward(
                    request, response);
        }
        else {
            request.getSession().setAttribute("user_name", name);
            request.getSession().setAttribute("id", loginResponse);
            response.sendRedirect("/controller/WorkoutService/home");
        }

    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        }
    }

