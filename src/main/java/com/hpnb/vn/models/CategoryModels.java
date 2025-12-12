package com.hpnb.vn.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private Long categoryId;

    @Column(name = "Categoryname", length = 100)
    private String categoryName;

    @Column(name = "Categorycode", length = 50)
    private String categoryCode;

    @Column(name = "Images", length = 255)
    private String images;

    @Column(name = "Status")
    private Boolean status;

    // ---------------------------
    // USER → CATEGORY (Many-to-One)
    // ---------------------------
    @ManyToOne
    @JoinColumn(name = "Username")
    private UsersModels user;

    // ---------------------------
    // CATEGORY → VIDEO (One-to-Many)
    // ---------------------------
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<VideoModels> videos;

	public CategoryModels() {
		super();
	}

	public CategoryModels(Long categoryId, String categoryName, String categoryCode, String images, Boolean status,
			UsersModels user, List<VideoModels> videos) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryCode = categoryCode;
		this.images = images;
		this.status = status;
		this.user = user;
		this.videos = videos;
	}

	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public UsersModels getUser() {
		return user;
	}

	public void setUser(UsersModels user) {
		this.user = user;
	}

	public List<VideoModels> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoModels> videos) {
		this.videos = videos;
	}

    
    
}

