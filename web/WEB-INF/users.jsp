<%-- 
    Document   : users
    Created on : Mar 9, 2021, 1:26:36 PM
    Author     : 815822
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Week8 Lab</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div class="container">
           
            <div class="addForm">
                <c:if test="${activeUser == null}">
                <h1 class="addUser">Add User</h1>
                    <form action="users" method="post">
                        <input class="userInfo" type="email" placeholder="Email" name="email" required>
                        <input class="userInfo" type="text" placeholder="First Name" name="firstname" required> 
                        <input class="userInfo" type="text" placeholder="Last Name" name="lastname" required>         
                        <input class="userInfo" type="password" placeholder="Password" name="password" required>
                        <select class="userInfo" name="role">
                            <option value= 1 >System Admin</option>
                            <option value= 2>Regular User</option>
                            <option value= 3 >Company Admin</option>
                        </select>

                        <input type="hidden" name="action" value="add">
                        <input class="addInput" type="submit" value="Add">
                    </form>
                </c:if>
            </div>
            
            <div class="manageForm">
                <h1 class="manageUser">Manage Users</h1>
                <table>
                    <tr>
                        <th>Email</th>
                        <th>Active</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                    
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td> ${user.email}</td>
                                <td>
                                    <input class="userInfo chActive" type="checkbox" name="isActive" checked>                          
                                </td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.role.roleName}</td>


                                <td>
                                    <form action="users" method="post" >
                                        <input type="submit" name="edit" value="Edit">
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="manageEmail" value="${user.email}">

                                    </form>
                                </td>
                                <td>
                                    <form action="users" method="post" >
                                        <input type="submit" name="delete" value="Delete">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="deleteEmail" value="${user.email}">

                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    
                </table>
            </div>
                           
            <div class="editForm">
                
                <h1 class="editUser">Edit User</h1>
                    <form action="users" method="post">
                        <input class="userInfo" type="email" placeholder="Edit Email" name="editEmail" value="${editEmail}" readonly>
                        <input class="userInfo" type="text" placeholder="Edit First Name" name="editFirstname" value="${editFirstname}"> 
                        <input class="userInfo" type="text" placeholder="Edit Last Name" name="editLastname" value="${editLastname}">     
                        <input class="userInfo" type="password" placeholder="Password" name="editPassword" value="${editPassword}">
                        <select class="userInfo" name="editRole" value="${editRoleID}">
                            <c:if test="${editRoleID eq 1}">
                                <option value="1" selected>System Admin</option>
                                 <option value="2" >Regular User</option>
                                 <option value="3" >Company Admin</option>
                            </c:if>
                            <c:if test="${editRoleID eq 2}">
                                <option value="1" >System Admin</option>
                                 <option value="2" selected>Regular User</option>
                                 <option value="3" >Company Admin</option>
                            </c:if>
                            <c:if test="${editRoleID eq 3}">               
                                <option value="1" >System Admin</option>
                                 <option value="2" >Regular User</option>
                                 <option value="3" selected >Company Admin</option>
                            </c:if>
                        </select>

                        <input type="hidden" name="action" value="save">
                        <input class="addInput" type="submit" value="Save">
                        <input type="hidden" name="action" value="cancel">
                        <input class="Cancel" type="submit" value="Cancel">
                    </form>  
          
            </div>

        </div>
    </body>
</html>