<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập hệ thống</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .login-container { max-width: 420px; margin: 60px auto; }
        .card { border-radius: 10px; box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); }
    </style>
</head>
<body>

    <!-- Header / Topbar dùng chung -->
    <jsp:include page="/views/topbar.jsp" />

    <div class="container login-container">
        <div class="card">
            <div class="card-header bg-primary text-white text-center py-3">
                <h4 class="mb-0">ĐĂNG NHẬP</h4>
            </div>
            <div class="card-body p-4">

                <!-- Nếu có attribute alert, in ra thông báo lỗi bằng c:if -->
                <c:if test="${alert != null}">
                    <div class="alert alert-danger" role="alert">
                        ${alert}
                    </div>
                </c:if>

                <!-- Form POST gọi /login - Đảm bảo name chính xác tuyệt đối không dư khoảng trắng -->
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Tên đăng nhập:</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Nhập tên đăng nhập" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Mật khẩu:</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
                    </div>

                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="remember" name="remember" value="on">
                        <label class="form-check-label" for="remember">Nhớ tôi</label>
                    </div>

                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-primary py-2">Đăng nhập</button>
                    </div>
                </form>

                <div class="text-center mt-3">
                    <span>Chưa có tài khoản? </span>
                    <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
