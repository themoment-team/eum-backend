package com.study.backendeum.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "getitem")
public class GetItem_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int board_id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User_Entity user;

    @Column(length = 20, nullable = false)
    private String student_name;

    @Column(length = 40)
    private String GetItem_name;

    @Column(length = 287)
    private String GetItem_detail;

    @Column(nullable = false)
    @Lob
    private String GetItem_url_image;
}