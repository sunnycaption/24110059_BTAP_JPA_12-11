package vn.iotstar.services;

import vn.iotstar.models.User;

/**
 * Interface UserService định nghĩa các nghiệp vụ xác thực và quản lý tài khoản.
 */
public interface UserService {
    /**
     * Xác thực thông tin đăng nhập.
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @return User nếu thông tin hợp lệ, ngược lại null
     */
    User login(String username, String password);

    /**
     * Lấy thông tin User theo tên đăng nhập (dùng cho khôi phục phiên từ Cookie).
     * @param username Tên đăng nhập
     * @return User tương ứng hoặc null
     */
    User get(String username);

    /**
     * Đăng ký tài khoản mới cho người dùng.
     * @param username Tên đăng nhập
     * @param password Mật khẩu
     * @param email Email
     * @param fullname Họ và tên
     * @param phone Số điện thoại
     * @return true nếu đăng ký thành công, false nếu username đã tồn tại
     */
    boolean register(String username, String password, String email, String fullname, String phone);

    /**
     * Kiểm tra trùng Email.
     * @param email Địa chỉ email
     * @return true nếu đã tồn tại, false nếu chưa
     */
    boolean checkExistEmail(String email);

    /**
     * Kiểm tra trùng Username.
     * @param username Tên đăng nhập
     * @return true nếu đã tồn tại, false nếu chưa
     */
    boolean checkExistUsername(String username);
}
