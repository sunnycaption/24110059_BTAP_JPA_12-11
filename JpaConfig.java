package vn.iotstar.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Lớp cấu hình quản lý EntityManagerFactory và EntityManager cho JPA.
 * Sử dụng Singleton Pattern để không tạo mới EntityManagerFactory mỗi lần gọi.
 */
public class JpaConfig {
    private static EntityManagerFactory factoryJavaWebMVC;
    private static EntityManagerFactory factoryCategoryVideo;

    // Lấy EntityManagerFactory cho persistence-unit "JavaWebMVC"
    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (factoryJavaWebMVC == null || !factoryJavaWebMVC.isOpen()) {
            factoryJavaWebMVC = Persistence.createEntityManagerFactory("JavaWebMVC");
        }
        return factoryJavaWebMVC;
    }

    // Lấy EntityManager mặc định (kết nối database JavaWebMVC)
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    // Lấy EntityManager cho persistence-unit "jpa-hibernate-mysql" (kết nối database webst2)
    public static synchronized EntityManagerFactory getCategoryVideoFactory() {
        if (factoryCategoryVideo == null || !factoryCategoryVideo.isOpen()) {
            factoryCategoryVideo = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
        }
        return factoryCategoryVideo;
    }

    public static EntityManager getCategoryVideoEntityManager() {
        return getCategoryVideoFactory().createEntityManager();
    }

    // Đóng toàn bộ EntityManagerFactory khi ứng dụng kết thúc
    public static synchronized void shutdown() {
        if (factoryJavaWebMVC != null && factoryJavaWebMVC.isOpen()) {
            factoryJavaWebMVC.close();
            factoryJavaWebMVC = null;
        }
        if (factoryCategoryVideo != null && factoryCategoryVideo.isOpen()) {
            factoryCategoryVideo.close();
            factoryCategoryVideo = null;
        }
    }
}
