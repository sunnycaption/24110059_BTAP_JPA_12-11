package vn.iotstar.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.utils.Constant;

/**
 * Controller xử lý đăng xuất hệ thống (/logout).
 */
@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Hủy Session hiện tại
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute("account");
            session.invalidate();
        }

        // 2. Xóa Cookie 'Nhớ tôi' nếu có
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, "");
        cookie.setMaxAge(0); // Xóa ngay lập tức
        cookie.setPath("/");
        resp.addCookie(cookie);

        // 3. Chuyển hướng về trang đăng nhập
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

