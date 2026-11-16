package vn.iotstar.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int categoryId;

    @Column(name = "categoryname", columnDefinition = "NVARCHAR(255)")
    private String categoryname;

    @Column(name = "images", columnDefinition = "NVARCHAR(255)")
    private String images;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Video> videos = new ArrayList<>();

    // Default constructor
    public Category() {
    }

    // Parameterized constructor without ID and videos
    public Category(String categoryname, String images, int status) {
        this.categoryname = categoryname;
        this.images = images;
        this.status = status;
    }

    // Full constructor
    public Category(int categoryId, String categoryname, String images, int status, List<Video> videos) {
        this.categoryId = categoryId;
        this.categoryname = categoryname;
        this.images = images;
        this.status = status;
        this.videos = (videos != null) ? videos : new ArrayList<>();
    }

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    // Relationship management helper methods
    public Video addVideo(Video video) {
        if (videos == null) {
            videos = new ArrayList<>();
        }
        videos.add(video);
        video.setCategories(this);
        return video;
    }

    public Video removeVideo(Video video) {
        if (videos != null) {
            videos.remove(video);
            video.setCategories(null);
        }
        return video;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryname='" + categoryname + '\'' +
                ", images='" + images + '\'' +
                ", status=" + status +
                '}';
    }
}
