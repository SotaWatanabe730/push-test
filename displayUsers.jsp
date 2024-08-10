<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.User" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display Users</title>
    <link rel="stylesheet" href="Login.css">
</head>
<body>
    <h1>管理者権限編集画面</h1>
    <div class="form-container">
    <h2>管理者権限付与</h2>
    <p>ユーザー名を選択してください</p>
    <form action="yourActionURL" method="post">
        <select name="userId">
            <%
                List<User> userList = (List<User>) request.getAttribute("userList");
                for (User user : userList) {
            %>
                <option value="<%= user.getId() %>">
                    <%= user.getName() %>
                </option>
            <%
                }
            %>
        </select>
        <input type="submit" value="Submit">
    </form>
   </div>
   <div class="form-container">
   <h2>管理者権限取り消し</h2>
   <p>ユーザー名を選択してください</p>
   <form action="yourActionURL" method="post">
        <select name="adminUserId">
          <%
                List<User> adminList = (List<User>) request.getAttribute("adminList");
                for (User user : adminList) {
            %>
                <option value="<%= user.getId() %>">
                    <%= user.getName() %>
                </option>
            <%
                }
            %>
        </select>
        <input type="submit" value="Submit">
    </form>
   </div>
</body>
</html>
