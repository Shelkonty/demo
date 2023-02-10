package com.example.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;
    private String senderName;

    @Column(columnDefinition = "text")
    private String message;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    private User users;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm-ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }
}
