package vn.iotstar;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.iotstar.config.JpaConfig;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;

public class Test {
    public static void main(String[] args) {
        EntityManager em = JpaConfig.getCategoryVideoEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            // 1. Tạo đối tượng Category
            Category category = new Category();
            category.setCategoryname("Iphone");
            category.setImages("abc.jpg");
            category.setStatus(1);

            // 5. Tạo đối tượng Video
            Video video = new Video();
            video.setVideoId("v01");
            video.setTitle("test");
            video.setActive(true);
            video.setDescription("Video test JPA/Hibernate");
            video.setPoster("poster01.jpg");
            video.setViews(100);

            // 8. Gán Video vào Category
            category.addVideo(video);

            // 9. Bắt đầu transaction
            trans.begin();

            // 10. Persist Category và Video
            em.persist(category);
            em.persist(video);

            // 11. Commit transaction
            trans.commit();

            System.out.println(">>> Thêm Category và Video thành công!");
            System.out.println("Category ID vừa tạo: " + category.getCategoryId());

            // 12. Kiểm tra dữ liệu đã được lưu vào SQL Server
            Category savedCategory = em.find(Category.class, category.getCategoryId());
            System.out.println(">>> Kiểm tra dữ liệu từ database:");
            System.out.println("Category: " + savedCategory.getCategoryname() + ", Status: " + savedCategory.getStatus());

            Video savedVideo = em.find(Video.class, "v01");
            if (savedVideo != null) {
                System.out.println("Video: ID=" + savedVideo.getVideoId() + ", Title=" + savedVideo.getTitle() +
                        ", Thuộc Category: " + (savedVideo.getCategories() != null ? savedVideo.getCategories().getCategoryname() : "null"));
            }

        } catch (Exception e) {
            if (trans != null && trans.isActive()) {
                trans.rollback();
                System.err.println(">>> Transaction rolled back do có lỗi.");
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            JpaConfig.shutdown();
        }
    }
}
