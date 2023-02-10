package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Participants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private Long phone;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String f_name;
    @Column(nullable = false)
    private String l_name;
    @Column(updatable = false)
    private LocalDateTime createDate;

    @PrePersist
    protected void onCreate(){
        this.createDate = LocalDateTime.now();
    }
}
