package vn.iotstar.models;

import java.io.Serializable;
import java.sql.Date;

/**
 * Model User tương ứng với cấu trúc bảng [User] trong SQL Server.
 * Implements Serializable để hỗ trợ lưu trữ trong HTTP Session và truyền tải dữ liệu.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String email;
    private String username;
    private String fullname;
    private String password;
    private String avatar;
    private int roleid;
    private String phone;
    private Date createddate;

    // 1. Constructor mặc định (không tham số)
    public User() {
        super();
    }

    // 2. Constructor đầy đủ tham số (bao gồm cả id)
    public User(int id, String email, String username, String fullname, String password, String avatar, int roleid,
                String phone, Date createddate) {
        super();
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.avatar = avatar;
        this.roleid = roleid;
        this.phone = phone;
        this.createddate = createddate;
    }

    // 3. Constructor không tham số id (sử dụng khi đăng ký / chèn mới bản ghi)
    public User(String email, String username, String fullname, String password, String avatar, int roleid,
                String phone, Date createddate) {
        super();
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.avatar = avatar;
        this.roleid = roleid;
        this.phone = phone;
        this.createddate = createddate;
    }

    // --- Getter và Setter ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Alias hỗ trợ cả getter/setter theo userName
    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    // Alias hỗ trợ cả getter/setter theo fullName
    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Alias hỗ trợ cả getter/setter theo passWord
    public String getPassWord() {
        return password;
    }

    public void setPassWord(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    // Alias hỗ trợ cả getter/setter theo createdDate
    public Date getCreatedDate() {
        return createddate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createddate = createdDate;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", username=" + username + ", fullname=" + fullname
                + ", password=" + password + ", avatar=" + avatar + ", roleid=" + roleid + ", phone=" + phone
                + ", createddate=" + createddate + "]";
    }
}
