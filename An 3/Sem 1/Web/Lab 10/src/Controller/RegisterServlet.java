package Controller;

import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihaicostea on 05/01/15.
 */
public class RegisterServlet extends HttpServlet {
    private Controller controller = new Controller();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(0);
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPictureURL(request.getParameter("pictureURL"));
        user.setTown(request.getParameter("town"));
        user.setAge(Integer.parseInt(request.getParameter("age")));

        User newUser = this.controller.addUser(user);
        if (newUser != null) {
            GetUsersServlet getUsers = new GetUsersServlet();
            getUsers.doGet(request, response);
        } else {
            request.setAttribute("message", "Register failed");

            RequestDispatcher view = request.getRequestDispatcher("failure.jsp");
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
