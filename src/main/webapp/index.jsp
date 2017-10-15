<%@ page import="io.ride.domain.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ride
  Date: 17-10-11
  Time: 上午10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <title>Starting Page</title>
    <link href="${pageContext.request.contextPath}/static/css/materialize.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/my-style.css">
</head>
<body>
<header>
    <%@include file="pages/slide-nav.jsp" %>
    <nav>
        <div class="nav-wrapper ">
            <a href="#" class="brand-logo right" style="margin-right: 20px">用户管理系统</a>
        </div>
    </nav>
</header>

<main>
    <div class="row center" style="margin-top: 10%;">
        <div class="col m4 s12">
            <i class="large material-icons  orange-text text-darken-3" style="font-size: 12rem;">perm_identity</i>
            <h3>用户管理</h3>
            <div class="row">
                <div class="col s2"></div>
                <div class="col s8">
                    <p>我们提供了完善的用户管理操作, 可以新建, 查看, 修改和删除用户</p>
                </div>
                <div class="col s2"></div>
            </div>
        </div>
        <div class="col m4 s12">
            <i class="large material-icons orange-text text-darken-3" style="font-size: 12rem;">supervisor_account</i>
            <h3>角色管理</h3>
            <div class="row">
                <div class="col s2"></div>
                <div class="col s8">
                    <p>我们提供了完善的角色管理操作, 可以新建, 查看, 修改和删除角色</p>
                </div>
                <div class="col s2"></div>
            </div>
        </div>
        <div class="col m4 s12">
            <i class="large material-icons orange-text text-darken-3" style="font-size: 12rem;">vpn_key</i>
            <h3>权限管理</h3>
            <div class="row">
                <div class="col s2"></div>
                <div class="col s8">
                    <p>我们提供了完善的权限管理操作, 可以新建, 查看, 修改和删除权限</p>
                </div>
                <div class="col s2"></div>
            </div>
        </div>
    </div>
</main>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('.button-collapse').sideNav('show');
    })
</script>
</body>
</html>