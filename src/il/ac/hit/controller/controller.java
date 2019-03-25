package il.ac.hit.controller;




import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "controller", urlPatterns = {"/controller/*"})
public class controller extends HttpServlet {


    private static final Logger LOGGER = Logger.getLogger(controller.class);
    /**
     * Called by the server to allow a servlet to handle a POST request.
     * The HTTP POST method allows the client to send data to the servar
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String[] splitted = path.split("/");
        String controllerName = splitted[1];
        String actionName = splitted[2];
        Class controllerClass = null;

        try {
            controllerClass = Class.forName("il.ac.hit.controller.service." + controllerName);
            Object controller = controllerClass.newInstance();
            Method[] m = controllerClass.getDeclaredMethods();
            Method method = controllerClass.getMethod(actionName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(controller,request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
}
    /**
     * Called by the server to allow a servlet to handle a GET request.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String path = request.getPathInfo();
        Cookie[] c = request.getCookies();
        if(path.equals("/land"))
        { LOGGER.info("GET:controller-" + path);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/LandPage/landpage.jsp");
            dispatcher.forward(request, response);
        }
        else {
            String[] splitted = path.split("/");
            String controllerName = splitted[1];
            String actionName = splitted[2];
            Class controllerClass = null;
            LOGGER.info("GET:controller-" + path);
            try {
                controllerClass = Class.forName("il.ac.hit.controller.service." + controllerName);
                Object controller = controllerClass.newInstance();
                Method[] m = controllerClass.getDeclaredMethods();
                Method method = controllerClass.getMethod(actionName, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(controller, request, response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        }
    }

