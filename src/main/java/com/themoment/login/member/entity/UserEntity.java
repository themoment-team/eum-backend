package com.themoment.login.member.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "student_id")
    private Long student_id;

    @Column(length = 20, nullable = false)
    private String student_name;

    @Column(length = 45, nullable = false)
    private String email;

    @Column(length = 45, nullable = false)
    private String password;
}