package Controller;

import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mihaicostea on 02/01/15.
 */
public class LoginServlet extends HttpServlet {
    private Controller controller = new Controller();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        User user = this.controller.loginUser(name);

        if (user != null) {
            RequestDispatcher view = request.getRequestDispatcher("user.jsp");
            request.setAttribute("id", user.getId());
            view.forward(request, response);
        } else {
            RequestDispatcher view = request.getRequestDispatcher("failure.jsp");
            request.setAttribute("message", "Login failed");
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
