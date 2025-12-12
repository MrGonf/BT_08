package com.hpnb.vn.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VideoId")
    private Long videoId;

    @Column(name = "Title", length = 200)
    private String title;

    @Column(name = "Poster", length = 255)
    private String poster;

    @Column(name = "Views")
    private Integer views;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "Active")
    private Boolean active;

    // ---------------------------
    // VIDEO → CATEGORY (Many-to-One)
    // ---------------------------
    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private CategoryModels category;
}
