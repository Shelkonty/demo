package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(columnDefinition = "text", length = 150, nullable = false)
    private String text;
    private Long price;

    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> userParticipants = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User users;
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    private List<Participants> participants = new ArrayList<>();
    @Column(updatable = false)

    private LocalDateTime createDate;

    @PrePersist
    protected void onCreate(){
        this.createDate = LocalDateTime.now();
    }


}
