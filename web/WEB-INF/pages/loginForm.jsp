<%--
  Created by IntelliJ IDEA.
  User: TJYSB
  Date: 2021/8/31
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
</head>
<body>
   <div>
       <%--<form action="/api/admin/yuansheng" method="post">--%>
       <form action="/api/admin/map" method="post">
           用户名：<input type="text" name="adminName"> <br/>
           密码：<input type="password" name="adminPwd"> <br/>
           <input type="submit" value="登录">
       </form>

   </div>
</body>
</html>
