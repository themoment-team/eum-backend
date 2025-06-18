package com.themoment.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "lostitem")
public class LostItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private UserEntity user;

    @Column(length = 20, nullable = false)
    private String student_name;

    @Column(length = 40, nullable = false)
    private String lostitem_name;

    @Column(length = 287)
    private String lostitem_detail;

    @Column(nullable = false)
    @Lob
    private String lostitem_url_image;
}