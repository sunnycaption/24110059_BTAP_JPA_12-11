package vn.iotstar.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

/**
 * Controller xử lý đăng ký tài khoản (/register).
 */
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService service = new UserServiceImpl();

    /**
     * doGet: Forward tới views/register.jsp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    /**
     * doPost:
     * - Nhận parameters (set UTF-8).
     * - Gọi Service check trùng Email hoặc Username. Nếu trùng -> set alert và forward lại.
     * - Nếu hợp lệ -> gọi register(). Thành công -> redirect /login. Thất bại -> set alert và forward lại.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        // 1. Kiểm tra trùng Email
        if (service.checkExistEmail(email)) {
            req.setAttribute("alert", "Email đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // 2. Kiểm tra trùng Username
        if (service.checkExistUsername(username)) {
            req.setAttribute("alert", "Tài khoản đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // 3. Thực hiện đăng ký
        boolean isSuccess = service.register(username, password, email, fullname, phone);

        if (isSuccess) {
            // Đăng ký thành công -> redirect tới /login
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // Đăng ký thất bại do lỗi hệ thống -> báo alert và forward lại trang đăng ký
            req.setAttribute("alert", "Đăng ký thất bại, lỗi hệ thống!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
