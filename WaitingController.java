package vn.iotstar.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.iotstar.models.User;

/**
 * Controller trung gian điều hướng phân quyền sau khi đăng nhập (/waiting).
 */
@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * doGet:
     * - Lấy đối tượng User từ Session ('account'). Nếu Session null hoặc account null -> redirect /login.
     * - Phân quyền:
     *   + roleid == 1 -> redirect /admin/home
     *   + roleid == 2 -> redirect /manager/home
     *   + Các role còn lại -> redirect /home
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");

            // Phân quyền điều hướng theo roleid
            if (user.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            } else if (user.getRoleid() == 2) {
                resp.sendRedirect(req.getContextPath() + "/manager/home");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        } else {
            // Chưa đăng nhập -> redirect /login
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
