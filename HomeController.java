package vn.iotstar.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller điều hướng các trang đích sau khi phân quyền:
 * /home, /admin/home, /manager/home, /member/myaccount
 */
@WebServlet(urlPatterns = {"/home", "/admin/home", "/manager/home", "/member/myaccount"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        req.setAttribute("currentPath", servletPath);
        req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
    }
}

