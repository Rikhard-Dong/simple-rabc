<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>
        <c:if test="${empty requestScope.user}">
            添加用户
        </c:if>
        <c:if test="${not empty requestScope.user}">
            更新用户
        </c:if>
    </title>
    <link href="${pageContext.request.contextPath}/static/css/materialize.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/my-style.css">
</head>
<body>
<header>
    <%@include file="../../slide-nav.jsp" %>
    <nav>
        <div class="nav-wrapper ">
            <a href="#" class="brand-logo right" style="margin-right: 20px">用户管理系统</a>
            <ul id="nav-mobile" class="left hide-on-med-and-down">
                <li>
                    <a href="../" class="breadcrumb">用户管理</a>
                </li>
                <li>/</li>
                <li>
                    <c:if test="${empty requestScope.user}">
                        <a href="javascript:void(0);" class="breadcrumb">添加用户</a>
                    </c:if>
                    <c:if test="${not empty requestScope.user}">
                        <a href="javascript:void(0);" class="breadcrumb">更新用户</a>
                    </c:if>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="row" style="margin-top: 8%;">
        <div class="col m1"></div>
        <div class="col s12 m10">
            <div class="card">
                <div class="card-content">
                    <span class="card-title text-accent-3 deep-orange-text">添加角色</span>
                    <div class="row">
                        <form action="${pageContext.request.contextPath}/user/manage" method="post"
                              class="col s12" id="user-form">
                            <c:if test="${empty requestScope.user}">
                                <input type="hidden" name="action" value="add_user">
                            </c:if>
                            <c:if test="${not empty requestScope.user}">
                                <input type="hidden" name="action" value="update_user">
                                <input type="hidden" name="user_id" value="${requestScope.user.id}">
                            </c:if>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input type="email" maxlength="50" class="validate" id="email"
                                           name="email"
                                    <c:if test="${not empty requestScope.user}">
                                           value="${requestScope.user.email}"
                                    </c:if>
                                    >
                                    <label for="email">注册邮箱</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input type="text" maxlength="50" class="validate" id="nickname"
                                           name="nickname"
                                    <c:if test="${not empty requestScope.user}">
                                           value="${requestScope.user.nickname}"
                                    </c:if>
                                    >
                                    <label for="nickname">用户昵称</label>
                                </div>
                            </div>
                            <c:if test="${empty requestScope.user}">
                                <div class="row">
                                    <div class="input-field col s12">
                                        <input type="password" maxlength="50" class="validate" id="password"
                                               name="password">
                                        <label for="password">用户密码</label>
                                    </div>
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="col s2">赋予角色</div>
                                <div class="col s10">
                                    <div class="row">
                                        <c:forEach var="role" items="${requestScope.roles}">
                                            <div class="col s4">
                                                <input type="checkbox" name="role_id" value="${role.id}"
                                                       id="role_${role.id}"
                                                <c:if test="${fn:contains(requestScope.user.roles, role.id)}">
                                                       checked="checked"
                                                </c:if>
                                                >
                                                <label for="role_${role.id}">${role.role_name}</label>
                                            </div>
                                        </c:forEach>
                                    </div>

                                </div>
                            </div>
                            <div class="row">
                                <a class="waves-effect waves-light btn-large col s5"
                                   id="user-form-submit">提交</a>
                                <div class="col s2"></div>
                                <a class="waves-effect waves-light btn-large col s5"
                                   id="user-form-reset">重置</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col m1"></div>
    </div>

</main>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        // 提交表单
        $("#user-form-submit").click(function () {
//            alert("表单提交");
            var flag =false;
            $('#user-form input:checkbox').each(function (index, element) {
                console.log(element.val);
                if ($(this).is(":checked")) {
                  flag =true;
                }
            });
            if(flag === false){
                alert("请至少选择一个角色!");
                return false;
            }
            $("#user-form").submit();
        });

        /* 重置表单 */
        $('#user-form-reset').click(function () {
            $("#user-form input").each(function (index, element) {
                element.value = "";
            });
            $("#user-form textarea").each(function (index, element) {
                element.value = "";
            })
        });
    });
</script>
</body>
</html>
