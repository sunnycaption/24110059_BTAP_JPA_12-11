<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .register-container { max-width: 480px; margin: 40px auto; }
        .card { border-radius: 10px; box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); }
    </style>
</head>
<body>

    <!-- Header / Topbar dùng chung -->
    <jsp:include page="/views/topbar.jsp" />

    <div class="container register-container">
        <div class="card">
            <div class="card-header bg-success text-white text-center py-3">
                <h4 class="mb-0">ĐĂNG KÝ TÀI KHOẢN</h4>
            </div>
            <div class="card-body p-4">

                <!-- In ra thông báo lỗi alert nếu có -->
                <c:if test="${alert != null}">
                    <div class="alert alert-danger" role="alert">
                        ${alert}
                    </div>
                </c:if>

                <!-- Form POST gọi /register -->
                <form action="${pageContext.request.contextPath}/register" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Tên đăng nhập:</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Nhập tên đăng nhập" required>
                    </div>

                    <div class="mb-3">
                        <label for="fullname" class="form-label">Họ và tên:</label>
                        <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Nhập họ và tên" required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email:</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
                    </div>

                    <div class="mb-3">
                        <label for="phone" class="form-label">Số điện thoại:</label>
                        <input type="text" class="form-control" id="phone" name="phone" placeholder="Nhập số điện thoại">
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Mật khẩu:</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
                    </div>

                    <div class="mb-3">
                        <label for="repassword" class="form-label">Xác nhận mật khẩu:</label>
                        <input type="password" class="form-control" id="repassword" name="repassword" placeholder="Nhập lại mật khẩu" required>
                    </div>

                    <div class="d-grid gap-2 mt-4">
                        <button type="submit" class="btn btn-success py-2">Đăng ký</button>
                    </div>
                </form>

                <div class="text-center mt-3">
                    <span>Đã có tài khoản? </span>
                    <a href="${pageContext.request.contextPath}/login">Đăng nhập ngay</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
