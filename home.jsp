<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - Hệ thống</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <!-- Topbar -->
    <jsp:include page="/views/topbar.jsp" />

    <div class="container mt-5">
        <div class="p-5 mb-4 bg-white rounded-3 shadow-sm border">
            <div class="container-fluid py-3">
                <h1 class="display-6 fw-bold text-primary">Hệ Thống Xác Thực Người Dùng (MVC)</h1>
                <p class="col-md-8 fs-5 text-secondary">
                    Đường dẫn hiện tại: <span class="badge bg-info text-dark">${currentPath}</span>
                </p>

                <c:choose>
                    <c:when test="${sessionScope.account != null}">
                        <div class="alert alert-success mt-4">
                            <h5>Thông tin tài khoản đăng nhập:</h5>
                            <ul class="mb-0">
                                <li><strong>ID:</strong> ${sessionScope.account.id}</li>
                                <li><strong>Tên đăng nhập:</strong> ${sessionScope.account.userName}</li>
                                <li><strong>Họ và tên:</strong> ${sessionScope.account.fullName}</li>
                                <li><strong>Email:</strong> ${sessionScope.account.email}</li>
                                <li><strong>Số điện thoại:</strong> ${sessionScope.account.phone}</li>
                                <li><strong>Mã quyền (Role ID):</strong> 
                                    <span class="badge ${sessionScope.account.roleid == 1 ? 'bg-danger' : (sessionScope.account.roleid == 2 ? 'bg-warning text-dark' : 'bg-success')}">
                                        ${sessionScope.account.roleid == 1 ? 'Admin (1)' : (sessionScope.account.roleid == 2 ? 'Manager (2)' : 'Member / User (5)')}
                                    </span>
                                </li>
                                <li><strong>Ngày tạo:</strong> ${sessionScope.account.createdDate}</li>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-warning mt-4">
                            Bạn hiện đang truy cập với tư cách <strong>Khách (Guest)</strong>. Vui lòng đăng nhập để trải nghiệm đầy đủ quyền hạn.
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

