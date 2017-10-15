<%--
  Created by IntelliJ IDEA.
  User: ride
  Date: 17-10-11
  Time: 下午2:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户登录</title>
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
            <li><a href="javascript:void(0);">用户登录</a></li>
        </ul>
        </div>

    </nav>
</header>
<main>
    <div class="row">
        <div class="col m2 "></div>
        <div class="col m8 s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title text-accent-3 deep-orange-text">用户登录</span>
                    <div class="row">
                        <form action="${pageContext.request.contextPath}/user/operation" method="post" class="col s12" id="login-form">
                            <input type="hidden" name="action" value="login">
                            <div class="row">
                                <div class="input-field col s12">
                                    <i class="material-icons prefix">email</i>
                                    <input type="text" id="email" name="email" class="validate">
                                    <label for="email">账号(邮箱)</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <i class="material-icons prefix">vpn_key</i>
                                    <input type="password" id="password" name="password" class="validate">
                                    <label for="password">密码(6-16位)</label>
                                </div>
                            </div>
                            <div class="row">
                                <a class="waves-effect waves-light btn-large col s12" id="login-form-submit">登录</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col m2"></div>
    </div>
</main>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/materialize/0.100.2/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('#login-form-submit').click(function (){
//            alert("表单提交");
            $("#login-form").submit();
        });
    });
</script>
</body>
</html>