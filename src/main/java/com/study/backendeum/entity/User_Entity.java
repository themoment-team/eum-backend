package com.study.backendeum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User_Entity {
    @Id
    @Column(name = "student_id")
    private int student_id;

    @Column(length = 20, nullable = false)
    private String student_name;

    @Column(length = 45, nullable = false)
    private String email;

    @Column(length = 45, nullable = false)
    private String password;
}