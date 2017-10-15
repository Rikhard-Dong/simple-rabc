<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ride
  Date: 17-10-11
  Time: 下午8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>消息通知</title>
    <link href="${pageContext.request.contextPath}/static/css/materialize.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/my-style.css">
    <style>

        .msg-warp {
            width: 100%;
            margin-top: 10%;
        }
    </style>
</head>
<body>
<header>
    <%@include file="slide-nav.jsp" %>
    <nav>
        <div class="nav-wrapper ">
            <a href="#" class="brand-logo right" style="margin-right: 20px">用户管理系统</a>
            <ul id="nav-mobile" class="left hide-on-med-and-down">
                <li><a href="javascript:void(0);">消息通知</a></li>
            </ul>
        </div>

    </nav>
</header>
<main>
    <div></div>
    <div class="row msg-warp">
        <div class="col s1"></div>
        <div class="col s10">
            <%-- 通知消息 --%>
            <c:if test="${not empty requestScope.msgNotice}">
                <div class="col s12 m8 offset-m2 l6 offset-l3">
                    <div class="card-panel grey lighten-5 z-depth-1">
                        <div class="row valign-wrapper">
                            <div class="col s2">
                                <img src="${pageContext.request.contextPath}/static/img/notice.jpeg" alt=""
                                     class="circle responsive-img">
                                <!-- notice the "circle" class -->
                            </div>
                            <div class="col s10">
                                <span class="black-text">
                                        <h4 class="teal-text text-accent-3">通知</h4>
                                        ${requestScope.msgNotice}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <%-- 错误消息 --%>
            <c:if test="${not empty requestScope['msgError']}">
                <div class="col s12 m8 offset-m2 l6 offset-l3">
                    <div class="card-panel grey lighten-5 z-depth-1">
                        <div class="row valign-wrapper">
                            <div class="col s2">
                                <img src="${pageContext.request.contextPath}/static/img/error.jpeg" alt=""
                                     class="circle responsive-img">
                            </div>
                            <div class="col s10">
                                <span class="black-text">
                                    <h4 class="red-text text-accent-3"">错误</h4>
                                        ${requestScope.msgError}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="col s1"></div>
    </div>
</main>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/materialize.min.js"></script>
<script>

</script>
</body>
</html>
