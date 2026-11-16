package vn.iotstar.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "videos")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "videoId", length = 50)
    private String videoId;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "poster", columnDefinition = "NVARCHAR(255)")
    private String poster;

    @Column(name = "title", columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "views")
    private int views;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category categories;

    // Default constructor
    public Video() {
    }

    // Parameterized constructor without Category
    public Video(String videoId, boolean active, String description, String poster, String title, int views) {
        this.videoId = videoId;
        this.active = active;
        this.description = description;
        this.poster = poster;
        this.title = title;
        this.views = views;
    }

    // Full constructor
    public Video(String videoId, boolean active, String description, String poster, String title, int views, Category categories) {
        this.videoId = videoId;
        this.active = active;
        this.description = description;
        this.poster = poster;
        this.title = title;
        this.views = views;
        this.categories = categories;
    }

    // Getters and Setters
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", active=" + active +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                ", title='" + title + '\'' +
                ", views=" + views +
                ", categoryId=" + (categories != null ? categories.getCategoryId() : "null") +
                '}';
    }
}
