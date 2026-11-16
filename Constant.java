package vn.iotstar.utils;

/**
 * Lớp chứa các hằng số dùng chung trong toàn bộ hệ thống.
 */
public class Constant {
    // Tên Cookie dùng cho chức năng 'Nhớ tôi' (Remember Me)
    public static final String COOKIE_REMEMBER = "username";

    // Thời gian sống của Cookie Remember Me: 30 phút (30 * 60 giây)
    public static final int COOKIE_MAX_AGE = 30 * 60;

    // Hằng số đường dẫn tới các trang JSP
    public static class Path {
        public static final String LOGIN = "/views/login.jsp";
        public static final String REGISTER = "/views/register.jsp";
        public static final String TOPBAR = "/views/topbar.jsp";
    }
}

