<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ride
  Date: 17-10-11
  Time: 下午8:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>权限管理</title>
    <link href="${pageContext.request.contextPath}/static/css/materialize.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/my-style.css">
</head>
<body>
<header>
    <%@include file="../../slide-nav.jsp" %>
    <nav>
        <div class="nav-wrapper ">
            <a href="${pageContext.request.contextPath}/" class="brand-logo right" style="margin-right: 20px">用户管理系统</a>
            <ul id="nav-mobile" class="left hide-on-med-and-down">
                <li><a href="javascript:void(0);">权限管理</a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="row" style="margin-top: 20px; margin-left: 2%; margin-right: 2%;">
        <div class="col s12">
            <table class="bordered highlight centered">
                <thead>
                <tr>
                    <th>编号</th>
                    <th>权限名</th>
                    <th>权限URL</th>
                    <th>权限描述</th>
                    <th>是否需要登录</th>
                    <th>修改权限</th>
                    <th>删除权限</th>
                </tr>
                </thead>
                <tbody>
                <c:set var="num" value="1" scope="page"/>
                <c:forEach var="privilege" items="${requestScope.privilegeList}">
                    <tr>
                        <td>${pageScope.num}</td>
                        <td>${privilege.privilege_name}</td>
                        <td>${privilege.privilege_url}</td>
                        <td>${privilege.description}</td>
                        <td>
                            <c:if test="${privilege.no_login == '1'}">
                                <div class="chip">否</div>
                            </c:if>
                            <c:if test="${privilege.no_login == '0'}">
                                <div class="chip">是</div>
                            </c:if>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/privilege/manage?action=update_privilege_UI&privilege_id=${privilege.id}"
                               class="btn orange darken-2 waves-effect waves-light"
                               style="width:60px;padding:0;">修改</a>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/privilege/manage?action=delete_privilege&privilege_id=${privilege.id}"
                               class="btn deep-orange accent-4 waves-effect waves-light"
                               style="width:60px;padding:0;">删除</a>
                        </td>
                    </tr>
                    <c:set var="num" value="${pageScope.num + 1}" scope="page"/>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/materialize/0.100.2/js/materialize.min.js"></script>
</body>
</html>