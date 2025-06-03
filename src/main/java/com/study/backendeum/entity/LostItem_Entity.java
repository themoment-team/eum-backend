package com.study.backendeum.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "lostitem")
public class LostItem_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int board_id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User_Entity user;

    @Column(length = 40, nullable = false)
    private String lostitem_name;

    @Column(length = 287)
    private String lostitem_detail;

    @Column(nullable = false)
    @Lob
    private String lostitem_url_image;
}