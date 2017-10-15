<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <a href="#" data-activates="nav-mobile"
       class="button-collapse top-nav waves-effect waves-light circle hide-on-large-only">
        <i class="material-icons">menu</i>
    </a>
</div>
<%-- 侧边导航栏 --%>
<ul id="nav-mobile" class="side-nav fixed">
    <li>
        <div class="userView" style="padding-bottom: 20px;">
            <div class="background">
                <img src="${pageContext.request.contextPath}/static/img/slide-bg.jpeg" style="width:100%;">
            </div>
            <c:if test="${not empty sessionScope.loginUser}">
                <a href="javascript:void(0);">
                    <img class="circle" src="${pageContext.request.contextPath}/static/img/logo.jpg"></a>
                <a href="javascript:void(0);">
                    <span class="white-text name ">${sessionScope.loginUser.nickname}</span></a>
                <a href="#javascript:void(0);"><span class="white-text email">${sessionScope.loginUser.email}</span></a>
                <a class="waves-effect waves-light btn red lighten-1"
                   href="${pageContext.request.contextPath}/pages/user/operation/login.jsp"
                   style="padding-left: 10px;padding-right: 10px;z-index: 100;">切换</a>
                <a class="waves-effect waves-light btn red lighten-1"
                   href="${pageContext.request.contextPath}/user?action=exit"
                   style="padding-left: 10px;padding-right: 10px;margin-left: 10px">登出</a>
            </c:if>
            <c:if test="${empty sessionScope.loginUser}">
                <h5>当前未登录</h5>
                <a class="waves-effect waves-light btn red lighten-1"
                   href="${pageContext.request.contextPath}/pages/user/operation/login.jsp"
                   style="padding-left: 10px;padding-right: 10px;">登录</a>
                <a class="waves-effect waves-light btn red lighten-1"
                   style="padding-left: 10px;padding-right: 10px;margin-left: 10px;">注册</a>
            </c:if>
        </div>
    </li>

    <li class="no-padding">
        <c:if test="${not empty sessionScope.loginUser}">
            <ul class="collapsible collapsible-accordion">
                    <%-- 用户管理 --%>
                <li class="bold ">
                    <a class="collapsible-header   waves-effect waves-teal">
                        用户管理<i class="material-icons">perm_identity</i>
                    </a>
                    <div class="collapsible-body">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/user/manage?action=add_user_UI">新建用户</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/manage?action=list_user">查看用户</a></li>
                        </ul>
                    </div>
                </li>
                    <%-- 角色管理 --%>
                <li class="bold">
                    <a class="collapsible-header  waves-effect waves-teal">
                        角色管理<i class="material-icons">supervisor_account</i>
                    </a>
                    <div class="collapsible-body">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/role/manage?action=add_role_UI">新建角色</a></li>
                            <li><a href="${pageContext.request.contextPath}/role/manage?action=list_role">查看角色</a></li>
                        </ul>
                    </div>
                </li>
                    <%-- 权限管理 --%>
                <li class="bold">
                    <a class="collapsible-header  waves-effect waves-teal">
                        权限管理<i class="material-icons">vpn_key</i>
                    </a>
                    <div class="collapsible-body">
                        <ul>
                            <li>
                                <a href="${pageContext.request.contextPath}/privilege/manage?action=add_privilege_UI">
                                    新建权限
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/privilege/manage?action=list_privilege">查看权限</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </c:if>
    </li>
    <li class="bold">
        <%--<a href="" class="waves-effect waves-light red lighten-1">关于我们</a>--%>
    </li>
</ul>
