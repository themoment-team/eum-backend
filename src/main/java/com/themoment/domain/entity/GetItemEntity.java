package com.themoment.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "getitem")
public class GetItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private UserEntity user;

    @Column(length = 20, nullable = false)
    private String student_name;

    @Column(length = 40)
    private String GetItem_name;

    @Column(length = 287)
    private String GetItem_detail;

    @Lob
    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String GetItem_url_image;
}