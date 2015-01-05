package Controller;

import Model.User;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mihaicostea on 02/01/15.
 */
public class EditProfileServlet extends HttpServlet {
    private Controller controller = new Controller();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = -1, age = -1;
            String name = null, email = null, pictureURL = null, town = null;
            File file;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            String filePath = "/Users/mihaicostea/Developer/info-labs/An 3/Sem 1/Web/Lab 10/web/pics/";

            String contentType = request.getContentType();
            if (contentType.indexOf("multipart/form-data") >= 0) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(maxMemSize);
                factory.setRepository(new File("/tmp/"));

                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(maxFileSize);

                try {
                    List fileItems = upload.parseRequest(request);
                    Iterator i = fileItems.iterator();

                    while (i.hasNext()) {
                        FileItem fi = (FileItem)i.next();
                        if (!fi.isFormField()) {
                            String fieldName = fi.getFieldName();
                            String fileName = fi.getName();
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();

                            System.out.println(fileName);

                            if (fileName.lastIndexOf("\\") >= 0) {
                                file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                            } else {
                                file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                            }

                            fi.write(file);
                            System.out.println("Uploaded to: " + filePath + fileName);
                            pictureURL = "http://localhost:8080/pics/" + fileName;
                        } else {
                            if (fi.getFieldName().equals("id")) {
                                id = Integer.parseInt(fi.getString());
                            } else if (fi.getFieldName().equals("name")) {
                                name = fi.getString();
                            } else if (fi.getFieldName().equals("age")) {
                                age = Integer.parseInt(fi.getString());
                            } else if (fi.getFieldName().equals("email")) {
                                email = fi.getString();
                            } else if (fi.getFieldName().equals("town")) {
                                town = fi.getString();
                            }
                        }
                    }

                    if (id != -1 && name != null && email != null && pictureURL != null && town != null && age != -1) {
                        User user = new User(id);
                        user.setName(name);
                        user.setEmail(email);

                        user.setPictureURL(pictureURL);
                        user.setTown(town);
                        user.setAge(age);

                        User newUser = this.controller.updateUser(user);
                        if (newUser != null) {
                            GetUsersServlet getUsers = new GetUsersServlet();
                            getUsers.doGet(request, response);
                        } else {
                            request.setAttribute("message", "Update failed");

                            RequestDispatcher view = request.getRequestDispatcher("failure.jsp");
                            view.forward(request, response);
                        }
                    }
                } catch (FileUploadException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
