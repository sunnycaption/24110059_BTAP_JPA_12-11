package vn.iotstar.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.models.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.impl.UserServiceImpl;

/**
 * Controller xử lý đăng nhập hệ thống (/login).
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        // 1. Kiểm tra session
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        // 2. Kiểm tra cookie Remember Me
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    String username = cookie.getValue();
                    User user = userService.get(username);
                    if (user != null) {
                        session = req.getSession(true);
                        session.setAttribute("account", user);
                        resp.sendRedirect(req.getContextPath() + "/waiting");
                        return;
                    }
                }
            }
        }

        // 3. Chưa đăng nhập -> forward views/login.jsp
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String rawUsername = req.getParameter("username");
        String rawPassword = req.getParameter("password");
        String remember = req.getParameter("remember");

        // Gọi trim() để loại bỏ hoàn toàn khoảng trắng thừa
        String username = (rawUsername != null) ? rawUsername.trim() : "";
        String password = (rawPassword != null) ? rawPassword.trim() : "";

        // DEBUG LOG RA CONSOLE
        System.out.println("==================================================");
        System.out.println(">>> [LoginController] Login attempt - User: [" + username + "] | Pass: [" + password + "]");

        // Gọi service xác thực đăng nhập
        User user = userService.login(username, password);

        if (user != null) {
            System.out.println(">>> [LoginController] Đăng nhập THÀNH CÔNG cho user: " + user.getUsername() + " (roleid: " + user.getRoleid() + ")");
            
            // Lưu account vào Session
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);

            // Xử lý Remember Me (30 phút)
            if ("on".equals(remember) || "true".equals(remember)) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(30 * 60);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }

            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            System.out.println(">>> [LoginController] Đăng nhập THẤT BẠI cho user: [" + username + "]");
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
        System.out.println("==================================================");
    }
}
