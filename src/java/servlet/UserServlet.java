/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author 827637
 */
public class UserServlet extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        try {

            User user = null;
            Role role = null;
            UserService userservice = new UserService();
            List<User> users = userservice.getAll(user);
            RoleService roleservice = new RoleService();
            List<Role> roles = roleservice.getAll(role);

            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();

        String editEmail = request.getParameter("editEmail");
        String editFname = request.getParameter("editFirstname");
        String editLname = request.getParameter("editLastname");
        String editRole = request.getParameter("editRole");
        String editPass = request.getParameter("editPassword");

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
        String role = request.getParameter("role");


        boolean active = true;

        if (request.getParameter("active") != null && request.getParameter("active").equals("on")) {

            active = false;

        };

        String action = request.getParameter("action");

        User selectedUser = null;
        String message = null;

        try {

            switch (action) {
                case "edit":
                    selectedUser = us.get(request.getParameter("editEmail"));
                    editEmail = selectedUser.getEmail();
                    editFname = selectedUser.getFirstName();
                    editLname = selectedUser.getLastName();
                    editRole = String.valueOf(selectedUser.getRole().getRoleId());
                    editPass = selectedUser.getPassword();

                    request.setAttribute("editEmail", editEmail);
                    request.setAttribute("editFirstname", editFname);
                    request.setAttribute("editLastname", editLname);
                    request.setAttribute("editPassword", editPass);
                    request.setAttribute("editRole", editRole);
                    break;

                case "add":
                    us.insert(email, firstName, lastName, password, active, Integer.parseInt(role));
                    break;

                case "save":
                    us.update(editEmail, editFname, editLname, editPass, active, Integer.parseInt(editRole));
                    break;
                    
                case "delete":
                    us.delete(email);
                    break;
                    
                default:
                    break;
            }
            request.setAttribute("message", action);

        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "error");

        }

        try {

            User user = null;
            Role roleAll = null;
            UserService userservice = new UserService();
            List<User> users = userservice.getAll(user);
            RoleService roleservice = new RoleService();
            List<Role> roles = roleservice.getAll(roleAll);

            request.setAttribute("users", users);
            request.setAttribute("roles", roles);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("selectedUser", selectedUser);

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

    }

}
