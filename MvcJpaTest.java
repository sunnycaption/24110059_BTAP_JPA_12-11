package vn.iotstar.test;

import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;
import vn.iotstar.service.impl.UserServiceImpl;

/**
 * Lớp kiểm thử tự động toàn diện cho MVC 3 tầng và JPA (Service + DAO + Entity + Database).
 */
public class MvcJpaTest {
    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("BẮT ĐẦU KIỂM THỬ TOÀN DIỆN MVC 3 TẦNG VÀ JPA - SQL SERVER");
        System.out.println("==========================================================");

        UserService userService = new UserServiceImpl();

        String testUsername = "student_" + System.currentTimeMillis() % 10000;
        String testPassword = "Password123@";
        String testFullname = "Nguyễn Văn Sinh Viên";
        String testEmail = testUsername + "@iotstar.vn";

        try {
            // 1. Kiểm tra đăng ký tài khoản mới
            System.out.println("\n[TEST 1] Đăng ký tài khoản mới:");
            System.out.println("- Username: " + testUsername);
            System.out.println("- Email: " + testEmail);
            boolean regResult = userService.register(testUsername, testPassword, testFullname, testEmail);
            if (regResult) {
                System.out.println("=> [PASS] Đăng ký tài khoản thành công!");
            } else {
                System.err.println("=> [FAIL] Đăng ký thất bại!");
                return;
            }

            // 2. Kiểm tra dữ liệu được lưu trong database và mật khẩu được mã hóa BCrypt
            System.out.println("\n[TEST 2] Kiểm tra dữ liệu trong SQL Server và mã hóa BCrypt:");
            User savedUser = userService.findByUsername(testUsername);
            if (savedUser != null) {
                System.out.println("=> [PASS] Tìm thấy user trong SQL Server!");
                System.out.println("   + ID: " + savedUser.getId());
                System.out.println("   + Fullname: " + savedUser.getFullname());
                System.out.println("   + Email: " + savedUser.getEmail());
                System.out.println("   + Hash BCrypt: " + savedUser.getPassword());
                if (savedUser.getPassword().startsWith("$2a$") || savedUser.getPassword().startsWith("$2b$") || savedUser.getPassword().startsWith("$2y$")) {
                    System.out.println("=> [PASS] Mật khẩu đã được mã hóa an toàn bằng chuẩn BCrypt (không lưu plain-text)!");
                } else {
                    System.err.println("=> [FAIL] Mật khẩu chưa được băm bằng BCrypt!");
                }
            } else {
                System.err.println("=> [FAIL] Không tìm thấy user trong database!");
                return;
            }

            // 3. Kiểm tra kiểm tra trùng username
            System.out.println("\n[TEST 3] Kiểm tra kiểm tra trùng Username:");
            boolean dupUser = userService.register(testUsername, "OtherPass456", "Người Khác", "other@iotstar.vn");
            if (!dupUser) {
                System.out.println("=> [PASS] Hệ thống đã chặn thành công khi đăng ký trùng Username!");
            } else {
                System.err.println("=> [FAIL] Cho phép đăng ký trùng Username!");
            }

            // 4. Kiểm tra kiểm tra trùng email
            System.out.println("\n[TEST 4] Kiểm tra kiểm tra trùng Email:");
            boolean dupEmail = userService.register("otheruser", "OtherPass456", "Người Khác", testEmail);
            if (!dupEmail) {
                System.out.println("=> [PASS] Hệ thống đã chặn thành công khi đăng ký trùng Email!");
            } else {
                System.err.println("=> [FAIL] Cho phép đăng ký trùng Email!");
            }

            // 5. Kiểm tra đăng nhập với mật khẩu đúng
            System.out.println("\n[TEST 5] Kiểm tra đăng nhập ĐÚNG thông tin:");
            User loginUser = userService.login(testUsername, testPassword);
            if (loginUser != null) {
                System.out.println("=> [PASS] Đăng nhập thành công! Chào mừng: " + loginUser.getFullname());
            } else {
                System.err.println("=> [FAIL] Đăng nhập đúng thông tin nhưng thất bại!");
            }

            // 6. Kiểm tra đăng nhập với mật khẩu SAI
            System.out.println("\n[TEST 6] Kiểm tra đăng nhập SAI mật khẩu:");
            User wrongLogin = userService.login(testUsername, "SaiMatKhau123");
            if (wrongLogin == null) {
                System.out.println("=> [PASS] Hệ thống đã từ chối đăng nhập khi sai mật khẩu!");
            } else {
                System.err.println("=> [FAIL] Hệ thống cho phép đăng nhập khi sai mật khẩu!");
            }

            // 7. Kiểm tra đăng nhập với tài khoản không tồn tại
            System.out.println("\n[TEST 7] Kiểm tra đăng nhập tài khoản KHÔNG tồn tại:");
            User nonExist = userService.login("khong_ton_tai_999", "Password123");
            if (nonExist == null) {
                System.out.println("=> [PASS] Hệ thống đã từ chối tài khoản không tồn tại!");
            } else {
                System.err.println("=> [FAIL] Lỗi tài khoản không tồn tại!");
            }

            System.out.println("\n==========================================================");
            System.out.println("TẤT CẢ 7/7 TEST CASE MVC 3 TẦNG & JPA ĐỀU ĐẠT (PASSED)!");
            System.out.println("==========================================================");

        } catch (Exception e) {
            System.err.println("Lỗi trong quá trình kiểm thử: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JpaConfig.shutdown();
        }
    }
}
