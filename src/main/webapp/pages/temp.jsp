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
</head>
<body>
<header>
    <%@include file="slide-nav.jsp" %>
    <nav>
        <div class="nav-wrapper ">
            <a href="#" class="brand-logo right" style="margin-right: 20px">用户管理系统</a>
            <ul id="nav-mobile" class="left hide-on-med-and-down">
                <li><a href="javascript:void(0);"></a></li>
            </ul>
        </div>

    </nav>
    <main>
        <div class="row">
            <div class="col m2 "></div>
            <div class="col m8 s12">
                <div class="card">
                    <div class="card-content">
                        <span class="card-title text-accent-3 deep-orange-text">添加权限</span>
                        <div class="row">
                            <form action="${pageContext.request.contextPath}/privilege/manage" method="post"
                                  class="col s12"
                                  id="login-form">
                                <input type="hidden" name="action" value="add_privilege">

                                <div class="row">
                                    <input class="waves-effect waves-light btn-large col s6" id="login-form-submit"
                                           value="登录"/>
                                    <input type="reset" class="waves-effect waves-light col s6" value="取消">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col m2"></div>
        </div>
    </main>
</header>
<main>


</main>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/materialize.min.js"></script>
<script>

</script>
</body>
</html>
