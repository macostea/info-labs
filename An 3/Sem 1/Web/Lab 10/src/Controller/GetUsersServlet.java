package Controller;

import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mihaicostea on 02/01/15.
 */
public class GetUsersServlet extends HttpServlet {
    private Controller controller = new Controller();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = this.controller.getUsersWithDetails(request.getParameter("term"));
        request.setAttribute("users", users);

        RequestDispatcher view = request.getRequestDispatcher("results.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = this.controller.getAllUsers();
        request.setAttribute("users", users);

        RequestDispatcher view = request.getRequestDispatcher("results.jsp");
        view.forward(request, response);
    }
}
