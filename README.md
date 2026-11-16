# Hướng Dẫn Cài Đặt và Khởi Chạy Dự Án JSP/Servlet MVC - Xác Thực Người Dùng

Dự án mẫu triển khai hệ thống **Xác thực & Phân quyền Người dùng** bằng **Java Web (JSP, Servlet, JDBC)** và **SQL Server** theo kiến trúc **MVC (Model-View-Controller)**.

---

## 1. Cấu Trúc Thư Mục Dự Án

```
UserAuthProject/
├── pom.xml
├── database.sql
├── src/
│   └── main/
│       ├── java/
│       │   └── vn/iotstar/
│       │       ├── configs/
│       │       │   └── DBConnect.java
│       │       ├── controllers/
│       │       │   ├── LoginController.java
│       │       │   ├── RegisterController.java
│       │       │   ├── WaitingController.java
│       │       │   ├── LogoutController.java
│       │       │   └── HomeController.java
│       │       ├── daos/
│       │       │   ├── UserDao.java
│       │       │   └── impl/
│       │       │       └── UserDaoImpl.java
│       │       ├── models/
│       │       │   └── User.java
│       │       ├── services/
│       │       │   ├── UserService.java
│       │       │   └── impl/
│       │       │       └── UserServiceImpl.java
│       │       └── utils/
│       │           └── Constant.java
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml
│           └── views/
│               ├── login.jsp
│               ├── register.jsp
│               ├── topbar.jsp
│               └── home.jsp
```

---

## 2. Thiết Lập Cơ Sở Dữ Liệu (SQL Server)

1. Mở **SQL Server Management Studio (SSMS)**.
2. Mở file `database.sql` và thực thi (Execute) toàn bộ script.
3. Script sẽ tạo database `UserAuthDB` và bảng `[User]` cùng 3 tài khoản mẫu:
   - **Admin (roleid = 1)**: `admin` / `123456`
   - **Manager (roleid = 2)**: `manager` / `123456`
   - **User (roleid = 5)**: `user01` / `123456`
4. Cập nhật thông tin tài khoản SQL Server (`sa` và `password`) trong file:
   `src/main/java/vn/iotstar/configs/DBConnect.java`

---

## 3. Chạy Dự Án Trên Eclipse / IntelliJ / NetBeans

1. **Import Dự án**:
   - Eclipse: `File` -> `Import...` -> `Maven` -> `Existing Maven Projects` -> Chọn thư mục dự án.
   - IntelliJ: `File` -> `Open` -> Chọn file `pom.xml`.
2. **Cấu hình Server**:
   - Sử dụng **Apache Tomcat 8.5** hoặc **Tomcat 9.0** (hỗ trợ Servlet 3.1 / 4.0 với namespace `javax.servlet`).
3. **Khởi chạy ứng dụng**:
   - Nhấp chuột phải vào dự án -> `Run As` -> `Run on Server`.
   - Đường dẫn mặc định: `http://localhost:8080/UserAuthProject/login`

