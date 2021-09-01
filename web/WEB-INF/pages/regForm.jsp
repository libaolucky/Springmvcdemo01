<%--
  Created by IntelliJ IDEA.
  User: TJYSB
  Date: 2021/8/31
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>最传统的 form传参</title>
    <link rel="stylesheet" type="text/css" href="/res/layui-v2.5.6/layui/css/layui.css">
    <script src="/res/layui-v2.5.6/layui/layui.js"></script>
</head>
<body>
<div>
    <form action="/api/admin/regFrom" method="post">
         用户名：<input type="text" name="adminName"> <br>
         密码：<input type="password" name="adminPwd"> <br>
            <input type="submit" value="注册">
    </form>
</div>



</body>
</html>
