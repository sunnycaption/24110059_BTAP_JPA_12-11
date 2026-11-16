package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;

import java.io.IOException;

/**
 * Servlet Controller xử lý chức năng Đăng nhập người dùng (MVC - Controller).
 * Tương thích Jakarta EE 10 / Tomcat 10.1.
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nếu đã đăng nhập thì chuyển hướng thẳng về trang chủ
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("currentUser") != null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        // Chuyển hướng tới giao diện login.jsp
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 1. Kiểm tra dữ liệu rỗng
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            req.setAttribute("errorMessage", "Vui lòng nhập đầy đủ Tên đăng nhập và Mật khẩu!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }

        // 2. Gọi tầng Service xử lý kiểm tra đăng nhập qua JPA & BCrypt
        User user = userService.login(username.trim(), password.trim());

        if (user != null) {
            // Đăng nhập thành công -> lưu thông tin vào HttpSession
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);

            // Chuyển hướng sang trang chủ
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            // Đăng nhập thất bại -> hiển thị thông báo lỗi
            req.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
