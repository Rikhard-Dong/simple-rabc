<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>
        <c:if test="${empty requestScope.role}">
            添加角色
        </c:if>
        <c:if test="${not empty requestScope.role}">
            更新角色
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
                    <c:if test="${empty requestScope.role}">
                        <a href="javascript:void(0);" class="breadcrumb">添加角色</a>
                    </c:if>
                    <c:if test="${not empty requestScope.role}">
                        <a href="javascript:void(0);" class="breadcrumb">更新角色</a>
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
                        <form action="${pageContext.request.contextPath}/role/manage" method="post"
                              class="col s12" id="role-form">
                            <c:if test="${empty requestScope.role}">
                                <input type="hidden" name="action" value="add_role">
                            </c:if>
                            <c:if test="${not empty requestScope.role}">
                                <input type="hidden" name="action" value="update_role">
                                <input type="hidden" name="role_id" value="${requestScope.role.id}">
                            </c:if>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input type="text" maxlength="50" class="validate" id="role_name"
                                           name="role_name"
                                    <c:if test="${not empty requestScope.role}">
                                           value="${requestScope.role.role_name}"
                                    </c:if>
                                    >
                                    <label for="role_name">新建角色</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                        <textarea name="description" id="description"
                                                  class="materialize-textarea"><c:if
                                                test="${not empty requestScope.role}">${requestScope.role.description}</c:if></textarea>
                                    <label for="description">角色描述</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s2">赋予权限</div>
                                <div class="col s10">
                                    <div class="row">
                                        <c:forEach var="privilege" items="${requestScope.privileges}">
                                            <div class="col s4">
                                                <input type="checkbox" name="privilege_id" value="${privilege.id}"
                                                       id="privilege_${privilege.id}"
                                                <c:if test="${fn:contains(requestScope.role.privileges, privilege.id)}">
                                                       checked="checked"
                                                </c:if>
                                                >
                                                <label for="privilege_${privilege.id}">${privilege.privilege_name}</label>
                                            </div>
                                        </c:forEach>

                                    </div>

                                </div>
                            </div>
                            <div class="row">
                                <a class="waves-effect waves-light btn-large col s5"
                                   id="role-form-submit">提交</a>
                                <div class="col s2"></div>
                                <a class="waves-effect waves-light btn-large col s5"
                                   id="role-form-reset">重置</a>
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
        $("#role-form-submit").click(function () {
//            alert("表单提交");
            var flag =false;
            $('#role-form input:checkbox').each(function (index, element) {
                console.log(element.val);
                if ($(this).is(":checked")) {
                    flag =true;
                }
            });
            if(flag === false){
                alert("请至少选择一个权限!");
                return false;
            }
            $("#role-form").submit();
        });

        /* 重置表单 */
        $('#role-form-reset').click(function () {
            $("#role-form input").each(function (index, element) {
                element.value = "";
            });
            $("#role-form textarea").each(function (index, element) {
                element.value = "";
            })
        });
    });
</script>
</body>
</html>
