package Controller;

import Model.User;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihaicostea on 02/01/15.
 */
public class EditProfileServlet extends HttpServlet {
    private Controller controller = new Controller();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = new User(id);
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPictureURL(request.getParameter("pictureURL"));
            user.setTown(request.getParameter("town"));
            user.setAge(Integer.parseInt(request.getParameter("age")));

            User newUser = this.controller.updateUser(user);
            if (newUser != null) {
                GetUsersServlet getUsers = new GetUsersServlet();
                getUsers.doGet(request, response);
            } else {
                request.setAttribute("message", "Update failed");

                RequestDispatcher view = request.getRequestDispatcher("failure.jsp");
                view.forward(request, response);
            }

        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Invalid id");

            RequestDispatcher view = request.getRequestDispatcher("failure.jsp");
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("user", this.controller.getUserById(id));

            RequestDispatcher view = request.getRequestDispatcher("editProfile.jsp");
            view.forward(request, response);
        } catch (NumberFormatException ex) {
            request.setAttribute("message", "Invalid id");

            RequestDispatcher view = request.getRequestDispatcher("failure.jsp");
            view.forward(request, response);
        }
    }
}
