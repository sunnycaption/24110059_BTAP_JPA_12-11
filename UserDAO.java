package vn.iotstar.dao;

import vn.iotstar.entity.User;
import java.util.List;

/**
 * Interface tầng Data Access Object (DAO) cho đối tượng User.
 * Định nghĩa các thao tác CRUD và truy vấn qua JPA.
 */
public interface UserDAO {
    // Thêm User mới vào database
    void insert(User user);

    // Cập nhật thông tin User
    void update(User user);

    // Xóa User theo id
    void delete(int id);

    // Tìm User theo id
    User findById(int id);

    // Tìm User theo username
    User findByUsername(String username);

    // Tìm User theo email
    User findByEmail(String email);

    // Lấy toàn bộ danh sách User
    List<User> findAll();

    // Kiểm tra username đã tồn tại chưa
    boolean checkExistUsername(String username);

    // Kiểm tra email đã tồn tại chưa
    boolean checkExistEmail(String email);
}
