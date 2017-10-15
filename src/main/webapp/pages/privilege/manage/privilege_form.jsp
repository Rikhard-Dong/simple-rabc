<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>
        <c:if test="${empty requestScope.privilege}">
            添加权限
        </c:if>
        <c:if test="${not empty requestScope.privilege}">
            更新权限
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
                    <a href="../../" class="breadcrumb">权限管理</a>
                </li>
                <li>/</li>
                <li>
                    <c:if test="${empty requestScope.privilege}">
                        <a href="javascript:void(0);" class="breadcrumb">添加权限</a>
                    </c:if>
                    <c:if test="${not empty requestScope.privilege}">
                        <a href="javascript:void(0);" class="breadcrumb">更新权限</a>
                    </c:if>
                </li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="row" style="margin-top: 8%; margin-left: 2%; margin-right: 2%">
        <div class="col m2"></div>
        <div class="col m8 s12 l6">
            <div class="card">
                <div class="card-content">
                    <span class="card-title text-accent-3 deep-orange-text">添加权限</span>
                    <div class="row">
                        <form action="${pageContext.request.contextPath}/privilege/manage" method="post"
                              class="col s12" id="privilege-form">
                            <c:if test="${empty requestScope.privilege}">
                                <input type="hidden" name="action" value="add_privilege">
                            </c:if>
                            <c:if test="${not empty requestScope.privilege}">
                                <input type="hidden" name="action" value="update_privilege">
                                <input type="hidden" name="privilege_id" value="${requestScope.privilege.id}">
                            </c:if>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input type="text" id="privilege_name" name="privilege_name" class="validate"
                                           maxlength="50"
                                    <c:if test="${not empty requestScope.privilege}">
                                           value="${requestScope.privilege.privilege_name}"
                                    </c:if>
                                    >
                                    <label for="privilege_name">权限名称</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input type="text" id="privilege_url" name="privilege_url" class="validate"
                                           maxlength="50"
                                    <c:if test="${not empty requestScope.privilege}">
                                           value="${requestScope.privilege.privilege_url}"
                                    </c:if>
                                    >
                                    <label for="privilege_url">权限URL</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s4 red-text text-lighten-1">是否需要登录</div>
                                <div class="col s2"></div>
                                <div class="switch col s4 center">
                                    <label>
                                        <input type="radio" name="no_login" id="no_login_true" value="0"
                                               class="with-gap"
                                        <c:if test="${not empty requestScope.privilege}">
                                        <c:if test="${requestScope.privilege.no_login == '0'}">
                                               checked
                                        </c:if>
                                        </c:if>
                                        <c:if test="${ empty requestScope.privilege}">
                                               checked
                                        </c:if>
                                        >
                                        <label for="no_login_true">是</label>
                                        <input type="radio" name="no_login" id="no_login_false" value="1"
                                               class="with-gap"
                                        <c:if test="${not empty requestScope.privilege}">
                                        <c:if test="${requestScope.privilege.no_login == '1'}">
                                               checked
                                        </c:if>
                                        </c:if>
                                        >
                                        <label for="no_login_false">否</label>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                        <textarea name="description" id="description"
                                                  class="materialize-textarea"><c:if
                                                test="${not empty requestScope.privilege}">${requestScope.privilege.description}</c:if></textarea>
                                    <label for="description">权限描述</label>
                                </div>
                            </div>
                            <div class="row">
                                <a class="waves-effect waves-light btn-large col s5"
                                   id="privilege-form-submit">提交</a>
                                <div class="col s2"></div>
                                <a class="waves-effect waves-light btn-large col s5"
                                   id="privilege-form-reset">重置</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col m2 l6"></div>
    </div>
</main>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        // 提交表单
        $("#privilege-form-submit").click(function () {
//            alert("表单提交");
            $("#privilege-form").submit();
        });

        /* 重置表单 */
        $('#privilege-form-reset').click(function () {
            var no_login = $("input[name='no_login']:checked").val();
            alert(no_login);
            $("#privilege-form input").each(function (index, element) {
                if (element.name !== 'no_login') {
                    element.value = "";
                }
            });
            $("#privilege-form textarea").each(function (index, element) {
                element.value = "";
            })
        });
    });
</script>
</body>
</html>
