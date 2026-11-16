package vn.iotstar.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Filter xác thực người dùng (Authentication Filter).
 * Chặn truy cập trang chủ /home và các trang bảo vệ nếu người dùng chưa đăng nhập.
 */
@WebFilter(urlPatterns = {"/home", "/home/*"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter nếu cần
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("currentUser") != null);

        if (isLoggedIn) {
            // Đã đăng nhập -> cho phép tiếp tục truy cập
            chain.doFilter(request, response);
        } else {
            // Chưa đăng nhập -> chuyển hướng về trang đăng nhập kèm thông báo
            req.setAttribute("errorMessage", "Bạn cần đăng nhập để truy cập trang này!");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        // Dọn dẹp tài nguyên khi filter kết thúc
    }
}
