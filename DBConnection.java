package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Cấu hình kết nối SQL Server chuẩn khớp với thông tin SSMS:
 * - Server Name: localhost\SQLEXPRESS
 * - Database Name: UserAuthDB
 * - Encrypt: Mandatory (encrypt=true)
 * - Trust Server Certificate: true (trustServerCertificate=true)
 */
public class DBConnection {

    // Tên máy chủ và Instance SQL Server khớp với màn hình SSMS
    private final String serverName = "localhost";
    private final String instanceName = "SQLEXPRESS";
    private final String dbName = "UserAuthDB";

    // Tùy chọn 1: Nếu dùng tài khoản SQL Server Authentication (sa)
    private final String userID = "sa";
    private final String password = "123";

    /**
     * Phương thức lấy kết nối đến cơ sở dữ liệu.
     * Tự động thử kết nối theo tài khoản sa, nếu chưa bật sa sẽ fallback sang Windows Authentication.
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // 1. Chuỗi kết nối khớp với cấu hình trong SSMS của bạn:
            // Server Name: localhost\SQLEXPRESS, Database: UserAuthDB
            // Encrypt: Mandatory, TrustServerCertificate: true
            String url = "jdbc:sqlserver://" + serverName + "\\" + instanceName 
                    + ";databaseName=" + dbName 
                    + ";encrypt=true;trustServerCertificate=true;";

            // Thử kết nối với user 'sa'
            try {
                conn = DriverManager.getConnection(url, userID, password);
            } catch (Exception ex) {
                // 2. Nếu sa bị khóa/chưa bật, thử kết nối bằng Windows Authentication (Integrated Security)
                // giống như lựa chọn 'Windows Authentication' trong ảnh SSMS của bạn
                String urlIntegrated = "jdbc:sqlserver://" + serverName + "\\" + instanceName 
                        + ";databaseName=" + dbName 
                        + ";integratedSecurity=true"
                        + ";encrypt=true;trustServerCertificate=true;";
                conn = DriverManager.getConnection(urlIntegrated);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Lỗi: Không tìm thấy JDBC Driver cho SQL Server!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Lỗi kết nối SQL Server: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Chạy thử nghiệm kết nối độc lập
     */
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        Connection conn = db.getConnection();
        if (conn != null) {
            System.out.println("✅ KẾT NỐI SQL SERVER THÀNH CÔNG!");
            System.out.println("   Database: UserAuthDB trên localhost\\SQLEXPRESS");
        } else {
            System.err.println("❌ KẾT NỐI THẤT BẠI! Vui lòng kiểm tra lại cấu hình SQL Server.");
        }
    }
}
