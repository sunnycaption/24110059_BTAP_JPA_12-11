<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Topbar Header dùng chung -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/home">
            <span class="text-primary">User</span>Auth
        </a>

        <div class="collapse navbar-collapse justify-content-end" id="navbarAuth">
            <ul class="navbar-nav align-items-center">
                <%-- Dùng c:choose kiểm tra sessionScope.account --%>
                <c:choose>
                    <%-- Nếu null: in ra thẻ <a> Đăng nhập và Đăng ký --%>
                    <c:when test="${sessionScope.account == null}">
                        <li class="nav-item">
                            <a class="nav-link btn btn-outline-light btn-sm px-3 me-2" 
                               href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary btn-sm px-3 text-white" 
                               href="${pageContext.request.contextPath}/register">Đăng ký</a>
                        </li>
                    </c:when>

                    <%-- Nếu khác null: in ra dòng "Xin chào, ${sessionScope.account.fullName}" và thẻ <a> Đăng xuất --%>
                    <c:otherwise>
                        <li class="nav-item me-3 text-light">
                            Xin chào, <strong class="text-warning">${sessionScope.account.fullName}</strong>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-outline-danger btn-sm px-3 text-white" 
                               href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
